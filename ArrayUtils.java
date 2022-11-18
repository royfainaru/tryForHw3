
public class ArrayUtils {
	
	/**
	 * we will divide the array into m 'links' according to net offset.
	 * then we will push each link one place forward.
	 */
	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		if(direction != 'L' && direction != 'R') {
			return array;
		}
		if(direction == 'L') {
			move = -move;
		}
		
		int n = array.length; // array length
		int m = (move >= 0) ? move % n : n - (-move % n); // link length
		if(m == 0) {
			// trivial outcome
			return array;
		}
		int k = (n % m == 0) ? (n / m) : (n / m) + 1; // no. of links in array, no. of iterations
		int[] tmpAry = new int[m + 1]; // here we will store currently operated link values
		
		ArrayUtils.setTmp(tmpAry, array, 0); // store first link values in tmp memory
		for(int l = 1; l <= k; l++) {
			ArrayUtils.swapLink(array, tmpAry, l); // swap between tmp and target link
		}

		return array;
	}
	
	private static int linkHeaderIndex(int n, int m, int l) {
		return (l * m) % n;
	}
	
	private static void setTmp(int[] tmpAry, int[] array, int l) {
		int n = array.length;
		int m = tmpAry.length - 1;
		int i0 = ArrayUtils.linkHeaderIndex(n, m, l);
		for(int t = 0; t < m; t++) {
			int i = (i0 + t) % n;
			tmpAry[t] = array[i];
		}
	}
	
	private static void swapLink(int[] array, int[] tmpAry, int l) {
		int n = array.length;
		int m = tmpAry.length - 1;
		int i0 = ArrayUtils.linkHeaderIndex(n, m, l);
		for(int t = 0; t < m; t++) {
			int i = (i0 + t) % n;
			tmpAry[m] = array[i];
			array[i] = tmpAry[t];
			tmpAry[t] = tmpAry[m];
		}
	} 


	public static int findShortestPath(int[][] m, int i, int j) {
		int[][] memo = initMemoTable(m.length);
		return shortestPathRec(m, i, j, memo);
	}
	
	private static int shortestPathRec(int[][] m, int i, int j, int[][] memo) {
		
		if(memo[i][j] < -1) {
			memo[i][j]++;
		}
		if(memo[i][j] > -2) {
			return memo[i][j];
		}
		if(i == j) {
			memo[i][j] = 0;
			return 0;
		}
		if(m[i][j] == 1) {
			memo[i][j] = 1;
			return 1;
		}
		
		int[] resAry = initResAry(m.length);
		for(int k = 0; k < m.length; k++) {
			if(m[i][k] == 1) {
				resAry[k] = shortestPathRec(m, k, j, memo);
			}
		}
		int minPath = aryMin(resAry);
		if(minPath == -1) {
			memo[i][j] = -1;
			return -1;
		}
		memo[i][j] = minPath + 1;
		return minPath + 1;
	}
	
	private static int aryMin(int[] ary) {
		int min = -1;
		for(int i = 0; i < ary.length; i++) {
			if(ary[i] >= 0 && ((ary[i] < min) || (min < 0))) {
				min = ary[i];
			}
		}
		return min;
	}

	private static int[] initResAry(int n) {
		int[] resAry = new int[n];
		for(int i = 0; i < n; i++) {
			resAry[i] = -1;
		}
		return resAry;
	}

	private static int[][] initMemoTable(int n) {
		int[][] res = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				res[i][j] = -3;
			}
		}
		return res;
	}
	
	public static void printAry(int[] array) {
		for(int i = 0; i < array.length - 1; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println(array[array.length - 1]);
	}
}

