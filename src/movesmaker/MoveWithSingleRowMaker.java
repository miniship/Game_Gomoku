package movesmaker;

import java.util.LinkedList;
import java.util.List;

public class MoveWithSingleRowMaker {
	/**
	 * make moves by rotating a row in a 5 x 5 matrix and fill in the remaining
	 * cells with value 4
	 */
	public List<int[][]> makeMoves(List<int[]> rows) {
		List<int[][]> moves = new LinkedList<int[][]>();
		for (int[] row : rows) {
			for (int i = 0; i < 6; i++) {
				int[][] move = MoveMakerHelper.prepareMove();
				for (int j = 0; j < 5; j++) {
					switch (i) {
					// backward cross
					case 0:
						move[j][j] = row[j];
						break;
					// forward cross
					case 1:
						move[j][4 - j] = row[j];
						break;
					// top row
					case 2:
						move[0][j] = row[j];
						break;
					// left column
					case 3:
						move[j][0] = row[j];
						break;
					// bottom row
					case 4:
						move[4][j] = row[j];
						break;
					// right column
					case 5:
						move[j][4] = row[j];
						break;
					}
				}
				moves.add(move);
			}
		}
		return moves;
	}
}
