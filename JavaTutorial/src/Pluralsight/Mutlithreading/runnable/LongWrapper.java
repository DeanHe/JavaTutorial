package Pluralsight.Mutlithreading.runnable;

public class LongWrapper {
	private long l;
	private Object lock = new Object();

	public LongWrapper(long l) {
		this.l = l;
	}

	public long getValue() {
		synchronized (lock) {
			return l;
		}
	}

	public void incrementValue() {
		synchronized (lock) {
			l = l + 1;
		}		
	}
}
