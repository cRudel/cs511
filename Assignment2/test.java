package Assignment2;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class test{
	private static Random rnd = new Random();

	public static void main(String[] args)
	{

		Map<WeightPlateSize, Integer> mp = new HashMap<>();
		mp.put(WeightPlateSize.rndWeight(), 0);		
		mp.put(WeightPlateSize.rndWeight(), 2);
		mp.put(WeightPlateSize.rndWeight(), 2);


		Exercise ex = Exercise.generateRandom(mp);

		ex.printExercise();

	}	
}
