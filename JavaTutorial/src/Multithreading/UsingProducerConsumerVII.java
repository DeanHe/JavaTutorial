package Multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class UsingProducerConsumerVII {

	private BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
	public static void main(String[] args) throws InterruptedException {
		UsingProducerConsumerVII app = new UsingProducerConsumerVII();
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					app.producer();
				} catch (InterruptedException e) {

				}			
			}
		});
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					app.consumer();
				} catch (InterruptedException e) {

				}			
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		
	}
	private void producer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			blockingQueue.put(random.nextInt(100));
		}
	}
	private void consumer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			Thread.sleep(100);
			if(random.nextInt(10) == 0){
				Integer value = blockingQueue.take();
				System.out.println("Taken value: " + value + ".Queue size is: " + blockingQueue.size());
			}
		}
	}
}
