<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

class Hessian1Parser{
	var $resolver;
	var $stream;
	var $refmap;
	var $typemap;
	var $log = array();
	var $objectFactory;
	var $options;
	var $refs;
	var $filterContainer;
	
	function __construct($resolver, $stream = null, $options = null){
		$this->resolver = $resolver;
		$this->refmap = new HessianReferenceMap();
		$this->typemap = new HessianTypeMap();
		$this->stream = $stream;
		$this->options = $options;
	}
	
	function logMsg($msg){
		$this->log[] = $msg;
	}
	
	function setTypeMap($typemap){
		$this->typemap = $typemap;
	}
	
	function setFilters($container){
		$this->filterContainer = $container;
	}
	
	function read($count=1){
		return $this->stream->read($count);
	} 

	function readNum($count=1){
		return ord($this->stream->read($count));
	} 
	
	function parseCheck($code = null){
		$value = $this->parse($code);
		if(!HessianRef::isRef($value))
			return $value;
		return $this->refmap->objectlist[$value->index];
	}
	
	function parse($code = null, $expect = false){
		$end = true;
		if(!$code)
			$code = $this->read();
		do {
			$rule = $this->resolver->resolveSymbol($code, $expect);
			$fun = $rule->func;
			$num = ord($code);
			$this->logMsg("llamando $fun con code $code y num $num hex 0x".dechex($num). " offset ".$this->stream->pos);
			$value = call_user_func_array(array($this, $fun), array($code, $num));
			if($value instanceof HessianIgnoreCode) {
				$end = false;
				$code = $this->read();
			} else $end = true;
		} while(!$end);
		
		$filter = $this->filterContainer->getCallback($rule->type);
		if($filter)
			$value = $this->filterContainer->doCallback($filter, array($value,$this));
		if(is_object($value)){
			$filter = $this->filterContainer->getCallback($value);
			if($filter)
				$value = $this->filterContainer->doCallback($filter, array($value, $this));
		}
		return $value;
	}
	
	function parseBinary($code, $num){
		$end = false;
		$data = '';
		while(!$end) {
			$bytes = $this->read(2);
			$tempLen = unpack('n',$bytes);
			$len = $tempLen[1];
			$data .= $this->read($len);
			if($code == 'b') {
				$code = $this->read(1);
			} else
				$end = true;
		}
		return $data;
	}
	
	//--- int
	
	function parseInteger($code, $num){
		$data = unpack('N', $this->read(4));
		return $data[1];
	}
	
	function parseBool($code, $num){
		return $code == 'T';
	}
	
	function parseNull($code, $num){
		return null;
	}
	
	//--- datetime
	
	function parseDate($code, $num){
		$ts = HessianUtils::timestampFromBytes64($this->read(8));
		$this->logMsg("timestamp $ts");
		return $ts;
	}
	
	// double
	
	function parseDouble($code, $num){
		$bytes = $this->read(8);
		if(HessianUtils::$littleEndian)
			$bytes = strrev($bytes);
		//$double = unpack("dflt", strrev($bytes));
		$double = unpack("dflt", $bytes);
        return $double['flt'];
	}
	
	// --- long
	
	function parseLong($code, $num){
		return ($this->readNum() << 56) + 
				($this->readNum() << 48) + 
				($this->readNum() << 40) + 
				($this->readNum() << 32) + 
				($this->readNum() << 24) + 
				($this->readNum() << 16) + 
				($this->readNum() << 8) + 
				$this->readNum();
	}
			
	// --- string
	
	function parseString($code, $num){
		$end = false;
		$string = '';
		while(!$end) {
			$tempLen = unpack('n',$this->read(2));
			$len = $tempLen[1];
		
			if($code == 's' || $code == 'x') {
				$code = $this->read(1);
			} else
				$end = true;
			
			$string .= $this->readUTF8Bytes($len);
			//$end = true;
		}
		if(HessianUtils::isInternalUTF8())
			return $string;
		return utf8_decode($string);
	}
	
	// Some UTF8 characters are represented with more than one byte to we need
	// to read every character to find out if we need to read in advance.
	function readUTF8Bytes($len){
		$string = $this->read($len);
		$pos = 0;
		$pass = 1;
		while($pass <= $len){
			$charCode = ord($string[$pos]);
			if($charCode < 0x80){
				$pos++;
			} elseif(($charCode & 0xe0) == 0xc0){
				$pos += 2;
				$string .= $this->read(1);
			} elseif (($charCode & 0xf0) == 0xe0) {
				$pos += 3;
				$string .= $this->read(2);
			}
			$pass++;
		}
		return $string;
	}
	
	//-- list
	function parseList($code, $num){
		$code = $this->read(1);
		// read type if exists
		if($code == 't'){ 
			$type = $this->parseString($code, ord($code));
			$code = $this->read(1);
		}
		// read list length if exists
		if($code == 'l') {
			$lenStruct = unpack('N', $this->read(4));
			$len = $lenStruct[1];
			$code = $this->read(1);
		}
		$list = array();
		
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$list;
		while($code != 'z'){
			$item = $this->parse($code);
			if(HessianRef::isRef($item))
				$list[] = &$this->refmap->objectlist[$item->index];
			else
				$list[] = $item;
			//$list[] = $this->parse($code); 
			$code = $this->read(1);
		}
		return $list;
	}
			
	//-- map
	function parseMap($code, $num){
		if($this->read(1) != 't') {
			throw new HessianParsingException('Malformed map format: expected t');
		}
		$type = $this->parseString($code, ord($num));

		$localType = $this->typemap->getLocalType($type);
		if(!$localType)
			$map = array();	
		else {
			$map = $this->objectFactory->getObject($localType);
		}
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$map;
		$code = $this->read(1);
		while($code != 'z'){
			$key = $this->parse($code);
			$value = $this->parse();
			if(HessianRef::isRef($key)) $key = &$this->refmap->objectlist[$key->index];
			if(HessianRef::isRef($value)) $value = &$this->refmap->objectlist[$value->index];
			if(!$localType)
				$map[$key] = $value;
			else
				$map->$key = $value;
			$code = $this->read(1);
		}
		return $map;
	}
		
	function parseReference($code, $num){
		$refStruct = unpack('N', $this->read(4));
		$numRef = $refStruct[1];
		if(isset($this->refmap->reflist[$numRef]))
			return $this->refmap->reflist[$numRef];
		else
			throw new HessianParsingException("Unresolved referenced object number $numRef");
	}
	
	
}
