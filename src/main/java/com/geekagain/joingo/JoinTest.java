package com.geekagain.joingo;

public class JoinTest {
	public static Object object = new Object();
	public static void main(String[] args) throws InterruptedException {
		Runnable target;
		Thread t = new Thread(()->{
			Thread thread = Thread.currentThread();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (thread){
				thread.notifyAll();
				try {
					thread.wait(30000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
