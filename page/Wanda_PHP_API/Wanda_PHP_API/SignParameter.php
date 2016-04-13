<?php
class SignParameter {
	var $mac;
	var $entryMem;
	var $entryPass;
	var $funId;
	var $mid;
	var $timeStamp;
	var $key;

	public function getKey() {
		return $this->key;
	}

	public function setKey($key) {
		$this->key = $key;
	}

	public function getEntryMem() {
		return $this->entryMem;
	}

	public function setEntryMem($entryMem) {
		$this->entryMem = $entryMem;
	}

	public function getEntryPass() {
		return $this->entryPass;
	}

	public function setEntryPass($entryPass) {
		$this->entryPass = $entryPass;
	}

	public function getMac() {
		return $this->mac;
	}

	public function setMac($mac) {
		$this->mac = $mac;
	}

	public function getTimeStamp() {
		return $this->timeStamp;
	}

	public function setTimeStamp($timeStamp) {
		$this->timeStamp = $timeStamp;
	}

	public function getFunId() {
		return $this->funId;
	}

	public function setFunId($funId) {
		$this->funId = $funId;
	}

	public function getMid() {
		return $this->mid;
	}

	public function setMid($mid) {
		$this->mid = $mid;
	}

}
