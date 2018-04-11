package movesmaker;

import java.util.LinkedList;
import java.util.List;
import logic.FileProcessor;
import logic.PropertiesGetter;

public class MoveMaker {
	private static int total = 0;
	private static List<int[][]> attackMovelevel1;
	private static List<int[][]> defendMovelevel1;
	private static List<int[][]> attackMovelevel2;
	private static List<int[][]> defendMovelevel2;
	private static List<int[][]> attackMovelevel3;
	private static List<int[][]> defendMovelevel3;
	private static List<int[][]> attackMovelevel4;
	private static List<int[][]> defendMovelevel4;
	private static List<int[][]> attackMovelevel5;
	private static List<int[][]> defendMovelevel5;
	private static List<int[][]> movelevel6;
	private static List<int[][]> moveList;
	private static List<String> statusLines;

	public static void main(String[] args) {
		attackMovelevel1 = new LinkedList<int[][]>();
		defendMovelevel1 = new LinkedList<int[][]>();
		attackMovelevel2 = new LinkedList<int[][]>();
		defendMovelevel2 = new LinkedList<int[][]>();
		attackMovelevel3 = new LinkedList<int[][]>();
		defendMovelevel3 = new LinkedList<int[][]>();
		attackMovelevel4 = new LinkedList<int[][]>();
		defendMovelevel4 = new LinkedList<int[][]>();
		attackMovelevel5 = new LinkedList<int[][]>();
		defendMovelevel5 = new LinkedList<int[][]>();
		movelevel6 = new LinkedList<int[][]>();
		moveList = new LinkedList<int[][]>();
		statusLines = new LinkedList<String>();
		new MoveMaker().makeMoves();
		System.out.println(MoveMakerHelper.checkDuplicate(moveList));
		System.out.println(MoveMakerHelper.isMoveWith2PlacesToPlayExist(moveList));
		System.out.println("Number of moves: " + total);
	}

