package com.geekagain.waitnotify.Print;

/**
 * @program: threadandjuc
 * @description:
 * @author: spoon
 * @create: 2020/09/06
 **/
public class PrintVersionSynchronized {

	static Object mutex = new Object();
	static int count = 1;
	static Runnable oddtask = new Runnable() {
		@Override
		public void run() {
			while (count<100){
				synchronized (mutex){
					if((count&1)==1){
						System.out.println(Thread.currentThread().getName()+" "+count);
						count++;
					}
				}
			}
		}
	};
	static Runnable evenTask = new Runnable() {
		@Override
		public void run() {
			while (count<100){
				synchronized (mutex){
					if((count&1)==0){
						System.out.println(Thread.currentThread().getName()+" "+count);
						count++;
					}
				}
			}
		}
	};
	public static void main(String[] args) {
		Thread oddThread = new Thread(oddtask, "奇数");
		Thread evenThread = new Thread(evenTask, "偶数");
		oddThread.start();
		evenThread.start();
	}
}
