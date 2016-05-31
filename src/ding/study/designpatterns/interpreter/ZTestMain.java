package ding.study.designpatterns.interpreter;

import java.util.ArrayList;
import java.util.List;
/**
 * 解释器模式（interpreter）:给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。
 * 
 * 输出:
 * 终端解释器
非终端解释器
终端解释器
终端解释器

 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-31 下午2:16:37
 */
public class ZTestMain {

 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = new Context();
		List<AbstractExpression> list = new ArrayList<AbstractExpression>();
		list.add(new TerminalExpression());
		list.add(new NonterminalExpression());
		list.add(new TerminalExpression());
		list.add(new TerminalExpression());
		for (AbstractExpression exp : list) {
			exp.interpret(context);
		}

	}

}