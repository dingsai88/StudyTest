package ding.study.designpatterns.chainofresponsibility;
/**
 * 大于等于20的处理类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 上午9:57:49
 */
public class HandlerImpl3 extends Handler {

	@Override
	public void handlerRequest(int request) {
		if (request >= 20 ) {
			System.out.println("  大于等于20的处理类 处理");
		} else if (this.successor != null) {
			//交于下一个责任链执行
			this.successor.handlerRequest(request);

		}
	}

}