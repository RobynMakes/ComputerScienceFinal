package cardGame;

import RobynsCustomUtilities.*;

public class Cards {
	
	public static String pickVal() {
		String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		String returnCard = cards[(int) RandomGenerators.randomNum(0, cards.length-1, 1)];
		
		return returnCard;
	}
	
	public static String pickSuit() {
		String[] suits = {"H", "D", "C", "S"};
		
		String returnCard = suits[(int) RandomGenerators.randomNum(0, suits.length-1, 1)];
		
		return returnCard;
	}
}
