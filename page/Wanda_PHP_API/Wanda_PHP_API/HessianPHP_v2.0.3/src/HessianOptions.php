<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Configuration options for use in Hessian services, both client and server
 */
class HessianOptions{
	public $version = 2;
	/**
	 * Name of the transport to use
	 * @var string
	 */
	public $transport = 'CURL';
	/**
	 * Array of custom options to pass to the transport object
	 * @var array
	 */
	public $transportOptions;
	public $detectVersion = false;
	public $objectFactory;
	public $typeMap = array();
	public $strictTypes = false;
	public $headers = array();
	public $interceptors = array();
	public $timeZone;
	public $saveRaw = false;
	
	public $serviceName = '';
	public $displayInfo = false;
	public $ignoreOutput = false;	
	
	public $parseFilters = array();
	public $writeFilters = array();
	
	public $before = array();
	public $after = array();
	/**
	 * Takes an array and matches the corresponding properties in this object
	 * @param array $arr
	 */
	public function fromArray(array $arr){
		$vars = get_class_vars('HessianOptions');
		foreach($arr as $key=>$value){
			if(array_key_exists($key, $vars)){
				$this->$key = $value;
			}
		}
	}
	
	/**
	 * Tries to resolve a HessianOptions object from either an object or an array.
	 * Always return an options object
	 * @param mixed $object variable to resolve
	 * @return HessianOptions
	 */
	public static function resolveOptions($object){
		$options = new HessianOptions();
		if($object == null)
			return $options;
		if($object instanceof HessianOptions)
			return $object;
		elseif(is_array($object))
			$options->fromArray($object);
		elseif(is_object($object)){
			$arr = (array)$object;
			$options->fromArray($arr);
		}
		return $options;
	}
}