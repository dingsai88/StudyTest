package ding.study.designpatterns.visitor;
/**
 * 观察人撒泼
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:28:58
 */
public class VisitorSaPo implements Visitor {

	@Override
	public void getVersion(Person person) {
		 if(person instanceof PersonNan){
				System.out.println( person.getMessage()+"撒泼  -打人");
			 }
			 if(person instanceof PersonNv){
					System.out.println( person.getMessage()+"撒泼-哭");
				 }

			}

}
