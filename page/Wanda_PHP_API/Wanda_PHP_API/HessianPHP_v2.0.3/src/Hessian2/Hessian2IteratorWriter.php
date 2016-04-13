<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Special writer for classes derived from Iterator. Hessian 2 protocol
 * Resolves and writes either a list or a map using the type information optionally.
 * @author vsayajin
 *
 */
class Hessian2IteratorWriter{
	var $usetype;
	
	function isIterator($object){
		return is_object($object) && ($object instanceof Iterator);
	} 
	
	function write(Iterator $list, $writer){
		$writer->logMsg('iterator writer');
		$writer->refmap->objectlist[] = $list; 
		
		$total = $this->getCount($list);
		$class = get_class($list);
		$type = $writer->typemap->getRemoteType($class);
		
		$mappedType = $type ? $type : $class; // OJO con esto
		
		$islist = HessianUtils::isListIterate($list);
		if($islist){
			list($stream, $terminate) = $this->listHeader($writer, $mappedType, $total);
			foreach($list as $value){
				$stream .= $writer->writeValue($value);
			}
			if($terminate)
				$stream .= 'Z';
		} else {
			if($this->usetype && $mappedType){
				$stream = 'M';
				$stream .= $writer->writeType($mappedType);
			} else {
				$stream = 'H';
			}
			
			foreach($elements as $key => $value){
				$stream .= $writer->writeValue($key);
				$stream .= $writer->writeValue($value);
			}
			$stream .= 'Z';
		}
		return new HessianStreamResult($stream);
	}

	function listHeader($writer, $type, $total = false){
		$stream = '';
		$terminate = false;
		if($this->usetype && $type){ // typed
			if($total !== false){ // typed fixed length
				$stream = 'V';
				$stream .= $writer->writeType($type);
				$stream .= $writer->writeInt($total);
			} else { // typed variable length
				$stream = "\x55"; 
				$stream .= $writer->writeType($type);
				$terminate = true;
			}
		} else { // untyped
			if($total !== false){ //untyped fixed length
				$stream = "\x58"; 
				$stream .= $writer->writeInt($total);
			} else { // untyped variable length
				$stream = "\x57"; 
				$terminate = true;
			}
		}
		
		return array($stream, $terminate);
	}
	
	function getCount($list){
		if($list instanceof Countable)
			return count($list);
		return false;
	}
}