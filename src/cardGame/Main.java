package cardGame;

import java.awt.*;

public class Main {
	//Creates a static reference to the GameWindow() class.
	static GameWindow window = new GameWindow();

	public static void main(String[] args) {
		//Runs the init() method.
		window.Init("Card Game", new Dimension(600, 600));
	}
}
