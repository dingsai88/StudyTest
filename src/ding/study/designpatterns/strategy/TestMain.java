
package ding.study.designpatterns.strategy;
/**
 * PS:策略模式是一种定义一系列算法的方法，从概念上来看，所有这些算法完成的都是相同的工作，只是实现不同，它可以以相同的方式调用所有的算法，减少各种算法类与使用算法类之间的耦合。

 

策略模式的Strategy类层次为Context定义了一系列的可供重用的算法或行为。继承有助于析取出些算法中的公共功能。

 

简化了单元测试，每个类可以通过自己的接口测试。

 

-------------------------------------------------------------------------------------------
 

 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-17 上午10:14:59
 */
public class TestMain {

 /**
  * @param args
  */
 public static void main(String[] args) {
   Context context;
  context= new Context(new ConcreteStrategyA());
  context.ContextInterface();
  
  context= new Context(new ConcreteStrategyB());
  context.ContextInterface();
  
  context= new Context(new ConcreteStrategyC());
  context.ContextInterface();

 }

}