<?php

$show = @$_GET['show'];
if($show):
	if(file_exists($show)) {
		echo "<a href='index.php' name='top'>Back</a> <br> file: $show <hr>";
		highlight_file($show);
		echo "<hr /><a href='#top' >Top</a>";
		die();
	}
endif;


$path = dirname(__FILE__).'/simpletest/autorun.php';
$exists = file_exists($path);
?>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
 <HEAD>
  <TITLE> HessianPHP2 </TITLE>
 </HEAD>

 <BODY>
  
	<h1>HessianPHP 2 Unit Test runner page</h1>

	<p>You can run the unit test suite for each major versions of the protocol using the links below. The unit tests require an
	installation of Simpletest in this directory. Simpletest can be downloaded from <a href="http://www.simpletest.org/">http://www.simpletest.org</a></p>

	<?php if(!$exists):?>
		<b>WARNING: Simpletest is not present, you will not be able to run the unit tests.</b> <br>
		<p>Please download Simpletest and unpack the contents inside this directory, you will see a folder called /simpletest.
		</p>
	<?php else:?>
		Simpletest is installed in '<?php echo dirname($path);?>', you can run the unit tests.
	<?php endif;?>

	<br />
	<hr />

	<a href="test_version2.php">Run test for version 2 of protocol (newest)</a> |
	<a href="?show=test_version2.php">View source</a>	
	<br/>
	<a href="test_version1.php">Run test for version 1 of protocol</a> |
	<a href="?show=test_version1.php">View source</a>	
	<br>
	<br>
	<a href="?show=php_test_server.php">Show source for test server</a>	
	<br>
	<a href="?show=base_test.php">Show source for test base file</a>	

 </BODY>
</HTML>
