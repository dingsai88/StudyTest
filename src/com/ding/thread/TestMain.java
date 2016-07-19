package com.ding.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Runnable rb = new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("ÄãºÃ" + Thread.currentThread().getName());
			}
		};

		ExecutorService s = Executors.newFixedThreadPool(3);

		for (int i = 0; i < 9; i++) {

			s.execute(rb);
		}

		System.out.println(Thread.currentThread().getName());
	}

	
	Runnable testOther=new Runnable() {
		
		@Override
		public void run() {
			try {
				Thread.sleep(1000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
	};
	
}
