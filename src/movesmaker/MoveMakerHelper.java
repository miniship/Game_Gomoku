package movesmaker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MoveMakerHelper {
	/**
	 * make a new move and fill in every cell with value 4
	 */
	public int[][] prepareMove() {
		int[][] move = new int[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				move[i][j] = 4;
			}
		}
		return move;
	}

	/**
	 * find in a list every row which has cell at 'toPlay' with value 3
	 */
	public List<int[]> filter(List<int[]> rows, int toPlay) {
		List<int[]> result = new LinkedList<int[]>();
		for (int[] row : rows) {
			if (row[toPlay] == 3) {
				result.add(row);
			}
		}
		return result;
	}

	/**
	 * fill a row into a move
	 * 
	 * @param type way to fill the row into the matrix 
	 *            1: horizontally 
	 *            2: vertically 
	 *            3: in backward cross
	 *            4: in forward cross
	 */
	public void fillARowIntoAMove(int[][] move, int[] row, int rowToPlay, int colToPlay, int type) {
		if (row.length != 5 || move.length != 5 || move[1].length != 5) {
			return;
		}

		switch (type) {
		case 1:
			for (int i = 0; i < 5; i++) {
				move[rowToPlay][i] = row[i];
			}
			break;

		case 2:
			for (int i = 0; i < 5; i++) {
				move[i][colToPlay] = row[i];
			}
			break;

		case 3:
			for (int i = 0; i < 5; i++) {
				move[i][i] = row[i];
			}
			break;

		case 4:
			for (int i = 0; i < 5; i++) {
				move[i][4 - i] = row[i];
			}
			break;

		default:
			break;
		}
	}

	/**
	 * make defend moves from attack moves
	 */
	public List<int[][]> makeDefendMoves(List<int[][]> moves) {
		List<int[][]> defendMoves = new LinkedList<int[][]>();
		for (int[][] move : moves) {
			int[][] defendMove = new int[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (move[i][j] == 1) {
						defendMove[i][j] = 2;
					} else {
						defendMove[i][j] = move[i][j];
					}
				}
			}
			defendMoves.add(defendMove);
		}
		return defendMoves;
	}

	/**
	 * make status lines for moves
	 */
	public List<String> makeStatusLines(int priority, int size) {
		List<String> result = new LinkedList<String>();
		for (int i = 1; i <= size; i++) {
			result.add(priority + "." + i);
		}
		return result;
	}

	/**
	 * display a move for debug purpose
	 */
	public void displayMove(int[][] move) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(move[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * check duplicate moves for debug purpose
	 */
	public String checkDuplicate(List<int[][]> moves) {
		boolean isDuplicate = false;
		Set<String> setMoves = new HashSet<String>();
		for (int[][] move : moves) {
			StringBuilder stringMove = new StringBuilder();
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					stringMove.append(move[i][j]);
				}
			}

			if (setMoves.contains(stringMove.toString())) {
				isDuplicate = true;
				break;
			}
			
			setMoves.add(stringMove.toString());
		}

		if (isDuplicate) {
			return "Duplicate moves are exist";
		} else {
			return "No duplicate moves";
		}
	}
}
