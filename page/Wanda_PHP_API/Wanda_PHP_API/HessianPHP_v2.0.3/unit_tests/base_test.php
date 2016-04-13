<?php

require_once(dirname(__FILE__) . '/simpletest/autorun.php');

include_once '../src/HessianClient.php';

function baseURL(){
	$local = 'http://localhost';
	$port = $_SERVER['SERVER_PORT'];
	if($port != '80')
		$local .= ":$port";
	$local .= dirname($_SERVER['PHP_SELF']).'/';
	return $local;
}

class ParamObject{
	var $test = 'Hola hessian';
	var $stringVar = 'vaca';
	var $hashVar;
}

class Interceptor implements IHessianInterceptor{
	var $base;
	function __construct(){
		$this->base = dirname(__FILE__).'/logs/';
	}
	
	function beforeRequest(HessianCallingContext $ctx){
		$ctx->options->saveRaw = true;
	}
	
	function afterRequest(HessianCallingContext $ctx){
		file_put_contents($this->base.'payload.bin', $ctx->payload);
		$this->writeAll($ctx);
	}
	
	function writeAll($ctx){
		file_put_contents($this->base.'parserLog.txt', implode("\n", $ctx->parser->log));
		file_put_contents($this->base.'writerLog.txt', implode("\n", $ctx->writer->log));
		file_put_contents($this->base.'result.bin', $ctx->transport->rawData);
	}
	
	function clear(){
		@unlink($this->base.'payload.bin');
		@unlink($this->base.'parserLog.txt');
		@unlink($this->base.'writerLog.txt');
		@unlink($this->base.'result.bin');
	}
}

/**
* Base class for unit tests of the protocol features. It uses a local php service
to perform the remote calls.
*/
abstract class BaseHessianTests extends UnitTestCase {
	var $version = 2;
	var $proxy;
	var $url;

	function setUp() {
		$options = new HessianOptions();
		$options->version = $this->version;
		$this->url = baseURL().'php_test_server.php';
		//echo "Testing in url $this->url<br>";
        $this->proxy = new HessianClient($this->url, $options);
    }
    
    function tearDown() {}

	// Tests if sent and received values are equal
	function testEcho(){
		$values = array(
			555.00, 666.00, 102456.5646, 'Hello', 'Ámbito', 546546, false, true
		);
		foreach($values as $value){
			$ret = $this->proxy->testEcho($value);
			$this->assertEqual($ret, $value);	
		}
	}

	// tests simple strings
	function testConcatString(){
		$str = $this->proxy->testConcatString("hello"," hessianphp");
		$this->assertEqual($str, "hello hessianphp");
	}

	// tests unicode strings
	function testConcatStringUnicode(){
		try{
			$expected = "áé";
			$str = $this->proxy->testConcatString("á","é");
			//var_dump($str);
			//var_dump($expected);
			$this->assertEqual($str, $expected);
		}catch(Exception $e){
			echo '<pre>';
			print_r($e);
			echo '</pre>';
			throw $e;
		}
	}

	function testStringToLong() { 
		$val = $this->proxy->testStringToLong('5124567855432488'); 
		$this->assertEqual($val , 5124567855432488);
	}
	function testStringToBoolean() {
		// fails with other values, works only with 'true' and 'false'
		$bool = $this->proxy->testStringToBoolean('true'); 
		$this->assertEqual($bool,true);
	}
	function testStringToDouble() {
		// Different format for .net
		$double = $this->proxy->testStringToDouble('545.54'); 
		$this->assertEqual($double,545.54);
	}
	function testStringToShort() { 
		$short = $this->proxy->testStringToShort('17');
		$this->assertEqual($short,17);
	}
	function testStringToInt(){
		$int = $this->proxy->testStringToInt('17');
		$this->assertEqual($int,17);
	}
	function testStringToFloat(){
		// Different format for .net
		$float = $this->proxy->testStringToFloat('0.333333'); 
		$this->assertEqual($float,0.333333);
	} 
	function testStringToByte(){
		// Just works with integers and returns integers
		$byte = $this->proxy->testStringToByte("01");
		$this->assertEqual($byte , 1);
	} 

	function testIntToString(){
		$str = $this->proxy->testIntToString(83);
		$this->assertEqual($str , '83');
	}
	function testDoubleToString(){
		$str = $this->proxy->testDoubleToString(123.4);
		$this->assertEqual($str , '123.4');
	}
	function testBoolToString(){  
		$str = $this->proxy->testBoolToString(true);
		$this->assertEqual($str , '1'); // returns 1 or 0, not true or false
		$str = $this->proxy->testBoolToStringStrict(false);
		$this->assertEqual($str , 'false'); 
	}

	function testCharToString(){
		// the ascii code of the char
		$str = $this->proxy->testCharToString(65);
		$this->assertEqual($str , '65'); 
	} 

	function testIntArrToString(){  
		$arr = $this->proxy->testIntArrToString(array(1,2,3,4));
		$stringArr = array('1','2','3','4');
		$this->assertEqual($arr , $stringArr); 
	} 
	function testStringArrToInt(){  
		// only integers or mixed strings/integers
		$arr = $this->proxy->testStringArrToInt(array('1',2,'3',4));
		$intArr = array(1,2,3,4);
		$this->assertEqual($arr , $intArr); 
	} 

	function testDoubleArrToString(){  
		$arr = $this->proxy->testDoubleArrToString(array(0.1,0.2,0.3,5.4));
		$doubleArr = array('0.1','0.2','0.3','5.4');
		$this->assertEqual($arr , $doubleArr); 
	} 

