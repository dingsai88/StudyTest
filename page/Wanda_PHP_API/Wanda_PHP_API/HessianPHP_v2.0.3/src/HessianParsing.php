<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Represents a parsing rule with a type and a calling function
 * @author vsayajin
 */
class HessianParsingRule{
	var $type;
	var $func;
	var $desc;
	
	function __construct($type = '', $func = '', $desc = ''){
		$this->type = $type;
		$this->func = $func;
		$this->desc = $desc;
	}
}

/**
 * Contains the sequence of rules and start symbols that match the rules.
 * Resolves a rule based on a symbol and optionally checks for expected outcomes; 
 * @author vsayajin
 */
class HessianRuleResolver{
	public $rules = array();
	public $symbols = array();

	function __construct($rulesFile){
		if($rulesFile)
			$this->loadRulesFromFile($rulesFile);
	}
	
	/**
	 * Takes a symbol and resolves a parsing rule to apply. Optionally it can
	 * check if the type resolved matches an expected type 
	 * @param string/int $symbol
	 * @param string $typeExpected
	 * @return HessianParsingRule rule to evaluate
	 */
	function resolveSymbol($symbol, $typeExpected = ''){
		$num = ord($symbol);
		if(!isset($this->symbols[$num]))
			throw new HessianParsingException("Code not recognized: 0x".dechex($num));
		$ruleIndex = $this->symbols[$num]; 
		$rule = $this->rules[$ruleIndex];
		if($typeExpected){
			if(!$this->checkType($rule, $typeExpected))
				throw new HessianParsingException("Type $typeExpected expected");
		}
		return $rule;
	}
	
	function checkType($rule, $types){
		$checks = explode(',', $types);
		foreach($checks as $type){
			if($rule->type == trim($type))
				return true;	
		}
		return false;
	}
	
	function loadRulesFromFile($file){
		//if(!file_exists($file))
		//	throw new HessianParsingException("Could not load parsing rules from file $file");
		include_once $file;
		$this->rules = $rules;
		$this->symbols = $symbols;
	}
}

/**
 * Interface used in parsers that need to ignore the incoming parsing value and continue
 * with the next in the stream
 * @author vsayajin
 */
interface HessianIgnoreCode{}

/**
 * Hold information on declared classes in the incoming payload
 * @author vsayajin
 */
class HessianClassDef implements HessianIgnoreCode{
	var $type;
	var $remoteType;
	var $props = array();
}

/**
 * Results from parsing a call to a local object
 * @author vsayajin
 *
 */
class HessianCall{
	var $method;
	var $arguments = array();
	
	function __construct($method='', $arguments=array()){
		$this->method = $method;
		$this->arguments = $arguments;
	}
}

/**
 * Represents an index to a reference. This hack is necessary for handling arrays
 * references
 * @author vsayajin
 *
 */
class HessianRef{
	var $index;
	
	static function isRef($val){
		return $val instanceof HessianRef;
	}
	
	static function getIndex($list){
		return new HessianRef($list);
	}
		
	function __construct($list){
		if(is_array($list))
			$this->index = count($list) - 1;
		else $this->index = $list;
	}
}

/**
 * Used by custom write filters to return a stream instead of a modified object 
 * @author vsayajin
 */
class HessianStreamResult {
	var $stream;
	function __construct($stream){
		$this->stream = $stream;
	}
}