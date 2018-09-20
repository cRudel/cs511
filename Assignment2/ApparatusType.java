/*
*		Christopher Rudel & Sean Hill
*		ApparatusType.java
*/

package Assignment2;
import java.util.Random;

public enum ApparatusType
{
	LEGPRESSMACHINE, BARBELL, HACKSQUATMACHINE, LEGEXTENSIONMACHINE,
	LEGCURLMACHINE, LATPULLDOWNMACHINE, PECDECKMACHINE, 
	CABLECROSSOVERMACHINE;

	private static Random rnd = new Random();

	public static ApparatusType rndApparatus()
	{
		int n = rnd.nextInt((8-1) + 1) + 1;

		//this part is not pretty excuse this 

		if(n==1)
			return ApparatusType.LEGPRESSMACHINE;
		else if(n==2)
			return ApparatusType.BARBELL;
		else if(n==3)
			return ApparatusType.HACKSQUATMACHINE;
		else if(n==4)
			return ApparatusType.LEGEXTENSIONMACHINE;
		else if(n==5)
			return ApparatusType.LEGCURLMACHINE;
		else if(n==6)
			return ApparatusType.LATPULLDOWNMACHINE;
		else if(n==7)
			return ApparatusType.PECDECKMACHINE;
		else
			return ApparatusType.CABLECROSSOVERMACHINE;
	}
}
