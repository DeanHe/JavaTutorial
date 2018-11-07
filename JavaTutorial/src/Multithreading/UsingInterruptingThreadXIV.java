package Multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.sql.rowset.RowSetWarning;

public class UsingInterruptingThreadXIV {

	public static void main(String[] args) throws InterruptedException{
		System.out.println("Starting.");
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<?> future = executorService.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				Random random = new Random();
				for(int i = 0; i < 1E8; i++){
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted!");
						break;
					}
					Math.sin(random.nextDouble());
				}
				return null;
			}
		});
		
		Thread.sleep(500);
		future.cancel(true);
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("Finished.");
	}
}
