package ding.study.designpatterns.abstratfactory;

/**
 * 部门类操作接口
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:20:32
 */
public interface DepartmentService {

	/*
	 * 保存部门信息
	 */
	public void insertDepartment(Department department);

	/*
	 * 获得部门信息
	 */
	public Department getDepartment(Integer i);
}
