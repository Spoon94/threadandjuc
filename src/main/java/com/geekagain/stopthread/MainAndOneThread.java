package com.geekagain.stopthread;

public class MainAndOneThread {

	public static void main(String[] args) {
		Thread thread = new Thread(new ThreadGroup("123"),()->{
			while (true){

			}
		});
		thread.start();
	}
}
