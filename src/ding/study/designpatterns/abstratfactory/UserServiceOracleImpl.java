package ding.study.designpatterns.abstratfactory;
/**
 * oracle用户接口的实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:16:09
 */
public class UserServiceOracleImpl implements UserService {

	@Override
	public void insertUser(User user) {
		System.out.println("Oracle insert User");

	}

	@Override
	public User getUser(int id) {
		System.out.println("Oracle get User");
		return null;
	}

}
