<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

include_once 'Hessian2Parser.php';

class Hessian2ServiceParser extends Hessian2Parser{
		
	public static function detectVersion($stream){
		$version = $stream->peek(3, 0);
		return $version == "H\x02\x00";
	}
	
	function parseTop(){
		$this->logMsg('Parsing top element');
		$this->parseVersion();
		$code = $this->read();
		$value = null;
		switch($code){
			case 'R':
				$value = $this->parseReply();
				break;
			case 'C':
				$value = $this->parseCall();
				break;
			case 'F':
				$value = $this->parseFault();
				break;
			case 'E':
				$value = $this->parseEnvelope();
				break;
			default:
				throw new HessianFault("Code $code not recognized as a top element");
		}
		return $value;
	}
	
	function parseVersion(){
		$version = $this->read(3);
	}
	
	function parseReply(){
		$this->logMsg('Parsing reply');
		return $this->parse(); 
	}
	
	function parseCall(){
		$this->logMsg('Parsing call');
		$call = new HessianCall();
		$call->method = $this->parse(null, 'string');
		$num = $this->parse(null, 'integer');
		for($i=0;$i<$num;$i++){
			$call->arguments[] = $this->parseCheck();
		}
		return $call;
	}
	
	function parseFault(){
		$this->logMsg('Parsing fault');
		$map = $this->parse(null, 'map');
		$fault = new HessianFault($map['message'], $map['code'], $map);
		throw $fault;
	}
	
	function parseEnvelope(){
		throw new Exception('Envelopes currently not supported');
	}
	
	function parsePacket(){
		throw new Exception('Packetc currently not supported');
	}
		
}