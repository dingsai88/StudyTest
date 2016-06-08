package ding.study.designpatterns.command;
/**
 * 命令发起者
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 上午11:12:33
 */
public class Invoker {
	private Command command;

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * 调用命令类执行方法
	 */
	public void executeCommand() {
		command.execute();
	}

}
