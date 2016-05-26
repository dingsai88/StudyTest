package ding.study.designpatterns.bridge;
/**
 * phone5手机品牌
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 下午12:47:00
 */
public class PhoneBrandIphone5 extends PhoneBrand {

	@Override
	public void run() {
		System.out.println("Iphone5运行");
		 this.phoneSoft.run();
	}

}
