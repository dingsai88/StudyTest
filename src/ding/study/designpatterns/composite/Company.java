package ding.study.designpatterns.composite;
/**
 * 公司类抽象啊
 * @author daniel
 *
 */
public abstract class Company {

	 
	 protected String name;
	 //公司名字
	 public Company(String name){
	  this.name=name;
	 }
	 //添加公司或者部门
	 public abstract void add(Company c);
	 public abstract void remove(Company c);
	 //展示公司 或部门名称  层级
	 public abstract void display(int i);
	 //展示部门职能
	 public abstract void lineOfDuty();
	}