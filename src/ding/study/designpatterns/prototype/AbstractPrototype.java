package ding.study.designpatterns.prototype;

abstract class AbstractPrototype implements java.lang.Cloneable {

	String name;

	public AbstractPrototype() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
 			e.printStackTrace();
		}
		return obj;

	}

}
