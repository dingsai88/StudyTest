<?php
//查询影城详细信息 
    include_once './getProxy.php';
    include_once './buildSign.php';
    include_once './ServiceParameter.php';
    
    $funid = 'doQueryCinemaInfo';
    $para  = new ServiceParameter();
	$para->setObjs(array("304"));

    $sign = buildSign($funid);
    $proxy = getProxy();

	try {
		$ret = $proxy->doAdapt($funid,$para, $sign);
        var_dump($ret);
	} catch (Exception $ex) {
		echo 'Message: ' . $ex->getMessage() . "\n";
	}

?>

