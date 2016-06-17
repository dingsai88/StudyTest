package ding.study.designpatterns.abstratfactory;
/**
 * 用户接口类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-17 上午10:12:45
 */
public interface UserService {
	/**
	 * 新增用户
	 * @author daniel
	 * @time 2016-6-17 上午10:13:01
	 * @param user
	 */
	public void insertUser(User user);
	/**
	 * 获得用户方法
	 * @author daniel
	 * @time 2016-6-17 上午10:13:51
	 * @param id
	 * @return
	 */
	 public   User getUser(int id);
}
