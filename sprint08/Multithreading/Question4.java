package sprint08.Multithreading;

//	Fix the problem with the preloaded implementation of the class MyThread.
//	
//	Test starts both threads with the code:
//	
//	        MyThreads.t1.start();
//	        MyThreads.t2.start();
//	
//	The goal of each thread is to update m and n fields and not switch between threads while loop is executed.
//	
//	You need to get an output like this:
//	
//	Thread1 n = 0
//	Thread1 n = 1
//	Thread1 n = 2
//	Thread1 n = 3
//	Thread1 n = 4
//	Thread2 m = 0
//	Thread2 m = 1
//	Thread2 m = 2
//	Thread2 m = 3
//	Thread2 m = 4
//	Thread2 n = 5
//	Thread2 n = 6
//	Thread2 n = 7
//	Thread2 n = 8
//	Thread2 n = 9
//	Thread2 success!
//	Thread1 m = 5
//	Thread1 m = 6
//	Thread1 m = 7
//	Thread1 m = 8
//	Thread1 m = 9
//	Thread1 success!
//	Please, don't change actions that change variables or print output within run() methods. Use only thread synchronization assets.

class MyThreads {
	public final static Object den = new Object();
	public final static Object ada = new Object();

	public static int n;
	public static int m;

	public static Thread t1 = new Thread(() -> {
		try {
			synchronized (den) {
				for (int i = 0; i < 5; i++, n++)
					System.out.println("Thread1 n = " + n);
				Thread.yield();
			}

			synchronized (ada) {
				ada.notifyAll();
				ada.wait(1000);
				synchronized (den) {
					for (int i = 0; i < 5; i++, m++)
						System.out.println("Thread1 m = " + m);
					System.out.println("Thread1 success!");
				}
			}
		} catch (InterruptedException e) {
		}
	});

	public static Thread t2 = new Thread(() -> {
		try {
			synchronized (ada) {
				ada.wait(1000);
				for (int i = 0; i < 5; i++, m++)
					System.out.println("Thread2 m = " + m);
				synchronized (den) {
					for (int i = 0; i < 5; i++, n++)
						System.out.println("Thread2 n = " + n);
					System.out.println("Thread2 success!");
				}
				ada.notifyAll();
			}
		} catch (InterruptedException e) {
		}
	});
}