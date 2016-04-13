<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

include_once 'HessianInterfaces.php';
include_once 'HessianExceptions.php';
include_once 'HessianParsing.php';
include_once 'HessianOptions.php';
include_once 'HessianUtils.php';
include_once 'HessianCallbackHandler.php';
include_once 'HessianReferenceMap.php';
include_once 'HessianTypeMap.php';
include_once 'HessianStream.php';

define('HESSIAN_PHP_VERSION', '2.0');

/**
 * Default implementation of an object factory 
 */
class HessianObjectFactory implements IHessianObjectFactory{
	var $options;
	public function setOptions(HessianOptions $options){
		$this->options = $options;
	}
	
	public function getObject($type){
		if(!class_exists($type)) {
			if(isset($this->options->strictType) && $this->options->strictType)
				throw new Exception("Type $type cannot be found for object instantiation, check your type mappings");
			$obj = new stdClass();
			$obj->__type = $type;
			return $obj;
		}
		return new $type();
	}
}

/**
 * Default Datetime adapter that works with the built-in Datetime class of PHP5
 * @author vsayajin
 */
class HessianDatetimeAdapter{
	public static function toObject($ts, $parser){
		$date = date('c', $ts);
		//$date = gmdate('c', $ts);
		return new Datetime($date);	
	}
	
	public static function writeTime($date, $writer){
		$ts = $date->format('U');
		$stream = $writer->writeDate($ts);
		return new HessianStreamResult($stream);
	}
} 

/**
 * Handles que creation of components for assembling Hessian clients and servers
 * It contains the basic assembly configuration for these components.
 * @author vsayajin
 *
 */
class HessianFactory{
	var $protocols = array();
	var $transports = array();
	static $cacheRules = array();
	
	function __construct(){
		$this->protocols = array(
			'2'=>array($this,'loadVersion2'), '1'=>array($this,'loadVersion1')
		);
		$this->transports = array(
			'CURL' => 'HessianCURLTransport',
			'curl' => 'HessianCURLTransport',
			'http' => 'HessianHttpStreamTransport'
		);
	}
	
	/**
	 * Returns a specialized HessianParser object based on the options object
	 * @param HessianStream $stream input stream
	 * @param HessianOptions $options configuration options
	 */
	function getParser($stream, $options){
		$version = $options->version;
		if($options->detectVersion && $stream)
			$version = $this->detectVersion($stream, $options);
		$callback = $this->getConfig($version);
		$parser = call_user_func_array($callback, array('parser', $stream, $options));
		if($options->objectFactory instanceof IHessianObjectFactory){
			$parser->objectFactory = $options->objectFactory;
		} else {
			$parser->objectFactory = new HessianObjectFactory();
		}
		return $parser;
	}
	
	/**
	 * Returns a specialized HessianWriter object based on the options object
	 * @param HessianStream $stream output stream
	 * @param HessianOptions $options configuration options
	 */
	function getWriter($stream, $options){
		$version = $options->version;
		if($options->detectVersion && $stream)
			$version = $this->detectVersion($stream, $options);
		$callback = $this->getConfig($version);
		$writer = call_user_func_array($callback, array('writer', $stream, $options));
		return $writer;
	}
	
	/**
	 * Creates a parsing helper object (rules resolver) that uses a protocol
	 * rule file to parse the incomin stream. It caches the rules for further
	 * use.
	 * @param Integer $version Protocol version
	 * @param array $config local component configuration
	 */
	public function getRulesResolver($version, $rulesPath=''){
		if(isset(self::$cacheRules[$version]))
			return self::$cacheRules[$version];
		$resolver = new HessianRuleResolver($rulesPath);
		self::$cacheRules[$version] = $resolver;
		return $resolver;
	}
	
	/**
	 * Receives a stream and iterates over que registered protocol handlers
	 * in order to detect which version of Hessian is it
	 * @param HessianStream $stream
	 * @return integer Protocol version detected
	 */
	function detectVersion($stream, $options){
		foreach($this->protocols as $version => $callback){
			$res = call_user_func_array($callback, array('detect', $stream, $options));
			if($res)
				return $version;		
		}
		throw new Exception("Cannot detect protocol version on stream");
	}
	
	function getConfig($version){
		if(!isset($this->protocols[$version]))
			throw new Exception("No configuration for version $version protocol");
		return $this->protocols[$version];
	}
	
	function getTransport(HessianOptions $options){
		$type = $options->transport;
		if(is_object($type))
			return $type;
		if(!isset($this->transports[$type]))
			throw new HessianException("The transport of type $type cannot be found");
		$class = $this->transports[$type];
		$trans = new $class();
		$trans->testAvailable();
		return $trans; 
	}
	
	function loadVersion2($mode, $stream, $options){
		if($mode == 'parser'){
			include_once 'Hessian2/Hessian2ServiceParser.php';
			$resolver = $this->getRulesResolver(2, 'Hessian2/hessian2rules.php');
			$parser = new Hessian2ServiceParser($resolver, $stream, $options);
			$filters['date'] = array('HessianDatetimeAdapter','toObject');
			$filters = array_merge($filters, $options->parseFilters);
			$parser->setFilters(new HessianCallbackHandler($filters));
			return $parser;
		}
		if($mode == 'writer'){
			include_once 'Hessian2/Hessian2ServiceWriter.php';
			include_once 'Hessian2/Hessian2IteratorWriter.php';
			$writer = new Hessian2ServiceWriter($options);
			$filters['@DateTime'] = array('HessianDatetimeAdapter','writeTime');
			$filters['@Iterator'] = array( new Hessian2IteratorWriter(), 'write');
			$filters = array_merge($filters, $options->writeFilters);
			$writer->setFilters(new HessianCallbackHandler($filters));
			return $writer;
		}
		if($mode == 'detect'){
			include_once 'Hessian2/Hessian2ServiceParser.php';
			return Hessian2ServiceParser::detectVersion($stream);
		}
	}
	
	function loadVersion1($mode, $stream, $options){
		if($mode == 'parser'){
			include_once 'Hessian1/Hessian1ServiceParser.php';
			$resolver = $this->getRulesResolver(1, 'Hessian1/hessian1rules.php');
			$parser = new Hessian1ServiceParser($resolver, $stream, $options);
			$filters['date'] = array('HessianDatetimeAdapter','toObject');
			$filters = array_merge($filters, $options->parseFilters);
			$parser->setFilters(new HessianCallbackHandler($filters));
			return $parser;
		}
		if($mode == 'writer'){
			include_once 'Hessian1/Hessian1ServiceWriter.php';
			include_once 'Hessian1/Hessian1IteratorWriter.php';
			$writer = new Hessian1ServiceWriter($options);
			$filters['@DateTime'] = array('HessianDatetimeAdapter','writeTime');
			$filters['@Iterator'] = array( new Hessian1IteratorWriter(), 'write');
			$filters = array_merge($filters, $options->writeFilters);
			$writer->setFilters(new HessianCallbackHandler($filters));
			return $writer;
		}
		if($mode == 'detect'){
			include_once 'Hessian1/Hessian1ServiceParser.php';
			return Hessian1ServiceParser::detectVersion($stream);
		}
	}
}




