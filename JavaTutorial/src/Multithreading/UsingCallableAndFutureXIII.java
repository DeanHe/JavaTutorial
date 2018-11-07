package Multithreading;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UsingCallableAndFutureXIII {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
			Future<Integer> future = executorService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					Random random = new Random();
					int duration = random.nextInt(4000);
					if(duration > 2000){
						throw new IOException("sleep too long!");
					}
					System.out.println("Starting...");
					Thread.sleep(duration);
					System.out.println("Finished");
					
					return duration;
				}
			});
		executorService.shutdown();
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("result is " + future.get());
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
	}
}
