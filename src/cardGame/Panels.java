package cardGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Panels {
	/*
	 * mainPanel() is the first JPanel that will
	 * run when the program in run.
	 */
	public JPanel[] mainMenu(JFrame frame) {
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
		playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Panels.remove(frame);
				try {
					Panels.load(playPanel(), frame);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
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

	public JPanel[] playPanel() {
		int texty = 0;
		String cardVal = Cards.pickVal();
		String cardSuit = Cards.pickSuit();
		ArrayList<String> valuesDrawn = new ArrayList<String>();
		ArrayList<String> suitsDrawn = new ArrayList<String>();

		BufferedImage inputImage = null;

		try {
			inputImage = ImageIO.read(getClass().getResource("/images/" + cardVal + cardSuit + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		valuesDrawn.add(cardVal);
		suitsDrawn.add(cardSuit);

		Image outputImage = inputImage.getScaledInstance(100, 150, BufferedImage.SCALE_DEFAULT);

		ImageIcon icon = new ImageIcon(outputImage);

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
		texty++;

		JButton next = new JButton("Next Card");
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
			}

		});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		playArea.add(next, c);
		texty++;

		JLabel text = new JLabel("|This Text Is For Testing Purposes|");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = texty;
		playArea.add(text, c);

		panels[0] = playArea;
		panels[1] = textBoxPanel;

		// Returning JPanel Array.
		return panels;
	}

	// TODO Code Info Panel.
	public JPanel[] infoPanel() {
		return null;
	}

	/*
	 * Will load the given panel.
	 */
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

	public static void remove(JFrame targetFrame) {
		targetFrame.getContentPane().removeAll();
		targetFrame.repaint();
	}
}
