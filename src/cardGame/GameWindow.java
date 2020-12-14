package cardGame;

import java.awt.*;
import javax.swing.*;

public class GameWindow {
	static JFrame frame = new JFrame();
	Panels panels = new Panels();

	/*
	 * TODO Code Init() method. TODO Overflow Method. DESCRIPTION This method will
	 * create and show the game window. CALLS Panels.load()
	 */
	public void Init(String name, Dimension dim) {
		Panels.load(panels.mainMenu(frame), frame);
		frame.setPreferredSize(dim);
		frame.setName(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
