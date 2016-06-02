package ding.study.designpatterns.chainofresponsibility;

/**
 * 责任链小于10处理类
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 上午9:55:12
 */
public class HandlerImpl1 extends Handler {

	@Override
	public void handlerRequest(int request) {
		if (request >= 0 && request < 10) {
			System.out.println("责任链小于10处理类处理");
		} else if (this.successor != null) {
			//交于下一个责任链执行
			this.successor.handlerRequest(request);

		}
	}

}
