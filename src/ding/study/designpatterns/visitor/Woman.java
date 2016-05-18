
package ding.study.designpatterns.visitor;

public class Woman extends IPersonShow {
 private String sexMessage="女人";

 @Override
 /**
  * 访问女人时，调用 访问者的 目的
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