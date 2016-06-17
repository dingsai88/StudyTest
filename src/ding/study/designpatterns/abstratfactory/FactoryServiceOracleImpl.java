package ding.study.designpatterns.abstratfactory;

public class FactoryServiceOracleImpl implements FactoryService {

	@Override
	public UserService getUserService() {
		return new UserServiceOracleImpl();
	}

	@Override
	public DepartmentService getDepartmentService() {
		return new DepartmentServiceOracleImpl();
	}

}
