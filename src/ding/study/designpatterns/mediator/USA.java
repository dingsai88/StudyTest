package ding.study.designpatterns.mediator;
/**
 * 美国
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-12 上午10:03:41
 */
public class USA extends Country {
	 /**
	  * 构造函数
	  * @param mediator
	  */
	 public USA(UnitedNations mediator){
	  //调用父类构造函数
	  super(mediator);
	 }
	 /**
	  * 声明
	  * @param message
	  */
	 public void declare(String message){
	  mediator.declare(message, this);
	 }
	 /**
	  * 获得消息
	  * @param message
	  */
	 public void getMessage(String message){
	  System.out.println("美国获得对方信息:"+message);
	 }

	}