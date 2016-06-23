package ding.study.designpatterns.proxy;

/**
 * 代理类
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 上午10:42:33
 */
public class Proxy implements Subject {
	private Subject subject;

	@Override
	public void request() {
		if (subject == null) {
			subject = new SubjectImpl();
		}
		subject.request();
	}

}
