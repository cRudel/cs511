/*
*		Christopher Rudel & Sean Hill
*		Exercise.java
*/
package Assignment2;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

	public static Exercise generateRandom()
	{
		int dur = rnd.nextInt((10-1) + 1) + 1;
		Map<WeightPlateSize, Integer> m = new LinkedHashMap<>();
		m.put(WeightPlateSize.SMALL_3KG, rnd.nextInt((10-0) + 1));
		m.put(WeightPlateSize.MEDIUM_5KG, rnd.nextInt((10-0) + 1));
		m.put(WeightPlateSize.LARGE_10KG, rnd.nextInt((10-0) + 1));
		Exercise ex = new Exercise(ApparatusType.rndApparatus(), m, dur); //the duration is in milliseconds
		return ex;
	}

	public ApparatusType getApparatus(){
		return this.at;
	}
	
	public Map<WeightPlateSize,Integer> getWeightPlateSizeMap() {
		return weight;
	}

	void printExercise()
	{
		System.out.print("(" + this.at + ", ");
		System.out.print(this.weight.toString());
		System.out.println(", " + duration);
	}
}		
