package main;

import gui.Board;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import logic.ComputerPlayer;
import logic.FileProcessor;
import logic.HumanListener;
import logic.PropertiesGetter;

public class Main {
	public static List<int[][]> moves;
	public static List<JButton> buttons;
	public static int[][] buttonValues;
	public static ComputerPlayer computerPlayer;

	public static void main(String[] args) {
		moves = new FileProcessor(new PropertiesGetter().getProperty("fileLocation")).readMoves();
		buttons = new ArrayList<JButton>();
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		for (int i = 0; i < boardSize * boardSize; i++) {
			JButton button = new JButton();	
			button.addActionListener(new HumanListener());
			buttons.add(button);
		}
		buttonValues = new int[boardSize][boardSize];
		computerPlayer = new ComputerPlayer();
		new Board().draw();
	}
}
