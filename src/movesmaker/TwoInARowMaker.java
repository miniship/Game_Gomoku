package movesmaker;

import java.util.LinkedList;
import java.util.List;

public class TwoInARowMaker {
	/**
	 * previous's moves and place to play next are close
	 */
	public List<int[]> makeTightRow() {
		List<int[]> result = new LinkedList<int[]>();
		result.add(new int[] { 0, 3, 1, 1, 0 });
		result.add(new int[] { 0, 1, 3, 1, 0 });
		result.add(new int[] { 0, 1, 1, 3, 0 });

//		result.add(new int[] { 0, 0, 3, 1, 1 });
//		result.add(new int[] { 0, 0, 1, 3, 1 });
//		result.add(new int[] { 0, 0, 1, 1, 3 });

//		result.add(new int[] { 3, 1, 1, 0, 0 });
//		result.add(new int[] { 1, 3, 1, 0, 0 });
//		result.add(new int[] { 1, 1, 3, 0, 0 });
		return result;
	}

	/**
	 * previous's moves and place to play next are not close
	 */
	public List<int[]> makeLooseRow() {
		List<int[]> result = new LinkedList<int[]>();
		result.add(new int[] { 0, 1, 0, 1, 3 });
		result.add(new int[] { 0, 1, 1, 0, 3 });
		result.add(new int[] { 3, 0, 1, 1, 0 });
		result.add(new int[] { 3, 1, 0, 1, 0 });
		
		result.add(new int[] { 0, 3, 0, 1, 1 });
		result.add(new int[] { 0, 1, 0, 3, 1 });		
		result.add(new int[] { 0, 3, 1, 0, 1 });
		result.add(new int[] { 0, 1, 3, 0, 1 });			
		result.add(new int[] { 1, 0, 3, 1, 0 });
		result.add(new int[] { 1, 0, 1, 3, 0 });	
		result.add(new int[] { 1, 3, 0, 1, 0 });
		result.add(new int[] { 1, 1, 0, 3, 0 });
		return result;
	}
}
