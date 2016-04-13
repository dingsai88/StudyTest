<?php

include_once('../src/HessianService.php');

class ParamObject{
	var $test = 'Hola hessian';
	var $stringVar = 'vaca';
	var $hashVar;
}

class HessianTest{

	function testEcho($value){
		return $value;
	}

	function testStringToBoolean($param){
		return (bool)$param;
	}

	function testConcatString($param1,$param2){
		return $param1.$param2;
	}

	function testStringToLong($param){
		return floatval($param);
	}

	function testStringToDouble($param){
		return floatval($param);
	}

	function testStringToShort($param){
		return intval($param);
	}

	function testStringToInt($param){
		return intval($param);
	}

	function testStringToFloat($param){
		return floatval($param);
	}

	function testStringToByte($param){
		return intval($param);
	}

	function testIntToString($param){
		return strval($param);
	}

	function testDoubleToString($param){
		return strval($param);
	}

	function testBoolToString($param){
		return strval($param);
	}

	function testBoolToStringStrict($param){
		if($param===true)
			return 'true';
		return 'false';
	}

	function testCharToString($param){
		return strval($param);
	}

	function testIntArrToString($param){
		$result = array();
		foreach($param as $num){
			$result[] = strval($num);
		}
		return $result;
	}

	function testStringArrToInt($param){
		$result = array();
		foreach($param as $num){
			$result[] = intval($num);
		}
		return $result;
	}

	function testDoubleArrToString($param){
		$result = array();
		foreach($param as $num){
			$result[] = strval($num);
		}
		return $result;
	}

	function testStringArrToDouble($param){
		$result = array();
		foreach($param as $num){
			$result[] = floatval($num);
		}
		return $result;
	}

	function testHashMap($keys,$values){
		$map = array();
		for($i = 0; $i<count($keys); $i++)
		{
			$key = $keys[$i];
			$map[$key] = $values[$i];
		}
		return $map;
	}

	function testHashMapParam($param){
		$result = '';
		foreach($param as $key=>$value){
			$result .= $key.' '.$value;
		}
		return $result;
	}

	function testParamObject($param){
		$obj = new ParamObject();
		$map = array();
		if(empty($param)){
			$obj->stringVar = "ParamObject was empty";
			$map["Message"] = "No Message";
		} else {
			$obj->stringVar = "ParamObject not empty";
			$map["Message"] = $param->stringVar;
		}
		$obj->hashVar = $map;
		return $obj;
	}

	function testTypedObject(ParamObject $obj){
		return $obj;
	}

	function testSendParamObject($param){
		return $param->stringVar;
	}

	function testReceiveParamObject($param){
		$obj = new ParamObject();
		$obj->stringVar = $param;
		return $obj;
	}

	function testArrayListParam($param){
		$result = '';
		foreach($param as $key){
			$result .= $key.' ';
		}
		return $result;
	}

	function testArrayList($param){
		$result = array();
		foreach($param as $num){
			$result[] = $num;
		}
		return $result;
	}

	function testList($param){
		return count($param);
	}

	function testStringToDate($dateISO){
		$dateobj = new DateTime($dateISO);
		return $dateobj;
	}

	function testDateToString($dateTime){
		return $dateTime->format('Y-m-d H:i:s');
	}
	
	function testFault(){
		throw new Exception("Test Fault");
	}
	
	function testSendFile($filename, $content){
		if(file_exists($filename))
			@unlink($filename);
		file_put_contents($filename, $content);		
	}
	
	function testReceiveFile(){
		$file = dirname(__FILE__).'/notok.png';
		return fopen($file, 'r');		
	}
}

$testService = new HessianTest();

$server = new HessianService($testService, array('displayInfo' => true));
$server->handle();

?>