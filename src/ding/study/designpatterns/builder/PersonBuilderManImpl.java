package ding.study.designpatterns.builder;
/**
 * 造人实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 上午10:23:28
 */
public class PersonBuilderManImpl implements PersonBuilder{
	 private Person person;
	 public PersonBuilderManImpl(){
		 person=new Person();
	 }

	@Override
	public void buildHead() {
		person.setHead("男人爆炸头");
	}

	@Override
	public void buildBody() {
		person.setBody("男人大胸肌");
		
	}

	@Override
	public void buildFoot() {
		person.setFoot("男人大脚板");
		
	}

	@Override
	public Person buildPerson() {
		return person;
	}

}
