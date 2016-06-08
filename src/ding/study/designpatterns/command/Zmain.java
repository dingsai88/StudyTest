package ding.study.designpatterns.command;
/**
 * 命令模式（Command）：
将一个请求封装为一个对象，从而使你可用不同的请求对客户端进行参数化，对请求排队或者记录请求日志，以及支持可撤销的操作。
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 上午11:13:46
 */
public class Zmain {

	/**
	 * 
	 *   //执行具体命令  调用类>命令类>命令实现类>执行类
	 * @author daniel
	 * @time 2016-6-8 上午11:12:52
	 * @param args
	 */
	public static void main(String[] args) {
		// 执行类
		Receiver r = new Receiver();
		// 命令实现类 装载具体执行类
		Command c = new CommandImpl(r);
		// 调用命令类
		Invoker i = new Invoker();
		// 装载命令类
		i.setCommand(c);
		// 执行具体命令 调用类>命令类>命令实现类>执行类
		i.executeCommand();
	}

}
