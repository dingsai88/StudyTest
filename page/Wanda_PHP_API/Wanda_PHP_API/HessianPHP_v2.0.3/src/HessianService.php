<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

include_once 'HessianFactory.php';
include_once 'HessianBufferedStream.php';

/**
 * HessianPHP Server that wraps an object and exposes its methods remotedly
 */
class HessianService{
	var $fault = false;
	/** @var HessianOptions */
	var $options;
	var $service;
	var $reflected;	
	var $typemap;
	
	private $factory;
	
	/**
	 * Creates a new HessianService instace to serve remote requests from Hessian clients
	 * @param $serviceObject Class name or object instance
	 * @param mixed $options array or HessianOptions object
	 */
	function __construct($serviceObject, $options = null){
		$this->options = HessianOptions::resolveOptions($options);
		if($serviceObject)
			$this->registerObject($serviceObject);
		$this->options->detectVersion = true;
		$this->typemap = new HessianTypeMap($this->options->typeMap);
		$this->factory = new HessianFactory();
		
		// timezones
		HessianUtils::setTimeZone($this->options->timeZone);
	}
	
	/**
	 * Registers either a class or an object instance to be served
	 * @param mixed $service Class name or instance
	 */
	public function registerObject($service){
		$name = 'HessianPHP_Service';
		if(is_object($service)){
			$name = get_class($service);
			$this->service = $service;
		}
		if(is_string($service)){
			$name = $service;
			//TODO use object factory
			$this->service = new $service();
		}
		if(!is_object($this->service))
			throw new Exception("Error registering service object");
		$this->reflected = new ReflectionObject($this->service);
		if($this->options->serviceName == '')
			$this->options->serviceName = $name;
	}
	
	/**
	 * Takes an exception and writes a fault stream or shows the error if no writer available
	 * @param Exception $ex
	 * @param HessianWriter $writer
	 * @return string Hessian stream representing a fault
	 */
	protected function writeFault(Exception $ex, $writer){
		header("HTTP/1.1 500 Hessian service error");
		$exDump = $ex->getMessage()."\n".$ex->getTraceAsString();
		$detail = null;
		if($ex instanceof HessianFault)
			$detail = $ex->detail;
		if(isset($writer)){
			return $writer->writeFault($ex, $detail);
		} else{
			die($exDump);		
		}
	}
	
	public function handle(){
		if(!is_object($this->service)){
			header("HTTP/1.1 500 Hessian not configured");
			die('Serviced object not registered!');
		}
		if($_SERVER['REQUEST_METHOD'] != 'POST') {
			if($this->options->displayInfo) {
				$this->displayInfo();
				exit();
			} else {
				header("HTTP/1.1 500 Hessian Requires POST");
				die('<h1>Hessian Requires POST</h1>');
			}
		}
		
		set_error_handler('hessianServiceErrorHandler');
				
		$ph = fopen("php://input", "rb");
		$instream = new HessianBufferedStream($ph);
		$ofp = fopen("php://output","wb+");
		$outstream = new HessianBufferedStream($ofp);
		$writer = null;
		$payload = '';
		ob_start(); 
		try{
			$instream->readAll();
			$parser = $this->factory->getParser($instream, $this->options);
			$parser->setTypeMap($this->typemap);
			// cache version detection
			$writer = $this->factory->getWriter($instream, $this->options);
			$writer->setTypeMap($this->typemap);

			$ctx = new HessianCallingContext();
			$ctx->isClient = false;
			$ctx->writer = $writer;
			$ctx->parser = $parser;
			$ctx->options = $this->options;
			$ctx->typemap = $this->typemap;
			
			$call = $parser->parseTop();
			$instream->close();
			
			$ctx->call = $call;
			foreach($this->options->interceptors as $interceptor){
				$interceptor->beforeRequest($ctx);
			}
			//file_put_contents('parserLog.txt', implode("\n", $parser->log));
			$result = $this->callMethod($call->method,	$call->arguments);
			$payload = $writer->writeReply($result);
			$ctx->payload = $payload;
			foreach($this->options->interceptors as $interceptor){
				$interceptor->afterRequest($ctx);
			}
			//file_put_contents('writerLog.txt', implode("\n", $writer->log));
		} catch (Exception $ex){
			$payload = $this->writeFault($ex, $writer);
		}
		
		$stdOutput = ob_get_contents();
		ob_end_clean();
		if($stdOutput != '' && !$this->options->ignoreOutput){
			$ex = new HessianFault('Unclean output detected', 0);
			$ex->detail = $stdOutput;
			$payload = $this->writeFault($ex, $writer);	
		}
		
		if(extension_loaded("mbstring"))
			$size = mb_strlen($payload, 'latin1');
		else{
			$size = count(str_split($payload));
		}
				
		header('Content-type: application/binary');
		header('Content-length: ' . $size);
		header('Connection: close');
		$outstream->write($payload);			
		$outstream->flush();
		$outstream->close();
	}
	
