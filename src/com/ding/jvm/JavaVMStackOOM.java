package com.ding.jvm;
/**
 * VM args:-Xss2M
 * @author Administrator
 *
 */
public class JavaVMStackOOM {
	private void dontStop() {
		while (true) {

		}
	}

	public void stackLeakByThread() {

		while (true) {
			Thread thread = new Thread(new Runnable() {
				public void run() {

				}
			});

			thread.start();
		}
	}

	public static void main(String[] args) {

		new JavaVMStackOOM().stackLeakByThread();

	}

}
