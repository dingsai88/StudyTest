package ding.study.designpatterns.visitor2;

public class ZMainTest {

	/**
	 * @author daniel
	 * @time 2016-5-11 обнГ2:30:19
	 * @param args
	 */
	public static void main(String[] args) {
        
		PersonVisitorTools tools=new PersonVisitorTools();
		tools.add(new PersonNan());
		tools.add(new PersonNv());
		
		Visitor visitor=new VisitorSaNiao();
		tools.show(visitor);
		
		 visitor=new VisitorSaPo();
		
		 tools.show(visitor);
	}

}
