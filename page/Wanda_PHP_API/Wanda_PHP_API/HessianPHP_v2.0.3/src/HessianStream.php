<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * Represents a stream of bytes used for reading
 * It doesn't use any of the string length functions typically used
 * for files because if can cause problems with encodings different than latin1
 * @author vsayajin
 */
class HessianStream{
	public $pos = 0;
	public $len;
	public $bytes = array();
	
	function __construct($data = null, $length = null){
		if($data)
			$this->setStream($data, $length);
	}
	
	function setStream($data, $length = null){
		$this->bytes = str_split($data);
		$this->len = count($this->bytes);
		$this->pos = 0;
	}
	
	public function peek($count = 1, $pos = null){
		if($pos == null)
			$pos = $this->pos;
		
		$portion = array_slice($this->bytes, $pos, $count);
		return implode($portion);
	}

	public function read($count=1){
		if($count == 0)
			return;
		$portion = array_slice($this->bytes, $this->pos, $count);
		$read = count($portion);
		$this->pos += $read;
		if($read < $count) {
			if($this->pos == 0)
				throw new Exception('Empty stream received!');
			else
				throw new Exception('read past end of stream: '.$this->pos);
		}
		return implode($portion);
	}
	
	public function readAll(){
		$this->pos = $this->len;
		return implode($this->bytes);		
	}

	public function write($data){
		$bytes = str_split($data);
		$this->len += count($bytes);
		$this->bytes = array_merge($this->bytes, $bytes);
	}
 
	public function flush(){}
	
	public function getData(){
		return implode($this->bytes);
	}
	
	public function close(){		
	}
}


