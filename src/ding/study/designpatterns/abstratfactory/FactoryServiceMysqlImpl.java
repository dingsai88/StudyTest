package ding.study.designpatterns.abstratfactory;

public class FactoryServiceMysqlImpl implements FactoryService {

	@Override
	public UserService getUserService() {

		return new UserServiceMySqlImpl();
	}

	@Override
	public DepartmentService getDepartmentService() {
		return new DepartmentServiceMySqlImpl();
	}

}
