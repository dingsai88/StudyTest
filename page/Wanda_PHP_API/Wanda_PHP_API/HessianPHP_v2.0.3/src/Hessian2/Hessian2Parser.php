<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel G�mez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

class Hessian2Parser{
	var $resolver;
	var $stream;
	var $refmap;
	var $typemap;
	var $log = array();
	var $objectFactory;
	var $options;
	var $filterContainer;
	
	function __construct($resolver, $stream = null, $options = null){
		$this->resolver = $resolver;
		$this->refmap = new HessianReferenceMap();
		$this->typemap = new HessianTypeMap();
		$this->stream = $stream;
		$this->options = $options;
	}
	
	function setStream($stream){
		$this->stream;
	}

	function setTypeMap($typemap){
		$this->typemap = $typemap;
	}
	
	function setFilters($container){
		$this->filterContainer = $container;
	}
	
	function logMsg($msg){
		$this->log[] = $msg;
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
			$value = $this->$fun($code, $num);
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
	
	function binary0($code, $num){
		$len = $num - 0x20;
		return $this->read($len);
	}
	
	function binary1($code, $num){
		$len = (($num - 0x34) << 8) + ord($this->read());
		return $this->read($len);	
	}
	
	function binaryLongData(){
		$tempLen = unpack('n',$this->read(2));
		$len = $tempLen[1];
		return $this->read($len);
	}

	function binaryLong($code, $num){
		$final = true;
		$data = '';
		do{
			$final = $num != 0x41;
			if($num == 0x41 || $num == 0x42)
				$data .= $this->binaryLongData();
			else
				$data .= $this->parse($code, 'binary');
			if(!$final){
				$code = $this->read();	
				$num = ord($code);
			}
		} while (!$final);
		return $data;
	}
	
	//--- int
	
	function compactInt1($code, $num){
		if($code == 0x90)
			return 0;
		return ord($code) - 0x90;
	}
	
	function compactInt2($code, $num){
		$b0 = ord($this->read());
		return ((ord($code) - 0xc8) << 8) + $b0;
	}
	
	function compactInt3($code, $num){
		$b1 = ord($this->read());
		$b0 = ord($this->read());
		return ((ord($code) - 0xd4) << 16) + ($b1 << 8) + $b0;
	} 
	
	function parseInt($code, $num){
		$data = unpack('N', $this->read(4));
		return $data[1];
	}
	
	function bool($code, $num){
		return $code == 'T';
	}
	
	//--- datetime
	
	function date($code, $num){
		$ts = HessianUtils::timestampFromBytes64($this->read(8));
		/*$data = unpack('N2', $this->read(8));
		$ts = ($data[1] *256*256*256*256 ) + $data[2];
		$ts = $ts / 1000;*/
		return $ts;
	}
	
	function compactDate($code, $num){
		$data = unpack('C4', $this->read(4));
		$num = ($data[1] << 24) +
				($data[2] << 16) +
				($data[3] << 8) +
				$data[4];
		$ts = $num * 60;
		return $ts;
	}
	
	// double
	
	function double1($code, $num){
		if($num == 0x5b)
			return (float)0;
		if($num == 0x5c)
			return (float)1.0;
		$bytes = $this->read(1);
		return (float)ord($bytes);
	}
	
	function double2($code, $num){
		$bytes = $this->read(2);
		$b = unpack('s', strrev($bytes));
		return (float)$b[1];
	}
	
	function double4($code, $num){
		$b = $this->read(4);
		$num = (ord($b[0]) << 24) + 
				(ord($b[1]) << 16) +
				(ord($b[2]) << 8) +
				ord($b[3]);
		return 0.001 * $num;
		// from the java implementation, this makes no sense
		// why not just use the float bytes as any sane language and pack it like 'f'?
	}
	
	function double64($code, $num){
		$bytes = $this->read(8);
		if(HessianUtils::$littleEndian)
			$bytes = strrev($bytes);
		//$double = unpack("dflt", strrev($bytes));
		$double = unpack("dflt", $bytes);
        return $double['flt'];
	}
	
	// --- long
	
	function long1($code, $num){
		if($code == 0xe0)
			return 0;
		return $num - 0xe0;
	}
	
	function long2($code, $num){
		return (($num - 0xf8) << 8) + $this->readNum();
	}
	
	function long3($code, $num){
		return ((ord($code) - 0x3c) << 16) 
			+ ($this->readNum() << 8) 
			+ $this->readNum();
	} 
	
	function long32($code, $num){
		return ($this->readNum() << 24) + 
				($this->readNum() << 16) + 
				($this->readNum() << 8) + 
				$this->readNum();
	}
	
	function long64($code, $num){
		return ($this->readNum() << 56) + 
				($this->readNum() << 48) + 
				($this->readNum() << 40) + 
				($this->readNum() << 32) + 
				($this->readNum() << 24) + 
				($this->readNum() << 16) + 
				($this->readNum() << 8) + 
				$this->readNum();
	}
	
	function parseNull($code, $num){
		return null;
	}
	
	function reserved($code, $num){
		throw new HessianParsingException("Code $code reserved");
	}
	
	// --- string
	
	function string0($code, $num){
		return $this->readUTF8Bytes($num);
	}
	
	function string1($code, $num){
		$len = (($num - 0x30) << 8) + ord($this->read());
		return $this->readUTF8Bytes($len);
	}
	
	function stringLongData(){
		$tempLen = unpack('n',$this->read(2));
		$len = $tempLen[1];
		return $this->readUTF8Bytes($len);
	}
	
	function stringLong($code, $num){
		$final = true;
		$data = '';
		// TODO Probar con textos bien largos con caracteres utf-8 puede haber problemas
		do{
			$final = $num != 0x52;
			if($num == 0x52 || $num == 0x53)
				$data .= $this->stringLongData();
			else
				$data .= $this->parse($code, 'string');
			if(!$final){
				$code = $this->read();	
				$num = ord($code);
			}
		} while (!$final);
		return $data;
	}
	
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
		
		if(HessianUtils::isInternalUTF8())
			return $string;
		//return utf8_decode($string); //modified by toddyu
        return $string;
		
		/*$string = '';
		for($i=0;$i<$len;$i++){
			$ch = $this->read(1);
			$charCode = ord($ch);
			if($charCode < 0x80)
				$string .= $ch;
			elseif(($charCode & 0xe0) == 0xc0) {
				$string .= $ch.$this->read(1);
			} elseif (($charCode & 0xf0) == 0xe0) {
				$string .= $ch.$this->read(2);
			} else {
				throw new HessianParsingException("Bad utf-8 encoding at pos ".$this->stream->pos);
			}
		}
		if(HessianUtils::isInternalUTF8())
			return $string;
		return utf8_decode($string);*/
	}
	
