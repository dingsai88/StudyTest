package ding.study.designpatterns.visitor2;
/**
 * 观察人撒尿
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:28:47
 */
public class VisitorSaNiao implements Visitor {

	@Override
	public void getVersion(Person person) {
	 if(person instanceof PersonNan){
		System.out.println( person.getMessage()+"站着");
	 }
	 if(person instanceof PersonNv){
			System.out.println( person.getMessage()+"蹲着");
		 }

	}

}
