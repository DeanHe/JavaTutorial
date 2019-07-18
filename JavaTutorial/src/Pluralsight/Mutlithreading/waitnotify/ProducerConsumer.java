  package Pluralsight.Mutlithreading.waitnotify;

import java.util.Random;

public class ProducerConsumer {

	private static Object lock = new Object();
	private static int[] buffer;
	private static int count;
	private static Random random = new Random();

	static class Producer {
		void produce() {
			synchronized (lock) {
				while (isFull(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				buffer[count] = random.nextInt(100);
				System.out.println("produce " + buffer[count]);
				count++;
				lock.notify();
			}

		}
	}

	static class Consumer {
		void consume() {
			synchronized (lock) {
				while (isEmpty(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				count--;
				System.out.println("consume " + buffer[count]);
				buffer[count] = 0;;
				lock.notify();
			}

		}
	}

	static boolean isEmpty(int[] buffer) {
		return count == 0;
	}

	static boolean isFull(int[] buffer) {
		return count == buffer.length;
	}

	public static void main(String... strings) throws InterruptedException {

		buffer = new int[10];
		count = 0;

		Producer producer = new Producer();
		Consumer consumer = new Consumer();

		Runnable produceTask = () -> {
			for (int i = 0; i < 50; i++) {
				producer.produce();
			}
			System.out.println("Done producing");
		};
		Runnable consumeTask = () -> {
			for (int i = 0; i < 45; i++) {
				consumer.consume();
			}
			System.out.println("Done consuming");
		};

		Thread consumerThread = new Thread(consumeTask);
		Thread producerThread = new Thread(produceTask);

		consumerThread.start();
		producerThread.start();

		consumerThread.join();
		producerThread.join();

		System.out.println("Data in the buffer: " + count);
	}
}
