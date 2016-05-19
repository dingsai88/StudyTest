package ding.study.designpatterns.proxy;
/**
 * 请求实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 上午10:42:42
 */
public class SubjectImpl implements Subject {

	@Override
	public void request() {

		System.out.println("发出请求");
	}

}
