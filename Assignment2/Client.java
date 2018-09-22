/*
*		Christopher Rudel & Sean Hill
*		Client.java
*/
package Assignment2;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Client
{
	private int id;
	private List<Exercise> routine;
	private static Map<WeightPlateSize, Integer> noOfWeightPlates = new LinkedHashMap<>();	
	private static Random rnd = new Random();


	public Client(int id)
	{
		this.id = id;
		this.routine = new ArrayList<Exercise>();
	}

	public void addExercise(Exercise e)
	{
	 	routine.add(e); 
	}

	public int getId(){
		return this.id;
	}

	public static Client generateRandom(int id)
	{
		Client c = new Client(id);
		for(int j=0; j<rnd.nextInt((20-15) + 1) + 15; j++)
      {
        //noOfWeightPlates.clear();
    //    noOfWeightPlates.put(WeightPlateSize.SMALL_3KG, rnd.nextInt((10-0) + 1));
     //   noOfWeightPlates.put(WeightPlateSize.MEDIUM_5KG, rnd.nextInt((10-0) + 1));
      //  noOfWeightPlates.put(WeightPlateSize.LARGE_10KG, rnd.nextInt((10-0) + 1));
		//		System.out.println(noOfWeightPlates.get(WeightPlateSize.SMALL_3KG));
				Exercise e = Exercise.generateRandom();
				c.addExercise(e);

      }

		return c;

	}

	Exercise getRoutineAt(int i)
	{
		return this.routine.get(i);
	}

	List<Exercise> getRoutine()
	{
		return this.routine;
	}
}
