package ding.study.designpatterns.strategy;

/**
 * 继承抽象类 实现抽象方法
 * 
 * @author daniel
 * 
 */
class ConcreteStrategyA extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("算法A实现");
	}

}

class ConcreteStrategyB extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("算法B实现");
	}
}

class ConcreteStrategyC extends Strategy {
	public void AlgorithmInterface() {
		System.out.println("算法C实现");
	}
}
