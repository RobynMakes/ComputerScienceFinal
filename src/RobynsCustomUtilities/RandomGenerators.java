package RobynsCustomUtilities;

/**
 * @author Robyn
 * @version 1.4
 *
 */
public class RandomGenerators {
	/**
	 * @return A pseudorandom double between 0.0 and 1.0.
	 * Generates a pseudorandom number between 0.0 and 1.0.
	 * @see CustomMaths#round(double, double)
	 */
	public static double randomNum() {
		double randomNum = Math.random();
		
		return CustomMaths.round(randomNum, 2);
	}
	
	/**
	 * @param max The maximum range.
	 * @return A pseudorandom double between 1 and max, rounded to the hundredth place.
	 * Generates a pseudorandom number between 0.0 and a given maximum.
	 * @see CustomMaths#round(double, double)
	 */
	public static double randomNum(int max) {
		int min = 1;
		int range = max - min + 1;
		double randomNum = (Math.random()*range) + min;
		
		return CustomMaths.round(randomNum, 2);
	}
	
	/**
	 * @param max The maximum range.
	 * @param min The bottom range.
	 * @return A pseudorandom double between min and max, rounded to the hundredth place.
	 * Generates a pseudorandom number between a given minimum and a given exclusive maximum.
	 * @see CustomMaths#round(double, double)
	 */
	public static double randomNum(int max, int min) {
		int range = max - min + 1;
		double randomNum = (Math.random()*range) + min;
		
		return CustomMaths.round(randomNum, 2);
	}
	
	/**
	 * @param max The maximum range.
	 * @param min The minimum range.
	 * @param round The amount of decimal places to round to.
	 * @return A pseudorandom double between a given minimum and a given maximum, rounded to a given decimal place.
	 * Generates a pseudorandom number between a given minimum and a given minimum, rounded to a given decimal place.
	 * @see CustomMaths#round(double, double)
	 */
	public static double randomNum(int max, int min, int round) {
		int range = max - min + 1;
		double randomNum = (Math.random()*range) + min;
		
		return CustomMaths.round(randomNum, round);
	}
}
