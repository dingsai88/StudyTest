package ding.study.designpatterns.interpreter;
/**
 * 解释器模式抽象类
 * - 声明一个抽象的Interpret方法，抽象语法树中所有的节点都必须实现该抽象方法。
 * @author daniel
 *
 */
abstract class AbstractExpression {
//解释方法
 public abstract void interpret(Context context);
 
}