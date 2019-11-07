package gomoku.movesmaker;

import java.util.LinkedList;
import java.util.List;

public class FourInARowMaker {
	public List<int[]> makeRow() {
		List<int[]> result = new LinkedList<int[]>();
		result.add(new int[] { 3, 1, 1, 1, 1 });
		result.add(new int[] { 1, 3, 1, 1, 1 });
		result.add(new int[] { 1, 1, 3, 1, 1 });
		result.add(new int[] { 1, 1, 1, 3, 1 });
		result.add(new int[] { 1, 1, 1, 1, 3 });
		return result;
	}
}
