package ding.study.designpatterns.flyweight;

/**
 * 网站实现类
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 上午10:51:35
 */
public class WebSiteImpl  extends WebSite{ 
	//网站分类名称
	 private String name="";
	 /**
	  * 构造函数
	  * @param name
	  */
	 public WebSiteImpl(String name){
	  this.name=name;
	 }
	 /**
	  * 显示名称
	  */
	 @Override
	 public void use() {
	  System.out.println("网站分类:"+this.name);

	 }}
