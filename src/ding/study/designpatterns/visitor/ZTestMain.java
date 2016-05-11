
package ding.study.designpatterns.visitor;

public class ZTestMain {

 /**
  * 测试访问者模式
  * @param args
  */
 public static void main(String[] args) {
   //对象结构类
  ObjectStructure o=new ObjectStructure();
  //放入男人和女人
  o.add(new Man());
  o.add(new Woman());
  //成功状态下访问
  Success visitor1=new Success();
  o.show(visitor1);
  //失败状态下访问
  Failing visitor2=new Failing();
  o.show(visitor2);

 }

}

 