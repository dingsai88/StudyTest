package com.ding.jvm;

public class Jvm2TestMainVMStackSOF {

	private int stackLength = 1;

	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {

		Jvm2TestMainVMStackSOF oom = new Jvm2TestMainVMStackSOF();
		try {
			oom.stackLeak();

		} catch (Throwable e) {
			System.out.println("stack length:" + oom.stackLength);
			throw e;
		}

	}

}