	public void makeMoves() {
		makeMoveLevelOne();
		makeMoveLevelTwo();
		makeMoveLevelThree();
		makeMoveLevelFour();
		makeMoveLevelFive();

		// defend move level 1
		List<int[][]> combineMove21 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel2, defendMovelevel1);
		moveList.addAll(combineMove21);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("2@1", combineMove21.size()));
		List<int[][]> combineMove31 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel3, defendMovelevel1);
		moveList.addAll(combineMove31);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("3@1", combineMove31.size()));
		List<int[][]> combineMove41 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel4, defendMovelevel1);
		moveList.addAll(combineMove41);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("4@1", combineMove41.size()));
		List<int[][]> combineMove51 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel5, defendMovelevel1);
		moveList.addAll(combineMove51);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("5@1", combineMove51.size()));
		moveList.addAll(defendMovelevel1);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("2", defendMovelevel1.size()));

		// attack move level 2
		List<int[][]> combineMove23 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel2, defendMovelevel3);
		moveList.addAll(combineMove23);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("2@3", combineMove23.size()));
		List<int[][]> combineMove24 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel2, defendMovelevel4);
		moveList.addAll(combineMove24);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("2@4", combineMove24.size()));
		moveList.addAll(attackMovelevel2);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("3", attackMovelevel2.size()));

		// defend move level 2
		List<int[][]> combineMove32 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel3, defendMovelevel2);
		moveList.addAll(combineMove32);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("3@2", combineMove32.size()));
		List<int[][]> combineMove42 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel4, defendMovelevel2);
		moveList.addAll(combineMove42);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("4@2", combineMove42.size()));
		List<int[][]> combineMove52 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel5, defendMovelevel2);
		moveList.addAll(combineMove52);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("5@2", combineMove52.size()));
		moveList.addAll(defendMovelevel2);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("4", defendMovelevel2.size()));

		// attack move level 3
		List<int[][]> combineMove34 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel3, defendMovelevel4);
		moveList.addAll(combineMove34);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("3@4", combineMove34.size()));
		moveList.addAll(attackMovelevel3);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("5", attackMovelevel3.size()));

		// defend move level 3
		List<int[][]> combineMove43 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel4, defendMovelevel3);
		moveList.addAll(combineMove43);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("4@3", combineMove43.size()));
		List<int[][]> combineMove53 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel5, defendMovelevel3);
		moveList.addAll(combineMove53);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("5@3", combineMove53.size()));
		moveList.addAll(defendMovelevel3);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("6", defendMovelevel3.size()));

		// attack move level 4
		moveList.addAll(attackMovelevel4);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("7", attackMovelevel4.size()));

		// defend move level 4
		List<int[][]> combineMove54 = MoveMakerHelper.makeAttackDefendCombineMoves(attackMovelevel5, defendMovelevel4);
		moveList.addAll(combineMove54);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("5@4", combineMove54.size()));
		moveList.addAll(defendMovelevel4);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("8", defendMovelevel4.size()));

		// attack move level 5
		moveList.addAll(attackMovelevel5);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("9", attackMovelevel5.size()));

		// defend move level 5
		moveList.addAll(defendMovelevel5);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("10", defendMovelevel5.size()));

		// move level 6
		// makeMoveLevelSix();

		FileProcessor fileProcessor = new FileProcessor(new PropertiesGetter().getProperty("fileLocation"));
		total += fileProcessor.writeMoves(moveList, statusLines);
	}

	private void makeMoveLevelOne() {
		attackMovelevel1 = new MoveWithSingleRowMaker().makeMoves(new FourInARowMaker().makeRow());
		defendMovelevel1 = MoveMakerHelper.makeDefendMoves(attackMovelevel1);

		// attack move level 1
		moveList.addAll(attackMovelevel1);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("1", attackMovelevel1.size()));
	}

	private void makeMoveLevelTwo() {
		ThreeInARowMaker threeInARowMaker = new ThreeInARowMaker();
		TwoInARowMaker twoInARowMaker = new TwoInARowMaker();
		MoveWithDoubleRowMaker moveWithDoubleRowMaker = new MoveWithDoubleRowMaker();
		MoveWithSingleRowMaker moveWithSingleRowMaker = new MoveWithSingleRowMaker();

		List<int[]> firstListThreeTight = threeInARowMaker.makeTightRow();
		List<int[]> secondListTreeTight = threeInARowMaker.makeTightRow();
		List<int[]> firstListThreeLoose = threeInARowMaker.makeLooseRow();
		List<int[]> secondListThreeLoose = threeInARowMaker.makeLooseRow();
		List<int[]> firstListTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> secondListTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> listTwoLoose = twoInARowMaker.makeLooseRow();

		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, secondListTreeTight));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, secondListThreeLoose));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose, secondListTreeTight));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, secondListTwoTight));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeTight, listTwoLoose));
		attackMovelevel2.addAll(moveWithSingleRowMaker.makeMoves(firstListThreeTight));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoTight, secondListTwoTight));
		attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose, secondListThreeLoose));

		// attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose,
		// listTwoTight));
		// attackMovelevel2.addAll(moveWithDoubleRowMaker.makeMoves(firstListThreeLoose,
		// listTwoLoose));
		// attackMovelevel2.addAll(moveWithSingleRowMaker.makeMoves(firstListThreeLoose));

		defendMovelevel2 = MoveMakerHelper.makeDefendMoves(attackMovelevel2);
	}

	private void makeMoveLevelThree() {
		TwoInARowMaker twoInARowMaker = new TwoInARowMaker();
		MoveWithDoubleRowMaker moveWithDoubleRowMaker = new MoveWithDoubleRowMaker();

		List<int[]> listTwoTight = twoInARowMaker.makeTightRow();
		List<int[]> listTwoLoose = twoInARowMaker.makeLooseRow();

		attackMovelevel3.addAll(moveWithDoubleRowMaker.makeMoves(listTwoTight, listTwoLoose));
		attackMovelevel3.addAll(moveWithDoubleRowMaker.makeMoves(listTwoLoose, listTwoTight));
		// attackMovelevel3.addAll(moveWithDoubleRowMaker.makeMoves(firstListTwoLoose,
		// secondListTwoLoose));

		defendMovelevel3 = MoveMakerHelper.makeDefendMoves(attackMovelevel3);
	}

	private void makeMoveLevelFour() {
		List<int[]> listTwo = new TwoInARowMaker().makeTightRow();
		List<int[]> listOne = new OneInARowMaker().makeRow();

		attackMovelevel4.addAll(new MoveWithDoubleRowMaker().makeMoves(listTwo, listOne));
		attackMovelevel4.addAll(new MoveWithSingleRowMaker().makeMoves(listTwo));

		defendMovelevel4 = MoveMakerHelper.makeDefendMoves(attackMovelevel4);
	}

	private void makeMoveLevelFive() {
		attackMovelevel5 = new MoveWithSingleRowMaker().makeMoves(new OneInARowMaker().makeRow());
		defendMovelevel5 = MoveMakerHelper.makeDefendMoves(attackMovelevel5);
	}

	private void makeMoveLevelSix() {
		movelevel6.add(new int[][] { new int[] { 3, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 } });
		movelevel6.add(new int[][] { new int[] { 4, 4, 4, 4, 3 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 } });
		movelevel6.add(new int[][] { new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 3, 4, 4, 4, 4 } });
		movelevel6.add(new int[][] { new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 },
				new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 4 }, new int[] { 4, 4, 4, 4, 3 } });

		moveList.addAll(movelevel6);
		statusLines.addAll(MoveMakerHelper.makeStatusLines("11", 4));
	}
}