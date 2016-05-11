package ding.study.designpatterns.visitor;
/**
 * 状态为成功时 访问类
 * @author daniel
 *
 */
public class Success extends Visitor {
    private String type="成功";

 /**
  * 访问男人
  */
 @Override
 public void getVisitor(Man concreteElementA) {
  System.out.println(concreteElementA.getSexMessage()+type+"时,背后多半有一个伟大的女人。");
 }

 /**
  * 访问女人
  */
 @Override
 public void getVisitor(Woman concreteElementB) {
  System.out.println(concreteElementB.getSexMessage()+type+"时,背后多半有一个不成功的男人。");
 }

}