package sprint08.Multithreading;

//	Suppose, you have class from task #1 ParallelCalculator that is able to execute an operation in separate thread. 
//	It uses an implementation of Runnable interface for this.
//	
//	You need to create Accountant class with a static sum(...) method that takes two parameters of type int and returns their sum. 
//	Use ParallelCalculator for this. The sum(...) method doesn't throw any checked exceptions.
//	
//	The sum must be evaluated in a separate thread  (please, do not call run() method of ParallelCalculator. Use start() method on object of type Thread).
//	
//	Using Thread.sleep() method is unwelcomed in this task.

class Accountant {
	public static int sum(int x, int y) {
		ParallelCalculator pCalc = new ParallelCalculator(Integer::sum, x, y);
		Thread thread = new Thread(pCalc);
		thread.start();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return pCalc.result;
	}
}