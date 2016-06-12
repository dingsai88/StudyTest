package ding.study.designpatterns.mediator;
/**
 * 中介者模式（Mediator）：用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显示地相互引用，从而使其耦合松散，而且可以独立的改变他们之间的交互。
 * 
 * 输出
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-12 下午12:27:40
 */
public class ZMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		UnitedNationsSecurityCouncil UNSC = new UnitedNationsSecurityCouncil();
		USA c1 = new USA(UNSC);
		Iraq c2 = new Iraq(UNSC);
		UNSC.setColleague1(c1);
		UNSC.setColleague2(c2);

		c1.declare("美国说 我要吃饭");
		c2.declare("伊拉克说 没饭吃喽");

	}
}
