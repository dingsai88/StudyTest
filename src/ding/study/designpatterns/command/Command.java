package ding.study.designpatterns.command;

/**
 * 命令类抽象
 * 
 * @author daniel
 * 
 */
abstract class Command {
	// 执行类属性
	protected Receiver receiver;

	public Command(Receiver receiver) {
		this.receiver = receiver;
	}

	// 调用执行类的执行方法
	abstract public void execute();
}