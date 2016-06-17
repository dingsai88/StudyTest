package ding.study.designpatterns.abstratfactory;
/**
 * I.定义:
提供一个创建一系列相关或相互依赖的接口，而无需指定它们具体的类。
为创建一组相关或相互依赖的对象提供一个接口，而且无需指定他们的具体类。

输出结果
Oracle insert User
Oracle get User
Oracle insert Department
Oracle get Department
Mysql insert User
Mysql get User
Mysql insert Department
Mysql get Department


 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:35:58
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-17 上午10:29:44
	 * @param args
	 */
	public static void main(String[] args) {
		// 准备实体对象
		User user = new User();
		Department department = new Department();

		// Oracle版本的工厂
		FactoryService factory = new FactoryServiceOracleImpl();
		// Oracle版本的接口
		UserService userService = factory.getUserService();
		DepartmentService departmentService = factory.getDepartmentService();

		// Oracle版本的执行
		userService.insertUser(user);
		userService.getUser(1);
		departmentService.insertDepartment(department);
		departmentService.getDepartment(1);

		// Mysql版本的工厂
		factory = new FactoryServiceMysqlImpl();
		// Mysql版本的接口
		userService = factory.getUserService();
		departmentService = factory.getDepartmentService();

		// Mysql版本的执行
		userService.insertUser(user);
		userService.getUser(1);
		departmentService.insertDepartment(department);
		departmentService.getDepartment(1);

	}

}
