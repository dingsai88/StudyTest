<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

include_once 'Hessian2Writer.php';

class Hessian2ServiceWriter extends Hessian2Writer{

	// web services
	
	function writeCall($method, $params = array()){
		$this->logMsg("call $method");
		$stream = $this->writeVersion();
		$stream .= 'C';
		$stream .= $this->writeString($method);
		$stream .= $this->writeInt(count($params));
		foreach($params as $param){
			$stream .= $this->writeValue($param);
		}
		return $stream;
	}
	
	function writeFault(Exception $ex, $detail = null){
		$this->logMsg("fault");
		$stream = $this->writeVersion();
		$stream .= 'F';
		$arr['message'] = $ex->getMessage();
		$arr['code'] = $ex->getCode();
		$arr['file'] = $ex->getFile();
		$arr['trace'] = $ex->getTraceAsString();
		$arr['detail'] = $detail;
		$stream .= $this->writeMap($arr);
		return $stream;
	}
	
	function writeReply($value){
		$this->logMsg("reply");
		$stream = $this->writeVersion();
		$stream .= 'R';
		$stream .= $this->writeValue($value);
		return $stream;
	}
	
	function writeVersion(){
		return "H\x02\x00";
	}

}