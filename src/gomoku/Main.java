package gomoku;

import gomoku.gui.Board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;

import gomoku.logic.ComputerPlayer;
import gomoku.logic.FileProcessor;
import gomoku.logic.HumanListener;
import gomoku.logic.PropertiesGetter;

public class Main {
	public static List<int[][]> moves;
	public static List<int[][]> movesToShuffle;
	public static List<JButton> buttons;
	public static int[][] buttonValues;
	public static ComputerPlayer computerPlayer;

	public static void main(String[] args) {
		moves = new LinkedList<int[][]>();
		movesToShuffle = new LinkedList<int[][]>();
		new FileProcessor(new PropertiesGetter().getProperty("fileLocation")).readMoves(moves, movesToShuffle);
		buttons = new ArrayList<JButton>();
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		for (int i = 0; i < boardSize * boardSize; i++) {
			JButton button = new JButton();	
			button.addActionListener(new HumanListener());
			buttons.add(button);
		}
		buttonValues = new int[boardSize][boardSize];
		computerPlayer = new ComputerPlayer();
		
		shuffleMoves();
		new Board().draw();
	}
	
	public static void shuffleMoves() {
		moves.removeAll(movesToShuffle);
		Collections.shuffle(movesToShuffle);
		moves.addAll(movesToShuffle);
	}
}
