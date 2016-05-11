package ding.study.designpatterns.visitor;
/**
 * 失败时候的访问类
 * @author daniel
 *
 */
public class Failing extends Visitor {
    private String type="失败";
    /**
     * 访问男人
     */
 @Override
 public void getVisitor(Man concreteElementA) {
  System.out.println(concreteElementA.getSexMessage()+type+"时,闷头喝酒谁也不用劝。");

 }

 /**
  * 访问女人
  */
 @Override
 public void getVisitor(Woman concreteElementB) {
  System.out.println(concreteElementB.getSexMessage()+type+"时,眼泪汪汪，谁也劝不了。");
 }

}