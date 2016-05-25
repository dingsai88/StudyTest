package ding.study.designpatterns.adapter;
/**
 * 适配器类 在此适配
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-25 上午11:34:21
 */
public class Ps2ToUsb implements UsbPort {
	// ps2类
	private Ps2Port ps2;

	/**
	 * 私有化构造函数，必须注入ps2
	 */
	private Ps2ToUsb() {
	}

	public Ps2ToUsb(Ps2Port ps) {
		ps2 = ps;
	}

	/**
	 * 原有USB实现方法，在此进行转换
	 */
	@Override
	public void workWithUsb() {
		ps2.workWithPs2();
		System.out.println("转换了");
		System.out.println("USB工作");
	}

}
