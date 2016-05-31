package ding.study.designpatterns.interpreter;
/**
 * 终端解释器
 * - 实现和语法中末端符号相关的Interpret方法。
- 在每个句子的末端符号中均需要一个TerminalExpression实例。
 * @author daniel
 *
 */
public class TerminalExpression extends AbstractExpression {

 @Override
 public void interpret(Context context) {
   System.out.println("终端解释器");
  
 }

}