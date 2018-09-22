/*
*		Christopher Rudel & Sean Hill
*		Client.java
*/
package Assignment2;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Client
{
	private int id;
	private List<Exercise> routine;

	public Client(int id)
	{
		this.id = id;
		this.routine = new ArrayList<Exercise>();
	}

	public void addExercise(Exercise e)
	{
	 	routine.add(e); 
	}

	public Client generateRandom(int id, Map<WeightPlateSize, Integer> noOfWeightPlates)
	{
		Exercise e = Exercise.generateRandom(noOfWeightPlates);
		this.addExercise(e);
		//why doesnt the first argument provide errors
		return this;

	}

	List<Exercise> getRoutine()
	{
		return this.routine;
	}
}
