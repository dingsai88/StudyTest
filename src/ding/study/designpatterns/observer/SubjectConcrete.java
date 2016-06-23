package ding.study.designpatterns.observer;

/**
 * 发布者子类 加入发布的具体内容 白银价格
 * 
 * @author daniel
 * 
 */
public class SubjectConcrete extends Subject {
	// 白银价格
	private double agPrice;

	/**
	 * @return the agPrice
	 */
	public double getAgPrice() {
		return agPrice;
	}

	/**
	 * @param agPrice
	 *            the agPrice to set
	 */
	public void setAgPrice(double agPrice) {
		this.agPrice = agPrice;
		this.Notify();
	}

}