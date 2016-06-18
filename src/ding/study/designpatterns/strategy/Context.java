package ding.study.designpatterns.strategy;

/**
 * 
 * @author daniel 维护Strategy对象的引用
 */
class Context {
	Strategy strategy;

	/**
	 * 初始化抽象类
	 * 
	 * @param strategy
	 */
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * 调用算法 实现方法
	 */
	public void ContextInterface() {
		strategy.AlgorithmInterface();
	}

}
