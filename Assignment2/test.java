package Assignment2;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.*;

public class test implements Runnable{
	private static Random rnd = new Random();
	ExecutorService executor = Executors.newFixedThreadPool(10);
	private static Map<WeightPlateSize, Integer> noOfWeightPlates = new LinkedHashMap<>();

	public test(){}
	public static void main(String[] args)
	{
			Client c[] = new Client[5];
			for(int i=0; i<5; i++){
				c[i] = Client.generateRandom(i);
      }


		for(int i=0; i<5; i++)
		{
			System.out.println("Person ID: " +  i + " has routine length: " + c[i].getRoutine().size());
			for(int j=0; j<c[i].getRoutine().size(); j++)
			{
				c[i].getRoutine().get(j).printExercise();
			}

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
