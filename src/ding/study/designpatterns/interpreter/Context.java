package ding.study.designpatterns.interpreter;

/**
 * 解释器之外的一些全局信息
 * Interpreter方法所需要的信息的容器，该信息对Interpreter而言全局可见。充当几个AbstractExpresssion
 * 实例之间的通讯频道。
 * 
 * @author daniel
 * 
 */
public class Context {
	private String input;
	private String output;

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

}