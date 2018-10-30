package Multithreading;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UsingCyclicBufferX {
	private final String[] buffer;
	private final int capacity;
	
	private int front;
	private int rear;
	private int count;
	
	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();
	
	public UsingCyclicBufferX(int capactiy){
		this.capacity = capactiy;
		buffer = new String[capactiy];
	}
	
	public void deposit(String data) throws InterruptedException {
		lock.lock();
		try {
			while(count == capacity){
				notFull.await();
			}
			buffer[rear] = data;
			rear = (rear + 1) % capacity;
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public String fetch() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			String res = buffer[front];
			front = (front + 1) % capacity;
			count--;
			notFull.signal();
			return res;
		} finally {
			lock.unlock();
		}
	}
}
