package ding.study.designpatterns.observer;

/**
 * 订阅者抽象类
 * 
 * @author daniel
 * 
 */
public abstract class Observer {
	/**
	 * 订阅者更新发布者发布的内容
	 */
	public abstract void update();
}