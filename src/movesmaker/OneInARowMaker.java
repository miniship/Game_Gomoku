package movesmaker;

import java.util.LinkedList;
import java.util.List;

public class OneInARowMaker {
	public List<int[]> makeRow() {
		List<int[]> result = new LinkedList<int[]>();
//		result.add(new int[] { 0, 0, 0, 3, 1 });
//		result.add(new int[] { 0, 0, 0, 1, 3 });

		result.add(new int[] { 0, 0, 3, 1, 0 });
		result.add(new int[] { 0, 0, 1, 3, 0 });

		result.add(new int[] { 0, 3, 1, 0, 0 });
		result.add(new int[] { 0, 1, 3, 0, 0 });

//		result.add(new int[] { 3, 1, 0, 0, 0 });
//		result.add(new int[] { 1, 3, 0, 0, 0 });
		return result;
	}
}
