package logic;

import gui.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.Main;

public class HumanListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		PropertiesGetter propertiesGetter = new PropertiesGetter();
		EndGameChecker endGameChecker = new EndGameChecker();
		Board board = new Board();
		JButton button = (JButton) e.getSource();
		int place = Main.buttons.indexOf(button);
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		int row = place / boardSize;
		int col = place % boardSize;

		board.displayMove(button, "O");
		Main.buttonValues[row][col] = 2;
		
		if (endGameChecker.checkWin(row, col)) {
			JOptionPane.showMessageDialog(board, propertiesGetter.getProperty("displayMessageHumanWin"));
			board.reset();
			return;
		}
		
		if (endGameChecker.checkDraw()) {
			JOptionPane.showMessageDialog(board, propertiesGetter.getProperty("displayMessageDraw"));
			board.reset();
			return;
		}
		
		Main.computerPlayer.autoPlay();
	}
}
