/*
*		Christopher Rudel & Sean Hill
*		WeightPlateSize.java
*/
package Assignment2;

import java.util.Random;
	public enum WeightPlateSize
  {
		SMALL_3KG, MEDIUM_5KG, LARGE_10KG;

		private static Random rnd = new Random();

		public static WeightPlateSize rndWeight()
		{
			int n = rnd.nextInt(3) + 1;

			if(n==1)
				return WeightPlateSize.SMALL_3KG;
			else if(n==2)
				return WeightPlateSize.MEDIUM_5KG;
			else
				return WeightPlateSize.LARGE_10KG;
		}
  }

