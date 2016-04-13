<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Special writer for classes derived from Iterator. Hessian 1 protocol
 * Resolves and writes either a list or a map using the type information optioally.
 * @author vsayajin
 *
 */
class Hessian1IteratorWriter{
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
		
		$islist = HessianUtils::isListIterate($list);
		if($islist){
			$stream = 'V';
			if($this->usetype && $type)
				$stream .= $writer->writeType($type);
			if($total !== false)
				$stream .= $writer->writeInt($total);
			foreach($list as $value){
				$stream .= $writer->writeValue($value);
			}
			$stream .= 'z';
		} else {
			$stream = 'M';
			if($this->usetype){
				$mapType = $type ? $type : $class;
				$stream .= $writer->writeType($mapType);
			}
			foreach($elements as $key => $value){
				$stream .= $writer->writeValue($key);
				$stream .= $writer->writeValue($value);
			}
			$stream .= 'z';
		}
		return new HessianStreamResult($stream);
	}

	function getCount($list){
		if($list instanceof Countable)
			return count($list);
		return false;
	}
}