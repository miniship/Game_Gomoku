package gomoku.movesmaker;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MoveMakerHelper {
	/**
	 * make a new move and fill in every cell with value 4
	 */
	public static int[][] prepareMove() {
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
	public static List<int[]> filter(List<int[]> rows, int toPlay) {
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
	 * @param type
	 *            way to fill the row into the matrix 1: horizontally 2: vertically
	 *            3: in backward cross 4: in forward cross
	 */
	public static void fillARowIntoAMove(int[][] move, int[] row, int rowToPlay, int colToPlay, int type) {
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
	public static List<int[][]> makeDefendMoves(List<int[][]> moves) {
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
	public static List<String> makeStatusLines(String priority, int size) {
		List<String> result = new LinkedList<String>();
		for (int i = 1; i <= size; i++) {
			result.add(priority + "." + i);
		}
		return result;
	}

	/**
	 * display a move for debug purpose
	 */
	public static void displayMove(int[][] move) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(move[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * check duplicate moves for debug purpose
	 */
	public static String checkDuplicate(List<int[][]> moves) {
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

	/**
	 * check whether a move which has 2 places to play (value 3) exist or not for
	 * debug purpose
	 */
	public static String isMoveWith2PlacesToPlayExist(List<int[][]> moves) {
		for (int[][] move : moves) {
			int count = 0;

			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (move[i][j] == 3) {
						count++;
						if (count > 1) {
							return "A move with 2 places to play is exist";
						}
					}
				}
			}
		}
		return "No move with 2 places to play";
	}

	/**
	 * combine attack and defend moves to make better moves
	 */
	public static List<int[][]> makeAttackDefendCombineMoves(List<int[][]> attackMoves, List<int[][]> defendMoves) {
		List<int[][]> result = new LinkedList<int[][]>();

		for (int[][] attackMove : attackMoves) {
			for (int[][] defendMove : defendMoves) {
				int[][] combineMove = combineMove(attackMove, defendMove);

				if (combineMove != null) {
					result.add(combineMove);
				}
			}
		}

		return result;
	}

	private static int[][] combineMove(int[][] attackMove, int[][] defendMove) {
		int[][] result = new int[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (attackMove[i][j] == defendMove[i][j]) {
					result[i][j] = attackMove[i][j];
				} else if (attackMove[i][j] == 4 && defendMove[i][j] != 3) {
					result[i][j] = defendMove[i][j];
				} else if (attackMove[i][j] != 3 && defendMove[i][j] == 4) {
					result[i][j] = attackMove[i][j];
				} else {
					return null;
				}
			}
		}

		return result;
	}
}
