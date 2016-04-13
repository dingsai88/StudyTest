<?php
//查询城市影院列表
    include_once './getProxy.php';
    include_once './buildSign.php';
    include_once './ServiceParameter.php';
    
    $funid = 'doQueryCityCinemas';
    $para  = new ServiceParameter();
	$para->setObjs(array("8022251040"));

    $sign = buildSign($funid);
    $proxy = getProxy();

	try {
		$ret = $proxy->doAdapt($funid,$para, $sign);
        var_dump($ret);
	} catch (Exception $ex) {
		echo 'Message: ' . $ex->getMessage() . "\n";
	}
?>
