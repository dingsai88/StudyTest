package ding.study.designpatterns.observer;

/**
 * 订阅者实现类
 * 
 * @author daniel
 * 
 */
public class ObserverConcrete extends Observer {
	private double agPrice;
	private String name;
	private SubjectConcrete subjectConcrete;// 发布者实现类

	/**
	 * 出入 发布者 和 自己的私有信息
	 * 
	 * @param subjectConcrete
	 * @param name
	 */
	public ObserverConcrete(SubjectConcrete subjectConcrete, String name) {
		this.subjectConcrete = subjectConcrete;
		this.name = name;
	}

	/**
	 * 获得发布者 的最新数据
	 */
	@Override
	public void update() {
		agPrice = subjectConcrete.getAgPrice();
		System.out.println("观察者" + name + " 观察到 最新价格是 " + agPrice);
	}

}