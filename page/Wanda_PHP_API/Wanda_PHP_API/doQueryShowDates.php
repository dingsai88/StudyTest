<?php
//查询影城场次日期列表
    include_once './getProxy.php';
    include_once './buildSign.php';
    include_once './ServiceParameter.php';
    
    $funid = 'doQueryShowDates';
    $para  = new ServiceParameter();
	$para->setObjs(array("304", "20121117023846859024"));

    $sign = buildSign($funid);
    $proxy = getProxy();

	try {
		$ret = $proxy->doAdapt($funid,$para, $sign);
        var_dump($ret);
	} catch (Exception $ex) {
		echo 'Message: ' . $ex->getMessage() . "\n";
	}

?>