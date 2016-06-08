package ding.study.designpatterns.command;
/**
 * 具体命令实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 上午11:11:32
 */
public class CommandImpl extends Command {
	/**
	 * 调用父类构造方法
	 * 
	 * @param receiver
	 */
	public CommandImpl(Receiver receiver) {
		super(receiver);
	}

	/**
	 * 执行具体方法
	 */
	@Override
	public void execute() {
		receiver.action();

	}

}