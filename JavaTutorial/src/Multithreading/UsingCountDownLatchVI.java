package Multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor2 implements Runnable {
	private CountDownLatch countDownLatch;
	public Processor2(CountDownLatch latch) {
		this.countDownLatch = latch;
	}
	@Override
	public void run() {
		System.out.println("start process.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
		countDownLatch.countDown();
	}
}

public class UsingCountDownLatchVI {
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		for(int i = 0; i < 3; i++){
			executorService.submit(new Processor2(latch));
		}
		try {
			latch.await();
		} catch (InterruptedException e) {

		}
		System.out.println("completed.");
		
	}
}
