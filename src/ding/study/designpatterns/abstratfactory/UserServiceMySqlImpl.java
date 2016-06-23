package ding.study.designpatterns.abstratfactory;
/**
 * mysql 用户接口的实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:16:28
 */
public class UserServiceMySqlImpl implements UserService {

	@Override
	public void insertUser(User user) {
		System.out.println("Mysql insert User");

	}

	@Override
	public User getUser(int id) {
		System.out.println("Mysql get User");
		return null;
	}

}
