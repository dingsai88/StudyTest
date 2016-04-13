<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

include_once 'Hessian1Parser.php';

class Hessian1ServiceParser extends Hessian1Parser{

	public static function detectVersion($stream){
		$head = $stream->peek(1, 0);
		if($head == 'f')
			return true;
		$head = $stream->peek(3, 0);
		return $head == "c\x01\x00" || $head == "r\x01\x00";
	}
	
	function parseTop(){
		$code = $this->read();
		$value = null;
		switch($code){
			case 'r':
				$version = $this->read(2);
				$value = $this->parseReply();
				break;
			case 'f':
				$value = $this->parseFault();
				break;
			case 'c':
				$version = $this->read(2);
				$value = $this->parseCall();
				break;
			default:
				throw new HessianParsingException("Unrecognized code $code at the start of the stream");
		}
		return $value;
	}
	
	function parseCall(){
		$this->parseHeaders();
		$code = $this->read();
		if($code != 'm') {
			throw new HessianParsingException('Hessian Parser, Malformed call: Expected m'); 
		}
		$call = new HessianCall();
		$call->method = $this->parseString($code, ord($code));
		$end = false;
		do{
			$code = $this->read();
			if($code == 'z')
				$end = true;
			else
				$call->arguments[] = $this->parseCheck($code);
		} while(!$end);
		return $call;
	}
	
	function parseReply(){
		$this->parseHeaders();
		$code = $this->read(1);
		if($code == 'f'){
			$value = $this->parseFault();
		} else
			$value = $this->parseCheck($code);
		if($this->read(1) == 'z')
			return $value;
	}
	
	function parseFault(){
		$code = $this->read(1);
		while($code != 'z'){
			$key = $this->parse($code);
			$value = $this->parse();
			$map[$key] = $value;
			$code = $this->read(1);
		}
		$fault = new HessianFault($map['message'], $map['code'], $map);
		throw $fault;
	}

	function parseHeaders(){
		$code = $this->stream->peek();
		if($code != 'H')
			return;
		throw new Exception('Headers currently not supported');			
		// TODO headers
	}
	
}