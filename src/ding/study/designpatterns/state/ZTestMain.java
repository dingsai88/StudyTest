package ding.study.designpatterns.state;

import com.ding.webservice.Wsdl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.support.GenericXmlApplicationContext;

public class ZTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new 人工作类 设置时间 输出状态
		Work w = new Work();
		w.setHour(9);
		w.writeProgram();
		w.setHour(10);
		w.writeProgram();
		w.setHour(11);
		w.writeProgram();
		w.setHour(12);
		w.writeProgram();
		w.setHour(13);
		w.writeProgram();
		w.setHour(14);
		w.writeProgram();
		w.setHour(15);
		w.writeProgram();
		w.setHour(16);
		w.writeProgram();
		w.setHour(17);
		w.writeProgram();
		w.setHour(18);
		w.writeProgram();
		w.setHour(19);
		w.writeProgram();
		w.setHour(20);
		w.writeProgram();
		w.setHour(21);
		w.writeProgram();
		w.setHour(22);
		w.writeProgram();
		w.setHour(23);
		w.writeProgram();
		w.setHour(24);
		w.writeProgram();

		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext-ding*.xml");
		context.refresh();

		// String paths[] = {"applicationContext.xml"};
		// 这个xml文件是Spring配置beans的文件，顺带一提，路径 在整个应用的根目录
		// ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		// MyBean myBean = (MyBean)ctx.getBean("myBeanImpl");
		// 获得实现类的名字需要增加注解
		String[] names = context.getBeanNamesForType(Wsdl.class);

		Map<String, Object> beans = new HashMap<String, Object>();

		for (String name : names) {
			System.out.println("name:" + name);
			// beans.put(name, context.getBean(name));
		}

		}

}