package ding.study.designpatterns.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现聚集类
 * 
 * @author daniel
 * 
 */
public class ConcreteAggregate extends Aggregate {
	private List<Object> items = new ArrayList<Object>();

	@Override
	public Iterator createIterator() {
		return new ConcreteIterator(this);
	}

	/**
	 * 返回总条数
	 * 
	 * @return
	 */
	public int count() {
		return this.items.size();
	}

	/**
	 * 获得索引 对应的值
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		return this.items.get(index);
	}

	/**
	 * 设置新数据
	 * 
	 * @param index
	 * @param value
	 */
	public void set(int index, Object value) {
		this.items.add(index, value);
	}
}
