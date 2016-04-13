<?php
include_once 'base_test.php';

echo '<h4>HessianPHP 2 Unit tests (protocol v1)</h4>';

echo "Url ".baseURL().'php_test_server.php';

class Hessian1Tests extends BaseHessianTests {
	var $version = 1;
}
