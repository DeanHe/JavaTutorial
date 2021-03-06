package Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UsingSemaphoreXII {

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		for(int i = 0; i < 200; i++){
			executorService.submit(new Runnable() {
				
				@Override
				public void run() {
					Connection.getInstance().connect();				
				}
			});
		}
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.DAYS);
	}
}

class Connection {
	private static Connection instance = new Connection();	
	private int connections = 0;
	private Semaphore lock = new Semaphore(10, true);
	
	private Connection() {
		
	}
	
	public static Connection getInstance(){
		return instance;
	}
	public void connect(){
		try {
			lock.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			doConnect();
		} finally {
			lock.release();
		}
	}
	private void doConnect(){
		
		synchronized (this) {
			connections++;
			System.out.println("current connections: " + connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized (this) {
			connections--;
		}
		
		lock.release();
	}
}