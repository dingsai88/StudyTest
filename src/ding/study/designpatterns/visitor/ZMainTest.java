package ding.study.designpatterns.visitor;
/**
 * 访问者模式：（Visitor）,表示一个作用于某个对象结构的各元素的操作。它使你可以在不改变元素的类的前提下定义作用于这些元素的新操作。
 * 
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午4:37:01
 */
public class ZMainTest {

	/**
	 * @author daniel
	 * @time 2016-5-11 下午2:30:19
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 观察者集合
		 */
		PersonVisitorTools tools = new PersonVisitorTools();
		tools.add(new PersonNan());
		tools.add(new PersonNv());

		// 观察人去洗手间
		Visitor visitor = new VisitorSaNiao();
		tools.show(visitor);

		// 观察人发飙
		visitor = new VisitorSaPo();
		tools.show(visitor);
	}

}
