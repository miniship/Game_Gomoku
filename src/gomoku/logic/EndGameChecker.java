package gomoku.logic;

import gomoku.Main;

public class EndGameChecker {
	public boolean checkDraw() {
		for (int[] row : Main.buttonValues) {
			for (int i = 0; i < row.length; i++) {
				if (row[i] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkWin(int row, int col) {
		if (Main.buttonValues[row][col] == 0) {
			return false;
		}
		int boardSize = Integer.parseInt(new PropertiesGetter().getProperty("boardSize"));
		return (checkWinRow(row, col, boardSize) || checkWinCol(row, col, boardSize)
				|| checkWinBackwardCross(row, col, boardSize) || checkWinForwardCross(row, col, boardSize));
	}

	public boolean checkWinRow(int row, int col, int boardSize) {
		int tempCol = col - 1;
		int count = 0;
		while (tempCol >= 0) {
			if (Main.buttonValues[row][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempCol--;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}

		tempCol = col + 1;
		while (tempCol < boardSize) {
			if (Main.buttonValues[row][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempCol++;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}

	public boolean checkWinCol(int row, int col, int boardSize) {
		int tempRow = row - 1;
		int count = 0;
		while (tempRow >= 0) {
			if (Main.buttonValues[tempRow][col] == Main.buttonValues[row][col]) {
				count++;
				tempRow--;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}

		tempRow = row + 1;
		while (tempRow < boardSize) {
			if (Main.buttonValues[tempRow][col] == Main.buttonValues[row][col]) {
				count++;
				tempRow++;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}

	public boolean checkWinBackwardCross(int row, int col, int boardSize) {
		int tempRow = row - 1;
		int tempCol = col - 1;
		int count = 0;
		while (tempRow >= 0 && tempCol >= 0) {
			if (Main.buttonValues[tempRow][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempRow--;
				tempCol--;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}

		tempRow = row + 1;
		tempCol = col + 1;
		while (tempRow < boardSize && tempCol < boardSize) {
			if (Main.buttonValues[tempRow][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempRow++;
				tempCol++;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}

	public boolean checkWinForwardCross(int row, int col, int boardSize) {
		int tempRow = row - 1;
		int tempCol = col + 1;
		int count = 0;
		while (tempRow >= 0 && tempCol < boardSize) {
			if (Main.buttonValues[tempRow][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempRow--;
				tempCol++;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}

		tempRow = row + 1;
		tempCol = col - 1;
		while (tempRow < boardSize && tempCol >= 0) {
			if (Main.buttonValues[tempRow][tempCol] == Main.buttonValues[row][col]) {
				count++;
				tempRow++;
				tempCol--;
			} else {
				break;
			}
			if (count == 4) {
				return true;
			}
		}
		return false;
	}
}
