package Multithreading;

import java.util.*;

public class UsingLocksDemoIV {

	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> ls1 = new ArrayList<>();
	private List<Integer> ls2 = new ArrayList<>();
	
	public void stageOne(){
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
			ls1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo(){
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {

			}
			ls2.add(random.nextInt(100));
		}
	}
	
	public void process(){
		for(int i = 0; i < 1000; i++){
			stageOne();
			stageTwo();
		}
	}
	
	public static void main(String[] args) {
		UsingLocksDemoIV app = new UsingLocksDemoIV();
		System.out.println("Starting....");
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				app.process();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				app.process();
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("Time takes: " + (end - start));
		System.out.println("ls1 size:" + app.ls1.size() + " ls2 size:" + app.ls2.size());
	}
}
