package ding.study.designpatterns.iterator;

/**
 * 迭代器 抽象类
 * @author daniel
 *
 */
public abstract class Iterator {
 //返回第一条数据
 public abstract Object first();
 //下一条数据
 public abstract Object next();
 //是否有下一条
 public abstract boolean isDone();
 //当前条数
 public abstract Object currentItem();

}