	//-- list
	function vlenList($code, $num){
		$type = $this->parseType();
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		while($code != 'Z'){
			$code = $this->read();
			if($code != 'Z'){
				$item = $this->parse($code);
				if(HessianRef::isRef($item))
					$array[] = &$this->refmap->objectlist[$item->index];
				else
					$array[] = $item;
				//$array[] = $this->parse($code);
			}
		}
		return $array;
	}
	
	function flenList($code, $num){
		$type = $this->parseType();
		$len = $this->parse(null, 'integer');
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		for($i=0;$i<$len;$i++){
			$item = $this->parse();
			if(HessianRef::isRef($item))
				$array[] = &$this->refmap->objectlist[$item->index];
			else
				$array[] = $item;
			//$array[] = $this->parse();
		}
		return $array;
	}
	
	function vlenUntypedList($code, $num){
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		while($code != 'Z'){
			$code = $this->read();
			if($code != 'Z'){
				$item = $this->parse($code);
				if(HessianRef::isRef($item))
					$array[] = &$this->refmap->objectlist[$item->index];
				else
					$array[] = $item;
				//$array[] = $this->parse($code);
			}
		}
		return $array;
	}
	
	function flenUntypedList($code, $num){
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		$len = $this->parse(null, 'integer');
		for($i=0;$i<$len;$i++){
			$item = $this->parse();
			if(HessianRef::isRef($item))
				$array[] = &$this->refmap->objectlist[$item->index];
			else
				$array[] = $item;
			//$array[] = $this->parse();
		}
		return $array;
	}
	
