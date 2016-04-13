<?php
    include_once './config.php';
	include_once './HessianPHP_v2.0.3/src/HessianClient.php';

	date_default_timezone_set('Asia/Chongqing'); 
    function getProxy() {
        $options = new HessianOptions();
        $options->typeMap['ServiceParameter'] = 'com.talkweb.wanda.app.ServiceParameter';
        $options->typeMap['SignParameter'] = 'com.talkweb.wanda.app.SignParameter';
	    $proxy = new HessianClient(URL, $options); 
        return $proxy;
    }
?>
