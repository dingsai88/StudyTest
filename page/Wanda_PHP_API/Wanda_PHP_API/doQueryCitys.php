<?php
//查询城市列表
    include_once './getProxy.php';
    include_once './buildSign.php';
    include_once './ServiceParameter.php';
    
    $funid = 'doQueryCitys';
    $para  = new ServiceParameter();
	$para->setObjs(array());

    $sign = buildSign($funid);
    $proxy = getProxy();

	try {
		$ret = $proxy->doAdapt($funid,$para, $sign);
        var_dump($ret);
	} catch (Exception $ex) {
		echo 'Message: ' . $ex->getMessage() . "\n";
	}

?>
