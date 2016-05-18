
package ding.study.designpatterns.visitor;

public class Man extends IPersonShow {
 private String sexMessage="男人";
 @Override
 /**
  * 访问男人时 调用 访问者的访问方法
  */
 public void accept(Visitor visitor) {
  visitor.getVisitor(this);
  }

 /**
  * @return the sexMessage
  */
 public String getSexMessage() {
  return sexMessage;
 }

}