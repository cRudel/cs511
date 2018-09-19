package Assignment2;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.*;

public class test implements Runnable{
	private static Random rnd = new Random();
	ExecutorService executor = Executors.newFixedThreadPool(10);

	public test(){}
	public static void main(String[] args)
	{
		Thread thread = new Thread(new test());
		thread.start();
		try{
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}

	}	

public void run()
{
		executor.execute(new Runnable() 
		{
			public void run() {
					System.out.println("I am an asynchronous task");
			}
		});
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
