package ding.study.designpatterns.iterator;

/**
 * 迭代器实现类
 * 
 * @author daniel
 * 
 */
public class ConcreteIterator extends Iterator {
	// 聚集类
	private ConcreteAggregate aggregate;
	private int current = 0;

	/**
	 * 初始化传入聚集类
	 * 
	 * @param aggregate
	 */
	public ConcreteIterator(ConcreteAggregate aggregate) {
		this.aggregate = aggregate;
	}

	/**
	 * 获得当前数值
	 */
	@Override
	public Object currentItem() {
 		return aggregate.get(current);
	}

	/**
	 * 返回第一条值
	 */
	@Override
	public Object first() {
 		return this.aggregate.get(0);
	}

	/**
	 * 判断是否有下一条 false 是有下一条
	 */
	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return current >= aggregate.count() ? true : false;
	}

	/**
	 * 下一条返回
	 */
	@Override
	public Object next() {
		Object ret = null;
		current++;
		if (current < aggregate.count()) {
			ret = aggregate.get(current);
		}

 		return ret;
	}

}