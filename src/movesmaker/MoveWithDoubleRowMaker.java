package movesmaker;

import java.util.LinkedList;
import java.util.List;

public class MoveWithDoubleRowMaker {
	/**
	 * make moves by rotating 2 rows in a 5 x 5 matrix and fill in the remaining
	 * cells with value 4
	 */
	public List<int[][]> makeMoves(List<int[]> firstList, List<int[]> secondList) {
		List<int[][]> moves = new LinkedList<int[][]>();
		MoveMakerHelper helper = new MoveMakerHelper();
		for (int i = 0; i < 25; i++) {
			int row = i / 5;
			int col = i % 5;

			// in order to combine 2 rows, they must have the same place to play next
			List<int[]> tempFirstList = helper.filter(firstList, col);
			List<int[]> tempSecondList = helper.filter(secondList, row);

			// place to play is in the middle
			if (row == 2 && col == 2) {
				// moves with 1 backward cross and 1 forward cross
				moves.addAll(makeMoves(tempFirstList, tempSecondList, row, col, 3, 4));
			}

			// place to play is in the backward cross
			if (row == col) {
				// moves with 1 row and 1 backward cross
				moves.addAll(makeMoves(tempFirstList, tempSecondList, row, col, 1, 3));
				// moves with 1 column and 1 backward cross
				moves.addAll(makeMoves(tempFirstList, tempSecondList, row, col, 2, 3));
			}

			// place to play is in the forward cross
			if (row + col == 4) {
				// moves with 1 row and 1 forward cross
				moves.addAll(makeMoves(tempFirstList, tempSecondList, row, col, 1, 4));
				// moves with 1 column and 1 forward cross
				// since row and col are different, we have to filter the firstList by row to
				// make moves
				moves.addAll(makeMoves(helper.filter(firstList, row), tempSecondList, row, col, 2, 4));
			}
			// moves with 1 row and 1 column
			moves.addAll(makeMoves(tempFirstList, tempSecondList, row, col, 1, 2));
		}
		return moves;
	}

	/**
	 * make moves by rotating 2 row in a 5 x 5 matrix and fill in the remaining
	 * cells with value 4
	 * 
	 * @param typeOne, typeTwo way to fill the row into the matrix 
	 *            1: horizontally 
	 *            2: vertically 
	 *            3: in backward cross
	 *            4: in forward cross
	 */
	public List<int[][]> makeMoves(List<int[]> firstList, List<int[]> secondList, int rowToPlay, int colToPlay, int typeOne,
			int typeTwo) {
		MoveMakerHelper helper = new MoveMakerHelper();
		List<int[][]> moves = new LinkedList<int[][]>();
		for (int[] rowOne : firstList) {
			for (int[] rowTwo : secondList) {
				int[][] move = helper.prepareMove();
				helper.fillARowIntoAMove(move, rowOne, rowToPlay, colToPlay, typeOne);
				helper.fillARowIntoAMove(move, rowTwo, rowToPlay, colToPlay, typeTwo);
				moves.add(move);
			}
		}
		return moves;
	}
}
