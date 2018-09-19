/*
*		Christopher Rudel & Sean Hill
*		Exercise.java
*/
package Assignment2;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class Exercise
{
	private ApparatusType at;
	private Map<WeightPlateSize, Integer> weight;
	private int duration;
	private static Random rnd = new Random();

	public Exercise(ApparatusType at, Map<WeightPlateSize, Integer> weight, int duration)
	{
		this.at = at;
		this.weight = weight;
		this.duration = duration;
	}

	public static Exercise generateRandom(Map<WeightPlateSize, Integer> weight)
	{
		Exercise ex = new Exercise(ApparatusType.rndApparatus(), weight, rnd.nextInt(10)+1); //the duration is in milliseconds
		//The pdf also did not specify how long the exercise should be 

		return ex;
	}

	void printExercise()
	{
		System.out.print("(" + this.at + ", ");
		System.out.print(this.weight.toString());
		System.out.println(", " + duration);
	}
}		
