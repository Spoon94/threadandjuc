package com.geekagain.waitnotify.Print;

/**
 * @program: threadandjuc
 * @description:
 * @author: spoon
 * @create: 2020/09/06
 **/
public class PrintVersionWaitNotify {
	static Object mutx = new Object();
	static int num = 1;
	static Runnable runnable = new Runnable() {
		@Override
		public void run() {
			synchronized (mutx){
				while(num<=100){
					System.out.println(Thread.currentThread().getName()+" "+num++);
					mutx.notifyAll();
					try {
						mutx.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						Thread.currentThread().interrupt();
					}
				}
				mutx.notifyAll();
			}
		}
	};

	public static void main(String[] args) {
		Thread thread1 = new Thread(runnable);
		Thread thread2 = new Thread(runnable);
		System.out.println("programm start");
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("programm end");

	}
}
