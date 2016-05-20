package ding.study.designpatterns.prototype;

public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-20 ионГ11:19:39
	 * @param args
	 */
	public static void main(String[] args) {
		ConcretePrototype1 p1 = new ConcretePrototype1();
		ConcretePrototype1 c1 = (ConcretePrototype1) p1.Clone();

		// System.out.println(c1.getId());

		AbstractPrototype a = new PrototypeImpl1();

		AbstractPrototype b=(AbstractPrototype)a.clone();
		System.out.println(b.getName());
		System.out.println(a.getName());
		a = new PrototypeImpl2();

		System.out.println(a.getName());

	}

}
