package ding.study.designpatterns.builder;
/**
 * 抽象造人过程，其它类实现造人细节
 * 定义
将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式。[1] 
实用范围
1 当创建复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时。
2 当构造过程必须允许被构造的对象有不同表示时。
角色
在这样的设计模式中，有以下几个角色：
1 builder：为创建一个产品对象的各个部件指定抽象接口。
2 ConcreteBuilder：实现Builder的接口以构造和装配该产品的各个部件，定义并明确它所创建的表示，并 提供一个检索产品的接口。
3 Director：构造一个使用Builder接口的对象。
4 Product：表示被构造的复杂对象。ConcreteBuilder创建该产品的内部表示并定义它的装配过程，包含定义组成部件的类，包括将这些部件装配成最终产品的接口。
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 上午10:26:50
 */
public class Zmain {

	/**男人爆炸头
男人大胸肌
男人大脚板

	 * @author daniel
	 * @time 2016-5-23 上午10:20:48
	 * @param args
	 */
	public static void main(String[] args) {

		//建造者-人类导演
		PersonDirector personDirector=new PersonDirector();
		//造人类，并且执行完造人细节
		Person person=personDirector.constructPerson(new PersonBuilderManImpl());
		System.out.println(""+person.getHead());
		System.out.println(""+person.getBody());
		System.out.println(""+person.getFoot());
	}

}
