package ding.study.designpatterns.adapter;
/**
 * 希望复用一些现存的类，但是接口又与复用环境要求不一致。
其实适配器模式有点无奈之举，在前期设计的时候，我们就不应该考虑适配器模式，而应该考虑通过重构统一接口。

适配器模式：将一个类的接口转换成客户希望的另外一个接口。adapter模式使得原本由于接口不兼容而不能一起工作的那些类可以一起工作。
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-25 上午11:35:22
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-25 上午11:31:23
	 * @param args
	 */
	public static void main(String[] args) {
		//原有ps2实现类
		Ps2Port ps2=new Ps2Port(){
			@Override
			public void workWithPs2() {
				System.out.println("PS2工作");				
			}			
		};

		Ps2ToUsb to=new Ps2ToUsb(ps2);
		//执行适配转换
		to.workWithUsb();
		
	}

}
