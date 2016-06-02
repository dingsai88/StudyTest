package ding.study.designpatterns.chainofresponsibility;
/**
 * 职责链模式（Chain of Responsibility）定义:
使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。将这个对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。



数字:1用-责任链小于10处理类处理
数字:5用-责任链小于10处理类处理
数字:11用- 责任链小于20大于10处理类
数字:21用-  大于等于20的处理类 处理
数字:20用-  大于等于20的处理类 处理
数字:9999用-  大于等于20的处理类 处理


 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 上午10:04:43
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-2 上午9:58:42
	 * @param args
	 */
	public static void main(String[] args) {


		HandlerImpl1 handler1=new HandlerImpl1();
		HandlerImpl2 handler2=new HandlerImpl2();
		HandlerImpl3 handler3=new HandlerImpl3();
        handler1.setHandler(handler2);
        handler2.setHandler(handler3);
        int[] request={1,5,11,21,20,9999};
		for(int i:request){
			System.out.print("数字:"+i+"用-");
			handler1.handlerRequest(i);
		}
	}

}
