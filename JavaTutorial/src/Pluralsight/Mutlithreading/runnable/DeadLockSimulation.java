package Pluralsight.Mutlithreading.runnable;

public class DeadLockSimulation {
	public static void main(String[] args) throws InterruptedException {
		DeadLock dl = new DeadLock();
		Runnable r1 = () -> dl.a();
		Runnable r2 = () -> dl.b();
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