	function testStringArrToDouble(){ 
		// same as above
		$arr = $this->proxy->testStringArrToDouble(array(0.1,'0.2',0.3,'5.4'));
		$doubleArr = array(0.1,0.2,0.3,5.4);
		$this->assertEqual($arr , $doubleArr); 
	} 

	function testHashMap(){ 
		// switches order of indexes
		$map = $this->proxy->testHashMap(array('A','B','C'), array(1,2,3));
		$testMap = array('A' => 1,'B' => 2,'C' => 3);
		$this->assertEqual($map , $testMap); 
	} 

	function testHashMapParam(){
		// same as above
		$string = $this->proxy->testHashMapParam(array('A' => 1,'B'=>2,'C'=>3)); 
		$testString = 'A 1B 2C 3';
		$this->assertEqual($string , $testString); 
	} 

	function testNullParamObject(){  
		$obj = $this->proxy->testParamObject(null); 
		$this->assertNotNull($obj);
		$this->assertEqual($obj->stringVar , "ParamObject was empty");
		//$this->assertEqual($obj->hashVar['Message'] , "No Message");
	}
	
	function testParamObject(){  
		$obj = $this->proxy->testParamObject(new ParamObject()); 
		$this->assertNotNull($obj);
		$this->assertEqual($obj->stringVar , "ParamObject not empty");
		//$this->assertEqual($obj->hashVar['Message'] , "vaca");
	}

	function testSendParamObject(){  
		$str = $this->proxy->testSendParamObject(new ParamObject());
		$this->assertEqual($str , "vaca");
	}

	function testReceiveParamObject(){  
		$obj = $this->proxy->testReceiveParamObject('burr'); 
		$this->assertNotNull($obj);
		$this->assertEqual($obj->stringVar , "burr");
	}

	function testArrayListParam(){  
		// as arraylist
		$list = $this->proxy->testArrayListParam(array(1,2,3,4,5));
		$this->assertEqual($list,'1 2 3 4 5 ');
	} 

	function testArrayList(){  
		// doesn't seem to care what I send in the array
		$sendList = array(1,'2',3,4.5,5);
		$respList = $list = $this->proxy->testArrayList($sendList);
		$this->assertEqual($sendList, $respList);
	} 

	function testList(){
		// as IList (.net) / List (Java)
		$count = $this->proxy->testList(array(1,'2'));
		$this->assertEqual($count, 2);
	} 

	function testEmptyList(){
		// as IList (.net) / List (Java)
		$count = $this->proxy->testList(array());
		$this->assertEqual($count, 0);
	} 

	function testStringToDate(){
		//var_dump($dt->format(DATE_ATOM)); 
		$time = 'H:i:s';
		$date = 'Y-m-d';
		$dt = $this->proxy->testStringToDate('2005-12-27 20:30:15');
		$this->assertTrue($dt instanceof DateTime);
		$this->assertEqual('20:30:15', $dt->format($time));
		$this->assertEqual('2005-12-27', $dt->format($date));
		
		$dt = $this->proxy->testStringToDate('2009-12-20 21:14');
		$this->assertTrue($dt instanceof DateTime);
		$this->assertEqual('21:14', $dt->format('H:i'));
		$this->assertEqual('2009-12-20', $dt->format($date));

	}

	function testDateToString(){
		// .NET: Returns incorrect information
		$dt1 = new DateTime('1998-05-08 02:51:31');
		$string1 = $this->proxy->testDateToString($dt1);
		$this->assertEqual($string1,'1998-05-08 02:51:31');
				
		$dt2 = new DateTime('1970-01-01 12:00:01');
		$string2 = $this->proxy->testDateToString($dt2);
		$this->assertEqual($string2,'1970-01-01 12:00:01');
		
		$dt3 = new DateTime('2006-11-14 11:16:44');
		$string3 = $this->proxy->testDateToString($dt3);
		$this->assertEqual($string3,'2006-11-14 11:16:44');
	}
	
	function testCurlTransport(){
		$options = new HessianOptions();
		$options->transport = "CURL";
		$options->version = $this->version;
		$this->proxy = new HessianClient($this->url, $options); 
		$this->testConcatString();		
	}
	
	function testHttpStreamTransport(){
		$options = new HessianOptions();
		$options->transport = "http";
		$options->version = $this->version;
		$this->proxy = new HessianClient($this->url, $options); 
		$this->testConcatString();		
	}
	
	function testInterceptor(){
		$interceptor = new Interceptor();
		$interceptor->clear();

		$options = new HessianOptions();
		$options->interceptors = array($interceptor);
		$options->version = $this->version;
		
		$this->proxy = new HessianClient($this->url, $options);
		$str = $this->proxy->testConcatString("hello"," hessianphp");
		
		$file = dirname(__FILE__).'/logs/payload.bin';
		$this->assertTrue(file_exists($file));
	}
	
	function testFault(){
		try{
			$this->proxy->testFault();
			$this->fail("Fault expected");
		} catch(Exception $e){
			$this->pass();
		}
	}
	
	function testSendFile(){
		$fp = fopen(dirname(__FILE__).'/ok.png', "r");
		$this->proxy->testSendFile('ok2.png', $fp);
		$this->assertTrue(file_exists(dirname(__FILE__).'/ok2.png'));		
	}
	
	function testReceiveFile(){
		$filename = dirname(__FILE__).'/notok.png';
		$size = filesize($filename);
		$bytes = $this->proxy->testReceiveFile();
		// feo truco para contar en bytes
		$totalbytes = count(str_split($bytes));
		$this->assertTrue($totalbytes == $size); //strlen($bytes)
	}
}

