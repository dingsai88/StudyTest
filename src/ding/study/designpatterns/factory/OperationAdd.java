package ding.study.designpatterns.factory;

/**
 * 
 * @author daniel 运算类 继承抽象
 */
class OperationAdd extends Operation {

	/*
	 * 重写父类运算方法 (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() + this.getNumberB();
		return result;
	}

}

class OperationSub extends Operation {
	/*
	 * 重写父类运算方法 (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() - this.getNumberB();
		return result;
	}

}

class OperationMul extends Operation {
	/*
	 * 重写父类运算方法 (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() * this.getNumberB();
		return result;
	}

}

class OperationDiv extends Operation {
	/*
	 * 重写父类运算方法 (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() / this.getNumberB();
		return result;
	}

}
