package ding.study.designpatterns.observer;

import java.util.Scanner;

/**
 * 观察者模式(发布-订阅):定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。这个主题对象在状态发生变化时，会通知所有观察者对象，
 * 使它们能够自动更新自己。
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午4:01:28
 */
public class ATestMain {

	/**
	 * @author daniel
	 * @time 2016-6-18 下午4:00:56
	 * @param args
	 */
	public static void main(String[] args) {
		AgPrice ag = new AgPrice(7.5);
		AgObserver linYiFu = new AgObserver("观察者 林毅夫");
		AgObserver song = new AgObserver("观察者 宋鸿兵");
		AgObserver lang = new AgObserver("观察者 郎咸平");
		ag.addObserver(linYiFu);
		ag.addObserver(song);
		ag.addObserver(lang);
		// System.out.println(ag);
		// ag.setPrice(7.8);
		// System.out.println(ag);
		while (true) {
			System.out.println("\n\n请输入就最新价格：");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if (str.equals("exit")) {
				System.out.println("结束");
				break;
			}
			System.out.println("您输入的价格为:" + str);
			ag.setPrice(Double.valueOf(str));
		}
	}
}
