package gomoku.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gomoku.logic.PropertiesGetter;
import gomoku.Main;

public class Board extends JFrame {

	public void draw() {
		setTitle(new PropertiesGetter().getProperty("boardTitle"));
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(650, 600);
		setLocation(dimension.width / 2 - this.getWidth() / 2, dimension.height / 2 - this.getHeight() / 2);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		JPanel mainPanel = new JPanel(new GridLayout(boardSize, boardSize));
		for (JButton button : Main.buttons) {
			mainPanel.add(button);
		}
		setContentPane(mainPanel);

		setVisible(true);
		displayFirstMove();
	}

	/**
	 * display the first move by computer
	 */
	public void displayFirstMove() {
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		int center = (boardSize / 2) * (boardSize + 1);
		Main.buttonValues[boardSize / 2][boardSize / 2] = 1;
		displayMove(Main.buttons.get(center), "X");
	}
	
	/**
	 * display the move 'O' or 'X' on the board
	 */
	public void displayMove(JButton button, String move) {
		button.setMargin(new Insets(0, 0, 0, 0));
		Font font = new Font(button.getFont().getFontName(), Font.BOLD, button.getHeight() - 1);
		button.setFont(font);
		button.setText(move);
		button.setEnabled(false);
	}

	/**
	 * reset to start a new game
	 */
	public void reset() {
		for (JButton button : Main.buttons) {
			button.setText("");
			button.setEnabled(true);
		}

		for (int[] row : Main.buttonValues) {
			for (int i = 0; i < row.length; i++) {
				row[i] = 0;
			}
		}
		
		Main.shuffleMoves();
		displayFirstMove();
	}
	
	/**
	 * suffle dfe
	 */
}
