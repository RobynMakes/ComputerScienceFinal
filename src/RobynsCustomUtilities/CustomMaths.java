package RobynsCustomUtilities;

/**
 * @author Robyn
 * @version 1.1
 * 
 */
public class CustomMaths {
	
	/**
	 * @param value The number that is rounded.
	 * @param d The value that the number is rounded to.
	 * @return The rounded number
	 * Rounds a given number to a given decimal place.
	 */
	public static double round(double value, double d) {
	    if (d < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, d);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
