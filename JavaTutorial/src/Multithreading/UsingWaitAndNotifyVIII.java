package Multithreading;

import java.util.Scanner;

class Processor3 {
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer thread running...");
			wait();
			System.out.println("Producer thread resumed...");
		}
	}
	
	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		
		synchronized (this) {
			System.out.println("Waiting for input...");
			scanner.nextLine();
			System.out.println("input is entered");
			notify();
		}
	}
}
public class UsingWaitAndNotifyVIII {
	public static void main(String[] args) throws InterruptedException {
		final Processor3 processor = new Processor3();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					
				}
				
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					
				}
				
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
}