	function directListTyped($code, $num){
		$len = ord($code) - 0x70;
		$type = $this->parseType();
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		for($i=0;$i<$len;$i++){
			$item = $this->parse();
			if(HessianRef::isRef($item))
				$array[] = &$this->refmap->objectlist[$item->index];
			else
				$array[] = $item;
			//$array[] = $this->parse();
		}
		return $array;
	}
	
	function directListUntyped($code, $num){
		$len = ord($code) - 0x78;
		$array = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$array;
		for($i=0;$i<$len;$i++){
			$item = $this->parse();
			if(HessianRef::isRef($item))
				$array[] = &$this->refmap->objectlist[$item->index];
			else
				$array[] = $item;
			//$array[] = $this->parse();
		}
		return $array;
	}
	
	function parseType(){
		$this->logMsg('Parsing type');
		$type = $this->parse(null, 'string,integer');
		if(is_integer($type)){
			$index = $type;
			if(!isset($this->refmap->reflist[$index]))
				throw new HessianParsingException("Reference index $index not found");
			return $this->refmap->typelist[$index];
		}
		$this->refmap->typelist[] = $type;
		return $type;
	}
	
	//-- map
	function untypedMap($code, $num){
		$map = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$map;
		$code = $this->read();
		while($code != 'Z'){
			$key = $this->parse($code);
			$value = $this->parse();
			if(HessianRef::isRef($key)) $key = &$this->objectlist->reflist[$key->index];
			if(HessianRef::isRef($value)) $value = &$this->objectlist->reflist[$value->index];
			
			$map[$key] = $value;
			if($code != 'Z')
				$code = $this->read();
		}
		return $map;
	}
	
	function typedMap($code, $num){
		$type = $this->parseType();
		$map = array();
		$this->refmap->incReference();
		$this->refmap->objectlist[] = &$map;
		// TODO references and objects
		$code = $this->read();
		while($code != 'Z'){
			$key = $this->parse($code);
			$value = $this->parse();
			if(HessianRef::isRef($key)) $key = &$this->objectlist->reflist[$key->index];
			if(HessianRef::isRef($value)) $value = &$this->objectlist->reflist[$value->index];

			$map[$key] = $value;
			if($code != 'Z')
				$code = $this->read();
		}
		return $map;
	}
	
	//-- object
	function typeDefinition($code, $num){
		$type = $this->parseType();
		$numfields = $this->parse(null, 'integer');
		$classdef = new HessianClassDef();
		$classdef->type = $type;
		for($i=0;$i<$numfields;$i++){
			$classdef->props[] = $this->parse(null, 'string');
		}
		$this->refmap->addClassDef($classdef);
		return $classdef;
	}
	
	function objectInstance($code, $num){
		$index = $this->parse(null, 'integer');
		return $this->fillMap($index);
	}
	
	function objectDirect($code, $num){
		$index = $num - 0x60;
		return $this->fillMap($index);
	}
	
	function fillMap($index){
		if(!isset($this->refmap->classlist[$index]))
			throw new HessianParsingException("Class def index $index not found");
		$classdef = $this->refmap->classlist[$index];
		
		$localType = $this->typemap->getLocalType($classdef->type);
		$obj = $this->objectFactory->getObject($localType);
		
		$this->refmap->incReference();
		$this->refmap->objectlist[] = $obj;

		foreach($classdef->props as $prop){
			$item = $this->parse();
			if(HessianRef::isRef($item)) 
				$item = &$this->refmap->objectlist[$item->index];
			$obj->$prop = $item;
		}

		return $obj;
	}
	
	function reference(){
		$index = $this->parse(null, 'integer');
		if(!isset($this->refmap->reflist[$index]))
			throw new HessianParsingException("Reference index $index not found");
		return $this->refmap->reflist[$index];
	}
	
	
}
