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
			Client c[] = new Client[4];
			for(int i=0; i<4; i++)
				c[i] = new Client(i);
			for(int i=0; i<4; i++)
    {
      noOfWeightPlates.clear();
      noOfWeightPlates.put(WeightPlateSize.SMALL_3KG, rnd.nextInt(10));
      noOfWeightPlates.put(WeightPlateSize.MEDIUM_5KG, rnd.nextInt(10));
      noOfWeightPlates.put(WeightPlateSize.LARGE_10KG, rnd.nextInt(10));
      //according to pdf number of plates for each exercise should between 0 and 10, aka rnd.nextInt(10) + 0
      for(int j=0; j<rnd.nextInt(20) + 15; j++)
        c[i].addExercise(Exercise.generateRandom(noOfWeightPlates));
    }


		for(int i=0; i<4; i++)
		{
			for(int j=0; i<c[i].getRoutine().size()-1; j++)
			{
				System.out.println(c[i].getRoutine().size());
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
