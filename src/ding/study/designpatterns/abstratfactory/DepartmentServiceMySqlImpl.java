package ding.study.designpatterns.abstratfactory;

public class DepartmentServiceMySqlImpl implements DepartmentService {

	@Override
	public void insertDepartment(Department department) {
		System.out.println("Mysql insert Department");
	}

	@Override
	public Department getDepartment(Integer i) {
		System.out.println("Mysql get Department");
		return null;
	}

}
