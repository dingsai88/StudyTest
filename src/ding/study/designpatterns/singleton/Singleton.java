package ding.study.designpatterns.singleton;
/**
 * 启动就new  省的判断了
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-24 上午10:54:21
 */
public class Singleton {
	private static Singleton singleton = new Singleton();
    //私有化构造函数
	private Singleton() {

	}

	//获得对象
	public static Singleton getInstance() {
		return singleton;
	}

}
