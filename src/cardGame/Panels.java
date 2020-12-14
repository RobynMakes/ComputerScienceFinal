package cardGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Panels {
	// mainMenu() is the first JPanel that will run when the program in run.
	public JPanel[] mainMenu(JFrame frame) {
		/* Declaring a JPanel Array, two JPanels (to hold components), & a new GridBagConstraints()
		 * GridBagContraints() allow for auto resizing grid based layout.
		 */
		JPanel[] panels = new JPanel[2];
		JPanel buttonPanel = new JPanel(new GridBagLayout());
		JPanel titlePanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		// Making and assigning elements to titlePanel.
		JLabel title = new JLabel("Cards");
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		titlePanel.add(title, c);

		// Making and assigning elements to buttonPanel.
		JButton playButton = new JButton("Run");
		/* Adding an ActionListener()
		 * An ActionListener() attached to a JButton will run whenever the JButton
		 * is clicked.
		 */
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Panels.remove(frame);
				try {
					Panels.load(playPanel(frame), frame);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		/* Assigning the GridBagConstraints() constraints
		 * This has to be done whenever one wants to add a component to the GridBagLayout().
		 */
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		buttonPanel.add(playButton, c);

		JButton infoButton = new JButton("Info");
		infoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Panels.remove(frame);
				Panels.load(infoPanel(), frame);
			}

		});
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		buttonPanel.add(infoButton, c);

		JButton quitButton = new JButton("Quit");
		// This ActionListener will close the JFrame() when a button is clicked.
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		buttonPanel.add(quitButton, c);

		// Assigning JPanels to array.
		panels[0] = titlePanel;
		panels[1] = buttonPanel;

		// Returning JPanel Array.
		return panels;
	}

	/*
	 * This panel does most of the computing.
	 * This took a while to code. *-*
	 */
	public JPanel[] playPanel(JFrame frame) {
		/*
		 * Take note of the two ArrayList()s. These lists will hold the cards that have been
		 * randomly chosen.
		 */
		String cardVal = Cards.pickVal();
		String cardSuit = Cards.pickSuit();
		ArrayList<String> valuesDrawn = new ArrayList<String>();
		ArrayList<String> suitsDrawn = new ArrayList<String>();

		/* 
		 * This checks the card that was randomly chosen and displays the corresponding image.
		 * This took the longest to get to work.
		 */
		BufferedImage inputImage = null;

		try {
			inputImage = ImageIO.read(getClass().getResource("/images/" + cardVal + cardSuit + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image outputImage = inputImage.getScaledInstance(100, 150, BufferedImage.SCALE_DEFAULT);

		ImageIcon icon = new ImageIcon(outputImage);
		
		// Adds the first generated card value and suit to the appropriate ArrayList().
		valuesDrawn.add(cardVal);
		suitsDrawn.add(cardSuit);
		
		/*
		 * First Panel Array!
		 * Contains
		 * 1. A textBoxPanel that will display the cards that have been drawn already.
		 * 2. A playArea that will show an image of the card drawn along with a JButton that will
		 * 		draw the next card.
		 */
		JPanel[] panels = new JPanel[2];
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel textBoxPanel = new JPanel(new GridBagLayout());
		JTextArea textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		textArea.setText("Cards Drawn: ");
		textArea.setText(textArea.getText() + " " + valuesDrawn.get(0) + suitsDrawn.get(0));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textBoxPanel.add(textArea, c);
		
		JPanel playArea = new JPanel(new GridBagLayout());
		JLabel card1 = new JLabel(icon);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		playArea.add(card1, c);
		
		JButton next = new JButton("Next Card");
		/*
		 * This ActionLister() will...
		 * 1. Generate a new value and suit.
		 * 2. Add the new value and suit to the appropriate ArrayList().
		 * 3. Show the image that corresponds to the new value and suit.
		 * 4. Update the text area.
		 * 5. Run the "checkforsuits()" method. (This method is located in the Cards.java file).
		 */
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String newCardVal = Cards.pickVal();
				String newCardSuit = Cards.pickSuit();

				valuesDrawn.add(newCardVal);
				suitsDrawn.add(newCardSuit);

				BufferedImage newImageInput = null;
				try {
					newImageInput = ImageIO
							.read(getClass().getResource("/images/" + newCardVal + newCardSuit + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				}

				Image newImage = newImageInput.getScaledInstance(100, 150, BufferedImage.SCALE_DEFAULT);

				textArea.setText(textArea.getText() + " " + newCardVal + newCardSuit);
				icon.setImage(newImage);
				playArea.repaint();
				Cards.checkForSuits(valuesDrawn, suitsDrawn, frame);
			}

		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		playArea.add(next, c);

		panels[0] = playArea;
		panels[1] = textBoxPanel;

		// Returning JPanel Array.
		return panels;
	}

	// TODO Code Info Panel.
	public JPanel[] infoPanel() {
		return null;
	}

	// This method adds the given JPanel array to the given JFrame.
	public static void load(JPanel[] panel, JFrame frame) {
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		int gridx = 0;
		int gridy = 0;
		for (int i = 0; i < panel.length; i++) {
			c.fill = GridBagConstraints.BOTH;
			c.gridx = gridx;
			c.gridy = gridy;
			frame.add(panel[i], c);
			gridy++;
		}
	}

	// This method will remove any JPanel present on the targetFrame.
	public static void remove(JFrame targetFrame) {
		targetFrame.getContentPane().removeAll();
		targetFrame.repaint();
	}

	// This winPanel will show how many cards were drawn and what those cards were. 
	public static JPanel[] winPanel(ArrayList<String>suitsDrawn, ArrayList<String>valuesDrawn) {
		JPanel[] panels = new JPanel[1];
		GridBagConstraints c = new GridBagConstraints();
		
		JPanel panel = new JPanel(new GridBagLayout());
		JTextArea winText = new JTextArea();
		winText.setSize(5, 20);
		winText.setText("It has taken the computer " + suitsDrawn.size() + " to get one card of every suit."
				+ "\nThe cards it has picked was... \n");
		for (int i = 0; i <= suitsDrawn.size() - 1; i++) {
			winText.setText(winText.getText() + valuesDrawn.get(i) + suitsDrawn.get(i) + " ");
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(winText, c);
		
		panels[0] = panel;
		
		return panels;
	}
}
