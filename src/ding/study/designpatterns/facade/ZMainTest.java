package ding.study.designpatterns.facade;
/**
 * 外观模式(Facade):为子系统中的一组接口提供一个一致的界面，此模式定义了一个高层接口，这个接口使子系统更加容易使用。
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-12 下午3:02:36
 */
public class ZMainTest {

	/**
	 * 复杂的逻辑封装为一个简单的接口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Facade facade = new Facade();
		facade.methodA();
		facade.methodB();
	}

}