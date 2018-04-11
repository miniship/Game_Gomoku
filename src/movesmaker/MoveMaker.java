package movesmaker;

import java.util.LinkedList;
import java.util.List;

import logic.FileProcessor;
import logic.PropertiesGetter;

public class MoveMaker {
	private static int total = 0;

	public static void main(String[] args) {
		new MoveMaker().makeMoves();
		System.out.println("Number of moves: " + total);
	}

	public void makeMoves() {
		makeLevelOne();
		makeLevelTwo();
		makeLevelThree();
		makeLevelFour();
		makeLevelFive();
		makeLevelSix();
	}

	private void makeLevelOne() {
		List<int[][]> moves = new MoveWithSingleRowMaker().makeMoves(new FourInARowMaker().makeRow());
		saveMoves(moves, 1);
	}

	private void makeLevelTwo() {
		List<int[][]> moves = new LinkedList<int[][]>();
		ThreeInARowMaker threeInARowMaker = new ThreeInARowMaker();
		TwoInARowMaker twoInARowMaker = new TwoInARowMaker();
		MoveWithDoubleRowMaker moveWithDoubleRowMaker = new MoveWithDoubleRowMaker();
		MoveWithSingleRowMaker moveWithSingleRowMaker = new MoveWithSingleRowMaker();

		List<int[]> firstListThreeTight = threeInARowMaker.makeTightRow();
		List<int[]> secondListTreeTight = threeInARowMaker.makeTightRow();
		List<int[]> firstListThreeLoose = threeInARowMaker.makeLooseRow();
		List<int[]> secondListThreeLoose = threeInARowMaker.makeLooseRow();
		List<int[]> listTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> listTwoLoose = twoInARowMaker.makeLooseRow();

		// combine 2 threeInARow
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, secondListTreeTight));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, secondListThreeLoose));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose, secondListTreeTight));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose, secondListThreeLoose));

		// combine 1 threeInARow and 1 twoInARow
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, listTwoTight));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, listTwoLoose));

		// 1 threeInARow
		moves.addAll(moveWithSingleRowMaker.makeMoves(firstListThreeTight));

		// ineffective moves
		// moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose,
		// listTwoTight));
		// moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose,
		// listTwoLoose));
		// moves.addAll(moveWithSingleRowMaker.makeMoves(firstListThreeLoose));

		saveMoves(moves, 3);
	}

	private void makeLevelThree() {
		List<int[][]> moves = new LinkedList<int[][]>();
		TwoInARowMaker twoInARowMaker = new TwoInARowMaker();
		MoveWithDoubleRowMaker moveWithDoubleRowMaker = new MoveWithDoubleRowMaker();

		List<int[]> firstListTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> secondListTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> firstListTwoLoose = twoInARowMaker.makeLooseRow();
		List<int[]> secondListTwoLoose = twoInARowMaker.makeLooseRow();

		// combine 2 twoInArow
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoTight, secondListTwoTight));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoTight, secondListTwoLoose));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoLoose, secondListTwoTight));
		moves.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoLoose, secondListTwoLoose));

		saveMoves(moves, 5);
	}

	private void makeLevelFour() {
		List<int[][]> moves = new LinkedList<int[][]>();
		List<int[]> listTwo = new TwoInARowMaker().makeTightRow();
		List<int[]> listOne = new OneInARowMaker().makeRow();

		// combine 1 TwoInARow and 1 OneInARow
		moves.addAll(new MoveWithDoubleRowMaker().makeMoves(listTwo, listOne));

		// 1 TwoInARow
		moves.addAll(new MoveWithSingleRowMaker().makeMoves(listTwo));

		saveMoves(moves, 7);
	}

	private void makeLevelFive() {
		List<int[][]> moves = new MoveWithSingleRowMaker().makeMoves(new OneInARowMaker().makeRow());

		saveMoves(moves, 9);
	}

	public void makeLevelSix() {
		List<int[][]> moves = new LinkedList<int[][]>();
		moves.add(new int[][] { new int[] { 3, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 } });
		moves.add(new int[][] { new int[] { 4, 4, 4, 4, 3 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 } });
		moves.add(new int[][] { new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 3, 4, 4, 4, 4 } });
		moves.add(new int[][] { new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 3 } });

		List<String> statusLines = new MoveMakerHelper().makeStatusLines(11, 4);
		total += new FileProcessor(new PropertiesGetter().getProperty("fileLocation")).writeMoves(moves, statusLines);
	}

	/**
	 * save all moves to the file, including defend moves
	 */
	private void saveMoves(List<int[][]> moves, int priority) {
		MoveMakerHelper helper = new MoveMakerHelper();
		List<int[][]> defendMoves = helper.makeDefendMoves(moves);
		List<String> attackStatusLines = helper.makeStatusLines(priority, moves.size());
		List<String> defendStatusLines = helper.makeStatusLines(priority + 1, moves.size());

		FileProcessor fileProcessor = new FileProcessor(new PropertiesGetter().getProperty("fileLocation"));
		total += fileProcessor.writeMoves(moves, attackStatusLines);
		total += fileProcessor.writeMoves(defendMoves, defendStatusLines);
	}
}