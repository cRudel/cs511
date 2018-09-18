package Assignment2;
import Assignment2.WeightPlateSize;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.concurrent.*;

public class Gym implements Runnable
{
	private static final int GYM_SIZE = 30;
	private static final int GYM_REGISTERED_CLIENTS = 10000;
	private Map<WeightPlateSize, Integer> noOfWeightPlates = new LinkedHashMap<>();
	private Set<Integer> clients = new HashSet<>(); //generating fresh client ids
	private Client[] people = new Client[GYM_REGISTERED_CLIENTS];
	private ExecutorService executor = Executors.newFixedThreadPool(10);
	private Semaphore[] weights = new Semaphore[] {new Semaphore(75), new Semaphore(90), new Semaphore(110)}; //75 10kg, 90 5kg, 110 3kg available
	private Semaphore[] apparatuses = new Semaphore[] {new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1),new Semaphore(1)};
	//semaphores for legpress, barbell, hacksquat, legextension, legcurl, latpulldown, pecdeck, & cablecrossover
	private Semaphore gym = new Semaphore(GYM_SIZE);

	private Random rnd = new Random();


	public void run()
	{
		for(int i=0; i<GYM_REGISTERED_CLIENTS; i++)
		{
			clients.add(Integer.valueOf(i));
			people[i] = new Client(i);
		}		



	}

	public Gym()
	{
	
	}
}
