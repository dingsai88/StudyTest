package ding.study.designpatterns.abstratfactory;

public class DepartmentServiceOracleImpl implements DepartmentService {

	@Override
	public void insertDepartment(Department department) {
		System.out.println("Oracle insert Department");

	}

	@Override
	public Department getDepartment(Integer i) {
		System.out.println("Oracle get Department");
		return null;
	}

}
