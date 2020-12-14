package cardGame;

import java.util.ArrayList;
import javax.swing.JFrame;

public class Cards {
	
	// This method generates a card value from Ace to King.
	public static String pickVal() {
		String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		
		String returnCard = cards[(int)(Math.random() * (12 - 0 + 1) + 0)];
		
		System.out.print(returnCard);
		
		return returnCard;
	}
	
	// This method generates a card suit from Hearts to Spades.
	public static String pickSuit() {
		String[] suits = {"H", "D", "C", "S"};
		
		String returnCard = suits[(int)(Math.random() * (3 - 0 + 1) + 0)];
		
		System.out.println(returnCard);
		
		return returnCard;
	}

	// Checks if all possible suits have been drawn and displays winPanel() if they have.
	public static void checkForSuits(ArrayList<String> valuesDrawn, ArrayList<String> suitsDrawn, JFrame frame) {
		boolean heart = false;
		boolean diamond = false;
		boolean club = false;
		boolean spade = false;
		
		for (int i = 0; i <= suitsDrawn.size()-1; i++) {
			if (suitsDrawn.get(i) == "H") {
				heart = true;
			}
			else if (suitsDrawn.get(i) == "D") {
				diamond = true;
			}
			else if (suitsDrawn.get(i) == "C") {
				club = true;
			}
			else if (suitsDrawn.get(i) == "S") {
				spade = true;
			}
			else {
				break;
			}
			
			if (heart == true && diamond == true && club == true && spade == true) {
				Panels.remove(frame);
				Panels.load(Panels.winPanel(suitsDrawn, valuesDrawn), frame);
			}
		}
		
	}
}
