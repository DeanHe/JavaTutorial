package multithreading;

public class UsingSynchronizedDemoIII {
	private int count = 0;

	public synchronized void increment() {
		count++;
	}

	public static void main(String[] args) {
		UsingSynchronizedDemoIII app = new UsingSynchronizedDemoIII();
		app.doWork();
	}

	private void doWork() {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					increment();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 100000; i++) {
					increment();
				}

			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
			System.out.println("count is " + count);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
