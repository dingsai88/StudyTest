package ding.study.designpatterns.iterator;

/**
 * 
 迭代器模式（Iterator）
 * 
 * 提供一种方法顺序访问一个聚合对象中各个元素，而又不暴露该对象的内部表示。 输出:
 * 
 * 数据1 最靠前 数据1 输出 哈哈12 输出 哈哈13 输出 哈哈14 输出 哈哈15 输出
 * 
 * 
 * 
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-11 下午6:58:11
 */
public class Zmain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConcreteAggregate a = new ConcreteAggregate();
		a.set(0, "数据1");
		a.set(1, "哈哈12");
		a.set(2, "哈哈13");
		a.set(3, "哈哈14");
		a.set(4, "哈哈15");
		Iterator i = new ConcreteIterator(a);
		Object item = i.first();
		System.out.println(item + "  最靠前");
		while (!i.isDone()) {
			System.out.println(i.currentItem() + "   输出");
			i.next();
		}

	}
}
