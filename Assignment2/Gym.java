/*
*		Christopher Rudel & Sean Hill
*		Gym.java
*/
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
	private static final int NUM_SMALL = 110;
	private static final int NUM_MEDIUM = 90;
	private static final int NUM_LARGE = 75;

	private static final int GYM_SIZE = 30;
	private static final int GYM_REGISTERED_CLIENTS = 10000;
	private Map<WeightPlateSize, Integer> noOfWeightPlates = new LinkedHashMap<>();
	private Set<Client> clients = new HashSet<Client>(); //generating fresh client ids
	private Client[] people = new Client[GYM_REGISTERED_CLIENTS];
	private ExecutorService executor = Executors.newFixedThreadPool(30);//basically the semaphore for entering the gym, only of size 30
	private Semaphore[] weights = new Semaphore[] {new Semaphore(75), new Semaphore(90), new Semaphore(110)}; //75 10kg, 90 5kg, 110 3kg available
	private Semaphore[] apparatuses = new Semaphore[] {new Semaphore(5),new Semaphore(5),new Semaphore(5),new Semaphore(5),new Semaphore(5),new Semaphore(5),new Semaphore(5),new Semaphore(5)};
	//semaphores for legpress, barbell, hacksquat, legextension, legcurl, latpulldown, pecdeck, & cablecrossover

	private Map<WeightPlateSize, Integer> numberOfWeights; //remaining weights
	private Map<WeightPlateSize, Semaphore> weight_perms;
	private Map<ApparatusType, Semaphore> app_perms;
	int numSmallWeights;
	int numMedWeights;
	int numLargeWeights;



	private Random rnd = new Random();
	//the random number generator in java will be: rnd.nextInt((max-min) + 1) + min; . I don't know why its so complex

	public Gym()
	{		
		weight_perms.put(WeightPlateSize.LARGE_10KG, new Semaphore(75));
		weight_perms.put(WeightPlateSize.MEDIUM_5KG, new Semaphore(90));
		weight_perms.put(WeightPlateSize.SMALL_3KG, new Semaphore(110));

		app_perms.put(ApparatusType.LEGPRESSMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.BARBELL, new Semaphore(5));
		app_perms.put(ApparatusType.HACKSQUATMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.LEGCURLMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.LEGEXTENSIONMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.LATPULLDOWNMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.PECDECKMACHINE, new Semaphore(5));
		app_perms.put(ApparatusType.CABLECROSSOVERMACHINE, new Semaphore(5));

		numberOfWeights.put(WeightPlateSize.SMALL_3KG, NUM_SMALL);
		numberOfWeights.put(WeightPlateSize.MEDIUM_5KG, NUM_MEDIUM);
		numberOfWeights.put(WeightPlateSize.LARGE_10KG, NUM_LARGE);


	}

	public void run()
	{		
		final Semaphore appMutex = new Semaphore(1);
		final Semaphore smallWeightMutex = new Semaphore(1);
		final Semaphore medWeightMutex = new Semaphore(1); 
		final Semaphore largeWeightMutex = new Semaphore(1); 


		for(int i=0; i<GYM_REGISTERED_CLIENTS; i++)
		{
			clients.add(new Client(i));
			people[i] = new Client(i);
		}	

		for(int i=0; i<GYM_REGISTERED_CLIENTS; i++)
		{
	   	  noOfWeightPlates.clear();
  		  noOfWeightPlates.put(WeightPlateSize.SMALL_3KG, rnd.nextInt((10-0) + 1));
    	  noOfWeightPlates.put(WeightPlateSize.MEDIUM_5KG, rnd.nextInt((10-0) + 1));
    	  noOfWeightPlates.put(WeightPlateSize.LARGE_10KG, rnd.nextInt((10-0) + 1));
			//according to pdf number of plates for each exercise should between 0 and 10
			for(int j=0; j<rnd.nextInt((20-15) + 1) + 15; j++) 
				people[i].addExercise(Exercise.generateRandom(noOfWeightPlates));
			//routines should have 15-20 exercises
		}
		for (Client client : people){
			executor.execute(new Runnable()
			{
					public void run()
					{
						for (Exercise exercise : client.getRoutine()){
							Map<WeightPlateSize, Integer> weightMap = exercise.getWeightPlateSizeMap();
							try{
								appMutex.acquire();
								app_perms.get(exercise.getApparatus()).acquire(); // acquire the semaphore for a specific apparatus in the exercise
								appMutex.release();

								numSmallWeights = weightMap.get(WeightPlateSize.SMALL_3KG);
								numMedWeights = weightMap.get(WeightPlateSize.MEDIUM_5KG);
								numLargeWeights = weightMap.get(WeightPlateSize.LARGE_10KG);
								
								while(true){
									smallWeightMutex.acquire();
									medWeightMutex.acquire();
									largeWeightMutex.acquire();
									
									//check if there are enough weights for us to use
									if(numSmallWeights <= numberOfWeights.get(WeightPlateSize.SMALL_3KG) &&
									numMedWeights <= numberOfWeights.get(WeightPlateSize.MEDIUM_5KG) &&
									numLargeWeights <= numberOfWeights.get(WeightPlateSize.LARGE_10KG) ){
										break; // we break out without releasing so then we can acquire the actual weights

									}
									//if there isn't enough weights for us to use we continue checking.
									//we need to do this so we don't hold up weights forever and it avoids deadlock.
									else{ 
										smallWeightMutex.release();
										medWeightMutex.release();
										largeWeightMutex.release();
									}
								}
								for (int i = 0; i < numSmallWeights; i++){
									weight_perms.get(WeightPlateSize.SMALL_3KG).acquire();
								}				
								numberOfWeights.put(WeightPlateSize.SMALL_3KG,  numberOfWeights.get(WeightPlateSize.SMALL_3KG) - numSmallWeights);
								smallWeightMutex.release(); //other people can now access small weights

								for (int i = 0; i < numMedWeights; i++){
									weight_perms.get(WeightPlateSize.MEDIUM_5KG).acquire();
								}				
								numberOfWeights.put(WeightPlateSize.MEDIUM_5KG,  numberOfWeights.get(WeightPlateSize.MEDIUM_5KG) - numMedWeights);
								medWeightMutex.release(); //other people can now access med weights

								for (int i = 0; i < numLargeWeights; i++){
									weight_perms.get(WeightPlateSize.LARGE_10KG).acquire();
								}				
								numberOfWeights.put(WeightPlateSize.LARGE_10KG,  numberOfWeights.get(WeightPlateSize.LARGE_10KG) - numLargeWeights);
								largeWeightMutex.release(); //other people can now access largeweights

								//use machine now for exercise .get duration

								//put weights back.
							}
							catch(InterruptedException error){
								error.printStackTrace();
							}							
						}
					}
			});
		}

		executor.shutdown();
	}

}
