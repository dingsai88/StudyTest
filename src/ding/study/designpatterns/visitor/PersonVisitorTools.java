package ding.study.designpatterns.visitor;
 
import java.util.ArrayList;
import java.util.List;
/**
 * 观察person的集合类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 下午2:27:03
 */
public class PersonVisitorTools {

	 /**
	  * PerSon集合
	  */
	 private List<Person> elements=new ArrayList<Person>();
	 /**
	  * 添加人对象
	  * @param element
	  */
	 public void add(Person element){
	  elements.add(element);
	 }
	 /**
	  * 杀人
	  * @param element
	  */
	 public void delete(Person element){
	  elements.remove(element);
	 }
	 /**
	  * 排队报数  访问状态下 人的反应
	  * @param visitor
	  */
	public void show(Visitor visitor) {
		for (Person e : elements) {
			e.showTime(visitor);
		}
	} 
	 
	 
}
