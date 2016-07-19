package com.ding.thread;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * 
 * @author danield
 * 
 */
public class ThreadPoolExecutorTest {
	private static int produceTaskMaxNumber = 200;

	public static void main(String[] args) {
		// 构造一个线程池;核心；最大；等待时间；单位;到200上限就抛出 RejectedExecutionException异常
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 20, 3,
				TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

		
		
		
		for (int i = 1; i <= produceTaskMaxNumber; i++) {
			System.out.println(Thread.currentThread().getName());
			System.out.println("线程测试getMaximumPoolSize:"
					+ threadPool.getMaximumPoolSize());
			System.out.println("线程测试corePoolSize:"
					+ threadPool.getCorePoolSize());
			System.out.println("线程测试getActiveCount:"
					+ threadPool.getActiveCount());
			System.out.println("线程测试getTaskCount:" + threadPool.getTaskCount());
			System.out.println("检查检查getLargestPoolSize:"
					+ threadPool.getLargestPoolSize());
			System.out.println("线程测试getPoolSize返回池中的当前线程数。:"
					+ threadPool.getPoolSize());
			
			try {
				// 产生一个任务，并将其加入到线程池
				final String task = "task@ " + i;
				// System.out.println("put " + task);
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						System.out.println("执行"+task);
						// 睡60秒
						try {
							Thread.sleep(1000 * 3);
							System.out.println(System.currentTimeMillis());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} catch (RejectedExecutionException e) {
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}

		}

		System.out.println("------------------end------------------------:");

		for (int i = 1; i <= 1; i++) {
			// 睡60秒
			try {
				Thread.sleep(1000 * 10);
				System.out.println("检查输出");
				System.out.println("线程测试getMaximumPoolSize:"
						+ threadPool.getMaximumPoolSize());
				System.out.println("线程测试corePoolSize:"
						+ threadPool.getCorePoolSize());
				System.out.println("检查检查getActiveCount:"
						+ threadPool.getActiveCount());
				System.out.println("检查检查getTaskCount:"
						+ threadPool.getTaskCount());
				System.out.println("检查检查getLargestPoolSize:"
						+ threadPool.getLargestPoolSize());
				System.out.println("检查检查getPoolSize:"
						+ threadPool.getPoolSize() + "\n\n");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
