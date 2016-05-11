package ding.study.designpatterns.visitor2;
/**
 * 定义人对象的接口
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:28:05
 */
public interface Person {

	public void showTime(Visitor visitor);
	public String getMessage();
}
