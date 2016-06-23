package ding.study.designpatterns.factory;
/**
 * 工厂模式是我们最常用的实例化对象模式了，是用工厂方法代替new操作的一种模式。
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 下午4:54:37
 */
public class ZTestMain {

	/**
	 * 测试用
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
 		Operation oper;
		oper = OperationFactory.createOperate('+');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		// 测试减
		oper = OperationFactory.createOperate('-');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		// 测试乘
		oper = OperationFactory.createOperate('*');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		oper = OperationFactory.createOperate('/');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

	}

}