	/**
	 * Shows an information page about this service and the operations it contains
	 */
	public function displayInfo(){
		if(!is_object($this->service)) { 
			echo "Service not configured!";
			return;
		}
		//$methods = get_class_methods(get_class($this->service));
		$methods = $this->reflected->getMethods();
		$methodTpl = '<li> <a href="#">#methodname</a> </li> <p>';
		$html = @file_get_contents(dirname(__FILE__).'/serviceInfo.tpl'); 
		if(!$html) {
			$html = '
				<html> <head> <title>#service Hessian Service</title></head> <body>
				<h2><strong>#service</strong></h2>
				<p>Powered by HessianPHP #version</p>
				<p>This is a list of supported operations in this service.<p>
				<ul>#methods</ul>
				<body></html>';
		}
		$methodsHtml = '';
		foreach($methods as $method){
			if($this->isMethodCallable($method)) {
				$text = $method->getName() .' (';
				$params = $method->getParameters();
				$paramText = array();
				foreach($params as $param){
					$class = $param->getClass();
					if($class)
						$paramText[] = $param->getClass()->getName().' '.$param->getName();
					else
						$paramText[] = ' '.$param->getName();
				}
				$text .= implode(',',$paramText).')';
				$methodsHtml .= str_replace('#methodname', $text , $methodTpl);
			}
		}
		$html = str_replace('#methods', $methodsHtml, $html);
		$html = str_replace('#service', ucfirst($this->options->serviceName), $html);
		$html = str_replace('#version', HESSIAN_PHP_VERSION, $html);
		echo $html;
	}

	/**
	 * Dynamically calls a method in the wrapped object passing parameters from the request
	 * and returns the result. Generates a fault if the method does not exist or cannot be invoked.
	 *  
	 * @param string method Name of the method
	 * @param array params Array of parameters to be passed
	 * @return mixed Returned value from the service or null if fault
	 * @access protected 
	 **/
	protected function callMethod($method, $params){
		try {
			$methodObj = $this->reflected->getMethod($method);
			if($this->isMethodCallable($methodObj)){
				return call_user_func_array(array($this->service,$method),$params);
			}
			else 
				throw new Exception("Method $method is not callable");
		} catch(Exception $e){
			throw $e;
		}
	}
	
	/**
	 * Determines if a method is callable in the real service object
	 * @param string $method
	 * @return boolean
	 */
	protected function isMethodCallable($method){
		$name = $method->getName();
		if($name == '__construct' || $name == '__destruct' || strcasecmp($name, get_class($this->service)) == 0) 
			// always exclude constructors and destructors
			return false;
		if(!$method->isPublic()) // only public methods
			return false;
		return true;
	}
}

/**
 * Default error handler for use when handling requests from a HessianPHP Service.
 * It throws a service Fault to return to the calling client.
 * @param unknown_type $errno
 * @param unknown_type $errstr
 * @param unknown_type $errfile
 * @param unknown_type $errline
 * @return boolean
 */
function hessianServiceErrorHandler($errno, $errstr, $errfile, $errline) {
	$msg = '';
    switch ($errno) {
    case E_USER_ERROR:
        $msg = "<b>Fatal error</b> [$errno] $errstr<br />\n";
        break;
    case E_USER_WARNING:
        $msg = "<b>WARNING</b> [$errno] $errstr<br />\n";
        break;

    case E_USER_NOTICE:
        $msg = "<b>NOTICE</b> [$errno] $errstr<br />\n";
        break;
    default:
        $msg = "Unknown error type: [$errno] $errstr<br />\n";
        break;
    }
	$msg .= "on line $errline in file $errfile";
    $ex = new HessianFault($msg, $errno);
    $ex->detail = $msg;
    throw $ex;
    return true;
}
