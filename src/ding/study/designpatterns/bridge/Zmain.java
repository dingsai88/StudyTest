package ding.study.designpatterns.bridge;

public class Zmain {

	/**
	 * 【GOF95】在提出桥梁模式的时候指出，桥梁模式的用意是"将抽象化(Abstraction)与实现化(Implementation)脱耦，使得二者可以独立地变化"。这句话有三个关键词，也就是抽象化、实现化和脱耦。
	 * 桥接模式（Bridge）：将抽象部分与它的实现部分分离，使它们都可以独立地变化。
	 * @author daniel
	 * @time 2016-5-26 下午12:42:52
	 * @param args
	 */
	public static void main(String[] args) {

		PhoneBrandIphone5 i5 = new PhoneBrandIphone5();
		i5.setPhoneSoft(new PhoneSoftGame());
		i5.run();
		i5.setPhoneSoft(new PhoneSoftAddressList());
		i5.run();
		

		PhoneBrandIphone4 i4 = new PhoneBrandIphone4();
		i4.setPhoneSoft(new PhoneSoftGame());
		i4.run();
		i4.setPhoneSoft(new PhoneSoftAddressList());
		i4.run();
		
		
		
	}

}
