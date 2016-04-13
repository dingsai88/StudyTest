<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Represents an error while parsing an input stream
 * @author vsayajin
 */
class HessianParsingException extends Exception {
	// TODO custom constructors
	public $position;
	public $details;
};

/**
 * Remote Exception, nuff said
 * @author vsayajin
 *
 */
class HessianFault extends Exception{
	var $detail;
	
	function __construct($message = '', $code = '', $detail = null){
		$this->message = $message;
		$this->code = $code;
		$this->detail = $detail;
	}
}

class HessianException extends Exception{}