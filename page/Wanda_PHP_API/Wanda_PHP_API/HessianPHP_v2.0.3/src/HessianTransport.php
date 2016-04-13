<?php
/*
 * This file is part of the HessianPHP package.
 * (c) 2004-2010 Manuel Gómez
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

/**
 * RContract for a network request to remote services 
 */
interface IHessianTransport {
	/**
	 * Executes a POST request to a remote Hessian service and returns a
	 * HessianStream for reading data 
	 * @param string $url url of the remote service
	 * @param binary $data binary data payload
	 * @param HessianOptions $options optional parameters for the transport
	 * @return HessianStream input stream
	 */
	function getStream($url, $data, $options);
	/**
	 * Tests wether the transport is available in this installation 
	 */
	function testAvailable();
	function getMetadata();
}

/**
 * Hessian request using the CURL library
 */
class HessianCURLTransport implements IHessianTransport{
	var $metadata;
	var $rawData;
	
	function testAvailable(){
		if(!function_exists('curl_init'))
			throw new Exception('You need to enable the CURL extension to use the curl transport');
	}
	
	function getMetadata(){
		return $this->metadata;
	}
	
	function getStream($url, $data, $options){
		$ch = curl_init($url);
		if(!$ch)
			throw new Exception("curl_init error for url $url.");
		
		$curlOptions = array(
			CURLOPT_URL => $url,
			CURLOPT_POST => 1,
			CURLOPT_POSTFIELDS => $data,
			CURLOPT_FOLLOWLOCATION => true,
			CURLOPT_RETURNTRANSFER => 1,
			CURLOPT_HTTPHEADER => array("Content-Type: application/binary")
		);
		
		if(!empty($options->transportOptions)){
			$extra = $options->transportOptions;
			if(isset($extra[CURLOPT_HTTPHEADER])){
				$curlOptions[CURLOPT_HTTPHEADER] = array_merge($curlOptions[CURLOPT_HTTPHEADER]
					, $extra[CURLOPT_HTTPHEADER]);
			}
			// array combine operation, does not overwrite existing keys
			$curlOptions = $curlOptions + $options->transportOptions;
		}
		curl_setopt_array($ch, $curlOptions);
			
		$result = curl_exec($ch);
		$this->metadata = curl_getinfo($ch);
		$error = curl_error($ch);
		$code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
		if($error){
			$this->safeClose($ch);
			throw new Exception("CURL transport error: $error");
		}
		if($result === false) {
			$this->safeClose($ch);
			throw new Exception("curl_exec error for url $url");
		}
		if(!empty($options->saveRaw))
			$this->rawData = $result;
		$this->safeClose($ch);
		if($code != 200){
			$this->safeClose($ch);
			$msg = "Server error, returned HTTP code: $code";
			if(!empty($options->saveRaw))
				$msg .= " Server sent: ".$result;
			throw new Exception($msg);
		}
		$stream = new HessianStream($result);
		return $stream;
	}

	private function safeClose($ch){
		if(is_resource($ch))	
			curl_close($ch);
	}
}

/**
 * Hessian request using PHP's http streaming context
 */
class HessianHttpStreamTransport implements IHessianTransport{
	var $metadata;
	var $options;
	var $rawData;
	var $lastError = '';
	
	function testAvailable(){
		if(!ini_get('allow_url_fopen'))
			throw new Exception("You need to enable allow_url_fopen to use the stream transport");
	}
	
	function getMetadata(){
		return $this->metadata;
	}
	
	function getStream($url, $data, $options){
		$this->lastError = '';
		$bytes = str_split($data);
		
		$params = array(
		    'method'=>"POST",
		    'header'=> array("Content-Type: application/binary",
		              "Content-Length: ".count($bytes) ),
			'timeout' => 3,
			'content' => $data
		);
		
		if(!empty($options->transportOptions)){
			$http = $options->transportOptions;
			if(isset($http['header'])){
				$headers = $http['header'];
				$newheaders = null;
				if(is_string($headers)){
					 $newheaders = explode("\n", $headers);
				}
				if(is_array($headers))
					$newheaders = $headers;
				if(!empty($newheaders)) {
					//$params['header'] = array();
					foreach($newheaders as $header){
						$params['header'][] = trim($header);
					}
				}
			} 
			$params = $params + $http;
			if(isset($http['timeout']))
				$params['timeout'] = $http['timeout'];
		}

		$scheme = 'http';
		if(strpos($url, 'https') === 0)
			$scheme = 'https';
		$opt = array($scheme => $params);		
		set_error_handler(array($this, 'httpErrorHandler'));
		$ctx = stream_context_create($opt);
		$fp = fopen($url, 'rb', false, $ctx);
		if (!$fp) {
			restore_error_handler();
			throw new Exception("Conection problem, message: $this->lastError");
		}
		$response = stream_get_contents($fp);
		if ($response === false) {
			if($fp) 
				fclose($fp);
			restore_error_handler();
			throw new Exception("Problem reading data from url, message: $this->lastError");
		} 
		restore_error_handler();
		$this->metadata = stream_get_meta_data($fp);
		$this->metadata['http_headers'] = array();
		foreach ($this->metadata['wrapper_data'] as $raw_header) {
			$parts = explode(':', $raw_header);
			$header = $parts[0];
			$data = count($parts) > 1 ? $parts[1] : '';
			$this->metadata['http_headers'][strtolower($header)] = trim($data);
		}
		fclose($fp);
		if(!empty($options->saveRaw))
			$this->rawData = $response;
		$stream = new HessianStream($response, $this->metadata['http_headers']['content-length']);
		return $stream;
	}
	
	function httpErrorHandler($errno, $errstr, $errfile, $errline){
    	$this->lastError = $errstr;
    	return true;
	}
}