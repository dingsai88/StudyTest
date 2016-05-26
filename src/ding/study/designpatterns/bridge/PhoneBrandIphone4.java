package ding.study.designpatterns.bridge;
/**
 * iphone4手机品牌
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 下午12:46:49
 */
public class PhoneBrandIphone4 extends PhoneBrand {

	@Override
	public void run() {
		System.out.println("Iphone4运行");
		 this.phoneSoft.run();
	}

}
