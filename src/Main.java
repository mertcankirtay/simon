import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class Main extends JFrame {

	private JPanel header;
	private JPanel entrance;
	private JPanel content;
	private JLabel scoreLabel;
	private JLabel score;
	private JLabel image;
	private JButton play;
	private JButton yellow;
	private JButton blue;
	private JButton red;
	private JButton green;
	private JButton start;
	private JButton back;
	private int[] plays;
	private int[] playInput;
	private int counter = 0;

	public Main() {
		setLayout(null);
		playInput = new int[5];

		scoreLabel = new JLabel("Score: ");
		score = new JLabel("0");

		start = new JButton("Start");
		start.setEnabled(false);
		start.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				game();
			}
		});

		back = new JButton("Back");
		back.setEnabled(false);
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				content.setVisible(false);
				entrance.setVisible(true);
				back.setEnabled(false);
				start.setEnabled(false);
				play.setEnabled(false);
			}
		});

		header = new JPanel();
		header.setSize(300, 50);
		header.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.red));
		header.add(start);
		header.add(scoreLabel);
		header.add(score);
		header.add(back);

		image = new JLabel(new ImageIcon("icon.jpg"));
		image.setPreferredSize(new Dimension(100, 100));

		play = new JButton("Play", new ImageIcon("play.png"));
		play.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				back.setEnabled(true);
				start.setEnabled(true);
				entrance.setVisible(false);
				add(content);
			}
		});
		play.setPreferredSize(new Dimension(205, 133));
		play.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		play.setBorder(null);

		entrance = new JPanel();
		entrance.setSize(300, 450);
		entrance.setLocation(0, 50);
		entrance.setBackground(Color.black);
		entrance.add(image);
		entrance.add(play);

		yellow = new JButton();
		yellow.setBackground(Color.yellow);
		yellow.setBorder(null);
		yellow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				playInput[counter] = 0;
				counter++;

				for (int i = 0; i < playInput.length; i++) {
					System.out.print(playInput[i] + " ");
				}

				if (counter == 5) {
					calculateScore();
				}
			}
		});

		blue = new JButton();
		blue.setBackground(Color.blue);
		blue.setBorder(null);
		blue.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				playInput[counter] = 1;
				counter++;

				for (int i = 0; i < playInput.length; i++) {
					System.out.print(playInput[i] + " ");
				}

				if (counter == 5) {
					calculateScore();
				}
			}
		});

		red = new JButton();
		red.setBackground(Color.red);
		red.setBorder(null);
		red.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				playInput[counter] = 2;
				counter++;

				for (int i = 0; i < playInput.length; i++) {
					System.out.print(playInput[i] + " ");
				}

				if (counter == 5) {
					calculateScore();
				}
			}
		});

		green = new JButton();
		green.setBackground(Color.green);
		green.setBorder(null);
		green.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				playInput[counter] = 3;
				counter++;

				for (int i = 0; i < playInput.length; i++) {
					System.out.print(playInput[i] + " ");
				}

				if (counter == 5) {
					calculateScore();
				}
			}
		});

		content = new JPanel();
		content.setLayout(new GridLayout(2, 2));
		content.setSize(300, 450);
		content.setLocation(0, 50);
		content.setBackground(Color.black);
		content.add(yellow);
		content.add(blue);
		content.add(red);
		content.add(green);

		add(header);
		add(entrance);

		setTitle("Simon");
		setSize(300, 500);
		setLocation(300, 100);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Main();
	}

	public void game() {
		start.setEnabled(false);

		Random random = new Random();

		plays = new int[5];

		for (int i = 0; i < plays.length; i++) {
			plays[i] = random.nextInt(4);
			System.out.print(plays[i] + " ");
		}

		System.out.println();

		System.out.println("Plays Created Successfully");

		for (int i = 0; i < plays.length; i++) {
			switch (plays[i]) {
			case 0:
				addBorder(yellow);
				break;

			case 1:
				addBorder(blue);
				break;

			case 2:
				addBorder(red);
				break;

			case 3:
				addBorder(green);
				break;
			}
		}
	}

	public void addBorder(JButton button) {
		try {
			Color oldColor = button.getBackground();
			button.setBackground(Color.black);
			repaint();
			Thread.sleep(1000);
			button.setBackground(oldColor);
			repaint();
		} catch (Exception e) {
			System.out.println("Coloring Error!");
		}
	}

	public void calculateScore() {

		if (checkPlays()) {
			int oldScore = Integer.parseInt(score.getText());
			int newScore = oldScore + 10;

			score.setText(newScore + "");

			JOptionPane.showMessageDialog(null, "Congralations +10 points", "Congrats", JOptionPane.OK_OPTION);
		} else {
			JOptionPane.showMessageDialog(null, "Game Over!", "Game Over", JOptionPane.ERROR_MESSAGE);
		}

		counter = 0;
		start.setEnabled(true);
	}

	public boolean checkPlays() {
		for (int i = 0; i < plays.length; i++) {
			if (plays[i] != playInput[i]) {
				return false;
			}
		}

		return true;
	}
}
