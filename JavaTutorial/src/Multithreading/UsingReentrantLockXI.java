package Multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
	private int balance = 10000;

	public void deposit(int amount) {
		balance += amount;
	}

	public void withdraw(int amount) {
		balance -= amount;
	}

	public int getBalance() {
		return balance;
	}

	public static void transfer(Account acc1, Account acc2, int amount) {
		acc1.withdraw(amount);
		acc2.deposit(amount);
	}
}

class Demo {
	private Account acc1 = new Account();
	private Account acc2 = new Account();

	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLock(Lock firstLock, Lock secondLock) throws InterruptedException {
		while (true) {
			// Acquire locks
			boolean getFirstLock = false;
			boolean getSecondLock = false;
			try {
				getFirstLock = firstLock.tryLock();
				getSecondLock = secondLock.tryLock();
			} finally {
				if (getFirstLock && getSecondLock) {
					return;
				}
				if (getFirstLock) {
					firstLock.unlock();
				}
				if (getSecondLock) {
					secondLock.unlock();
				}
			}
			// Locks not acquired
			Thread.sleep(1);
		}
	}

	public void firstThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			acquireLock(lock1, lock2);
			try {
				Account.transfer(acc1, acc2, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void secondThread() throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 10000; i++) {
			acquireLock(lock2, lock1);
			try {
				Account.transfer(acc2, acc1, random.nextInt(100));
			} finally {
				lock1.unlock();
				lock2.unlock();
			}
		}
	}

	public void finish() {
		System.out.println("Account 1 balance: " + acc1.getBalance());
		System.out.println("Account 2 balance: " + acc2.getBalance());
		System.out.println("Total balance: " + (acc1.getBalance() + acc2.getBalance()));
	}
}

public class UsingReentrantLockXI {
	public static void main(String[] args) {
		final Demo demo = new Demo();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					demo.firstThread();
				} catch (InterruptedException e) {

				}

			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					demo.secondThread();
				} catch (InterruptedException e) {

				}

			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
			demo.finish();
		} catch (InterruptedException e) {

		}
	}
}
