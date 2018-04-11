package logic;

import javax.swing.JOptionPane;

import main.Main;
import movesmaker.MoveMakerHelper;
import gui.Board;

public class ComputerPlayer {
	public void autoPlay() {
		PropertiesGetter propertiesGetter = new PropertiesGetter();
		EndGameChecker endGameChecker = new EndGameChecker();
		Board board = new Board();
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		int[] placeToPlay = findPlaceToPlay(boardSize);
		if (placeToPlay == null) {
			JOptionPane.showMessageDialog(board, propertiesGetter.getProperty("displayMessageComputerSurrender"));
			board.reset();
		}

		int rowToPlay = placeToPlay[0];
		int colToPlay = placeToPlay[1];
		board.displayMove(Main.buttons.get(rowToPlay * boardSize + colToPlay), "X");
		Main.buttonValues[rowToPlay][colToPlay] = 1;
		if (endGameChecker.checkWin(rowToPlay, colToPlay)) {
			JOptionPane.showMessageDialog(board, propertiesGetter.getProperty("displayMessageComputerWin"));
			board.reset();
		}

		if (endGameChecker.checkDraw()) {
			JOptionPane.showMessageDialog(board, propertiesGetter.getProperty("displayMessageDraw"));
			board.reset();
		}
	}

	public int[] findPlaceToPlay(int boardSize) {
		int[] placeToPlay = null;

		for (int[][] move : Main.moves) {
			for (int i = 0; i <= boardSize - 5; i++) {
				for (int j = 0; j <= boardSize - 5; j++) {
					int[][] positionFromBoard = getPositionFromBoard(i, j);
					if ((placeToPlay = compare(i, j, move, positionFromBoard)) != null) {
						MoveMakerHelper.displayMove(move);
						return placeToPlay;
					}
				}
			}
		}
		return placeToPlay;
	}

	public int[] compare(int row, int col, int[][] move, int[][] positionFromBoard) {
		int[] placeToPlay = new int[2];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (move[i][j] == 4) {
					continue;
				}

				if (move[i][j] == 3 && positionFromBoard[i][j] == 0) {
					placeToPlay[0] = row + i;
					placeToPlay[1] = col + j;
					continue;
				}
				
				if (move[i][j] != positionFromBoard[i][j]) {
					return null;
				}
			}
		}
		return placeToPlay;
	}

	public int[][] getPositionFromBoard(int row, int col) {
		int[][] result = new int[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				result[i][j] = Main.buttonValues[row + i][col + j];
			}
		}
		return result;
	}
}
