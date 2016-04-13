<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * General container for type, class definition and object/array references used in parsers and writers 
 * @author vsayajin
 */
class HessianReferenceMap{
	var $reflist = array();
	var $typelist = array();
	var $classlist = array();
	var $objectlist = array();
	
	function incReference($obj = null){
		$this->reflist[] =  new HessianRef(count($this->objectlist));
		if($obj != null)
			$this->objectlist[] = $obj;
	}
	
	function getClassIndex($class){
		foreach($this->classlist as $index => $classdef){
			if($classdef->type == $class)
				return $index;
		}
		return false;
		//return array_search($class, $this->classlist);
	}
	
	function addClassDef(HessianClassDef $classdef){
		$this->classlist[] = $classdef;
		return count($this->classlist) - 1;
	}
	
	function getReference($object){
		return array_search($object, $this->objectlist, true);
	}
	
	function getTypeIndex($type){
		return array_search($type, $this->typelist, true);
	}

	function reset(){
		$this->objectlist = 
			$this->reflist = 
			$this->typelist = 
			$this->classlist = array();
	}
	
}