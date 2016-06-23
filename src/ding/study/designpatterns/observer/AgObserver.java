package ding.study.designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class AgObserver implements Observer {
	private String name;

	public AgObserver(String name) {
		this.name = name;
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Double) {
			System.out.println(this.name + "观察到的价格更改为\n" + ((Double) arg1).toString());
		}

	}

}