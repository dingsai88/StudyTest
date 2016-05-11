package ding.study.designpatterns.visitor;
/**
 * 访问者抽象类
 * @author daniel
 *
 */
abstract class Visitor {
/**
 * 访问男人的反应 抽象
 * @param concreteElementA
 */
 public abstract void getVisitor(Man concreteElementA); 
 
 /**
  *访问女人的反应 抽象
  */
 
 public abstract void getVisitor(Woman concreteElementB);
}

/**
 * 人类抽象
 * @author daniel
 *
 */
abstract class Person{ 
 /**
  * 接受访问
  * @param visitor
  */
 public abstract void accept(Visitor visitor);
}

 