package ding.study.designpatterns.abstratfactory;

/**
 * 工厂接口
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:26:05
 */
public interface FactoryService {
	/*
	 * 获得用户接口
	 */
	public UserService getUserService();

	/*
	 * 获得部门接口
	 */
	public DepartmentService getDepartmentService();

}
