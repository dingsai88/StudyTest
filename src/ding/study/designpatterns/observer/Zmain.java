package ding.study.designpatterns.observer;

import java.util.Scanner;
/**
 * 观察者模式(发布-订阅):定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，使它们能够自动更新自己。
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午4:01:40
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-18 下午3:57:10
	 * @param args
	 */
	public static void main(String[] args) {
		// 创建发布者
		SubjectConcrete subjectConcrete = new SubjectConcrete();
		// 加入 订阅者
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "狼"));
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "林"));
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "rod johnson"));
		// 发布者更新发布信息 并且发给订阅者
		subjectConcrete.setAgPrice(10);
		// subjectConcrete.Notify();
		while (true) {
			System.out.println("\n\n请输入就最新价格：");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if (str.equals("exit")) {
				System.out.println("结束");
				break;
			}
			System.out.println("您输入的价格为:" + str);
			subjectConcrete.setAgPrice(Double.valueOf(str));
		}

	}

}
