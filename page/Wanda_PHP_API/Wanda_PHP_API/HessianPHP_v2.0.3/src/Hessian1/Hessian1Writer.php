<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

class Hessian1Writer{
	var $refmap;
	var $typemap;
	var $log = array();
	var $options;
	var $filterContainer;
	
	function __construct($options = null){
		$this->refmap = new HessianReferenceMap();
		$this->typemap = new HessianTypeMap();
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
	
	function writeValue($value){
		$type = gettype($value);
		$dispatch = $this->resolveDispatch($type);
		if(is_object($value)){
			$filter = $this->filterContainer->getCallback($value);
			if($filter) {
				$value = $this->filterContainer->doCallback($filter, array($value, $this));
				if($value instanceof HessianStreamResult){
					return $value->stream;
				}
				$ntype = gettype($value);
				if($type != $ntype)
					$dispatch = $this->resolveDispatch($ntype);
			}
		}
		$data = $this->$dispatch($value);
		return $data;	
	}

	function resolveDispatch($type){
		$dispatch = '';
		// TODO usar algun type helper
		switch($type){
			case 'integer': $dispatch = 'writeInt' ;break;
			case 'boolean': $dispatch = 'writeBool' ;break;
			case 'string': $dispatch = 'writeString' ; break;
			case 'double': $dispatch = 'writeDouble' ; break;
			case 'array': $dispatch = 'handleArray' ; break;
			case 'object': $dispatch = 'writeMap' ;break;
			case 'NULL': $dispatch = 'writeNull';break;
			case 'resource': $dispatch = 'writeResource' ; break;
			default: 
				throw new Exception("Handler for type $type not implemented");
		}
		$this->logMsg("dispatch $dispatch");
		return $dispatch;
	}

	function writeNull(){
		return 'N';
	}
		
	function handleArray($array){
		if(empty($array))
			return 'N';
		$refindex = $this->refmap->getReference($array);
		if($refindex !== false){
			return $this->writeReference($refindex);
		}
		if(HessianUtils::isListFormula($array))
			return $this->writeList($array);
		return $this->writeMap($array);
	}
	
	function writeBool($value){
		return $value ? 'T' : 'F';
	}

	function writeString($value){
		return 'S' . $this->writeStringData($value);
	}

	function writeStringData($value){
		$len = HessianUtils::stringLength($value);
		$stream = pack('n', $len);
		$stream .= HessianUtils::writeUTF8($value);
		return $stream;
	}
	
	function writeHeader($value){
		return 'H'. $this->writeStringData($value);
	}

	function writeBytes($value){
		return 'B' . $this->writeStringData($value);
	}

	function writeInt($value){
		return 'I' . $this->writeIntData($value);
	}

	function writeLong($value){
		$stream = 'L';
		$less = $value>>32;
		$res = $value / HessianUtils::pow32; //pow(2,32);
		$stream .= pack('N2',$res,$less);
		return $stream;
	}

	function writeDate($ts){
		//$ts = $this->dateAdapter->toTimestamp($value);
		$this->logMsg("writeDate $ts");
		$stream = 'd';
	    $ts = $ts * 1000;
		$res = $ts / HessianUtils::pow32;
		$stream .= pack('N', $res);
		$stream .= pack('N', $ts);
		return $stream;
	}

	// OJO que no se sabe si la representacion interna de PHP sea 64 bit IEEE 754
	function writeDouble($value){
		$stream = 'D';
		$stream .= HessianUtils::doubleBytes($value);
		return $stream;
	}

	function writeReference($value){
		$this->logMsg("writeReference $value");
		return 'R' . $this->writeIntData($value);
	}

	function writeType($type){
		return 't' . $this->writeStringData($value);
	}
	
	function writeIntData($value){
		$stream = pack('c', ($value >> 24));
		$stream .= pack('c', ($value >> 16));
		$stream .= pack('c', ($value >> 8));
		$stream .= pack('c', $value);
		return $stream;
	}
	
	function writeList($list){
		$refindex = $this->refmap->getReference($list);
		if($refindex !== false){
			return $this->writeReference($refindex);
		}
		
		$this->refmap->objectlist[] = &$list;
		$stream = 'V';
		//$stream .= $this->writeType('');
		if(!empty($list)){
			$stream .= 'l' . $this->writeIntData(count($list));
			foreach($list as $val){
				$stream .= $this->writeValue($val);
			}
		}
		$stream .= 'z';
		return $stream;
	}
	
	function writeMap($value){
		
		$refindex = $this->refmap->getReference($value);
		if($refindex !== false){
			return $this->writeReference($refindex);
		}
		
		//if($this->iteratorWriter->isIterator($value))
		//	return $this->iteratorWriter->write($value);
			
		$stream = "M";
		// type handling for local classes
		$stream .= 't';
		if(is_object($value)) {
			$localType = get_class($value);
			$type = $this->typemap->getRemoteType($localType);
			if(!$type) $type = $localType;
			$stream .= $this->writeStringData($type);
		}
		else
			$stream .= $this->writeStringData('');
		
		if(is_array($value)) {
			$this->refmap->objectlist[] = &$value;
			// arrays
			foreach($value as $key => $val){
				$stream .= $this->writeValue($key);
				$stream .= $this->writeValue($val);
			}
		}
		if(is_object($value)) {
			// classes
			$this->refmap->objectlist[] = $value;
			$vars = get_object_vars($value);
			foreach($vars as $varName => $varValue){
				$stream .= $this->writeValue($varName);
				$stream .= $this->writeValue($value->$varName);
			}
		}

		$stream .= 'z';
		return $stream;
	}

	function writeResource($handle){
		$type = get_resource_type($handle);
		$stream = '';
		if($type == 'file' || $type == 'stream'){
			while (!feof($handle)) {
				$content = fread($handle, 32768);
				$tag = 'b';
				if(feof($handle))
					$tag = 'B';
				$size = count(str_split($content));
				//$stream .= $tag . pack('n',strlen($content));
				$stream .= $tag . pack('n', $size);
				$stream .= $content;
			}
			fclose($handle);
			return $stream;
		} else {
			throw new HessianParsingException("Cannot handle resource of type '$type'");	
		}
	}
	
	
	
}