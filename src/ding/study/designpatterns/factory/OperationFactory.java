package ding.study.designpatterns.factory;

/**
 * 工厂类
 * 
 * @author daniel
 * 
 */
public class OperationFactory {

	/**
	 * 工厂方法
	 * 
	 * @param operate
	 * @return
	 */
	public static Operation createOperate(char operate) {
		Operation oper = null;
		switch (operate) {
		case '+':
			oper = new OperationAdd();
			break;
		case '-':
			oper = new OperationSub();
			break;
		case '*':
			oper = new OperationMul();
			break;
		case '/':
			oper = new OperationDiv();
			break;
		}
		return oper;
	}

}