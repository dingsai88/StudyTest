package com.ding.webservice;
/**
 * 
 * apache的axis1.4框架。是目前最稳定，使用最广泛的版本。用于webservice开发

1、CXF_HOME=C:\DingSai\Tools\apache-cxf-2.7.18

2、在path后面加上 %CXF_HOME%/bin;

在cmd命令中输入wsdl2java，如果有提示usage，就表明配置成功


 

开始使用
set Axis_Lib=C:\Users\Desktop\axis\WEB-INF\lib  
set Java_Cmd=java -Djava.ext.dirs=%Axis_Lib% 
set Output_Path=C:\webservice
set Package=com.ding.webservice
%Java_Cmd% org.apache.axis.wsdl.WSDL2Java -o%Output_Path% -p%Package%  http://10.xx.xx.xx/webservice/?wsdl

 wsdl有时候需要小写
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-5 上午11:02:46
 */
public class ZTestMain {

	/**
	 * @author daniel
	 * @time 2016-5-5 上午11:00:05
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		WsdlPortType service = new WsdlLocator().getwsdlPort(new java.net.URL("http://10.xx.xx.xx/webservice/?WSDL"));

		String retMsg = service.getCinemas("xxxx", "xxxx");
		System.out.println("" + retMsg);

	}

}
