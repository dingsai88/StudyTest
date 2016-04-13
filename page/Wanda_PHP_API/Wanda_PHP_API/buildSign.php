<?php
    include_once './config.php';
	include_once './SignParameter.php';

    function buildSign($funId) {
        $sign = new SignParameter();
	    $mid = date("YmdHis");
	    $timeStamp = date("YmdHis");
	    $mac = strtoupper(Md5($funId . $mid . $timeStamp . $entryMem . $entryPass . $key));
        $sign->setEntryMem(MERID);
        $sign->setEntryPass(PASS);
        $sign->setFunId($funId);
        $sign->setKey(KEY);
        $sign->setMid($mid);
        $sign->setTimeStamp($timeStamp);
        $sign->setMac($mac);
        return $sign;
    }

?>
