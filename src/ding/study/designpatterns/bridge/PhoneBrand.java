package ding.study.designpatterns.bridge;
/**
 * 手机品牌抽象类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 上午11:52:09
 */
public abstract class PhoneBrand {
/**
 * 手机软件
 */
	protected PhoneSoft phoneSoft ;
	public void setPhoneSoft(PhoneSoft phoneSoft){
		this.phoneSoft=phoneSoft;
	}
	/**
	 * 运行软件
	 * @author daniel
	 * @time 2016-5-26 上午11:52:03
	 */
	public abstract void run(); 
	
}
