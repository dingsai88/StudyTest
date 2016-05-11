package ding.study.designpatterns.visitor2;
/**
 * 男人类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:28:16
 */
public class PersonNan implements Person {

	@Override
	public void showTime(Visitor visitor) {


		visitor.getVersion(this);
	}

	@Override
	public String getMessage() {
		 
		return "男人撒尿";
	}

}
