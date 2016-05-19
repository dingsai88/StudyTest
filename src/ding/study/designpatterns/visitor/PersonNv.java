package ding.study.designpatterns.visitor;
/**
 * 女人类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:28:27
 */
public class PersonNv implements Person {

	@Override
	public void showTime(Visitor visitor) {
		visitor.getVersion(this);
	}

	 
	@Override
	public String getMessage() {
		//System.out.println("女人撒尿");
		return "女人撒尿";
	}
}
