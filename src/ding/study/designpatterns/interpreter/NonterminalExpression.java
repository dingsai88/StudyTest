package ding.study.designpatterns.interpreter;

/**
 * 非终端解释器 另外一个实现了AbstractExpression
 * 接口的类，用来处理语法树中非末端节点的语法。它含有下一个AbstractExpression(s)的引用，调用它每个子节点的Interpret方法。
 * 
 * @author daniel
 * 
 */
public class NonterminalExpression extends AbstractExpression {

	@Override
	public void interpret(Context context) {
 		System.out.println("非终端解释器");
	}

}