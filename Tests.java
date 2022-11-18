class Q1Tests {
	final static int N = 5;

	public static void test() {
		int[] intArray = {1, 2, 3, 4 ,5};
		char[] dirArray = {'R', 'L'};


		// first row: rightwards with offset from 0 to 5
		// second row: rw w -1, rw w -6, lw w 1, lw w 2, lw w -2
 
		int[][] resArray = {{1, 2, 3, 4, 5}, {5, 1, 2, 3, 4}, {4, 5, 1, 2, 3}, {3, 4, 5, 1, 2}, {2, 3, 4, 5, 1}, {1, 2, 3, 4, 5}, 
					  {2, 3, 4, 5, 1}, {2, 3, 4, 5, 1}, {2, 3, 4, 5, 1}, {3, 4, 5, 1, 2}, {4, 5, 1, 2, 3}};
		
		int cnt = 0;
		for(int i = 0; i < N + 1; i++) {
			singleCheck(i, 'R', resArray[cnt++]);
		}
		singleCheck(-1, 'R', resArray[cnt++]);
		singleCheck(-6, 'R', resArray[cnt++]);
		singleCheck(1, 'L', resArray[cnt++]);
		singleCheck(2, 'L', resArray[cnt++]);
		singleCheck(-2, 'L', resArray[cnt++]);
		
		System.out.println("Survived");
	}

	private static void singleCheck(int offset, char direction, int[] resArray) {
		int[] intArray = {1, 2, 3, 4, 5};
		assert ArrayUtils.shiftArrayCyclic(intArray, offset, direction) == resArray;
		assert intArray == resArray;
	}
}

class Q2Tests {
	public static void test() {
		int[][][] mats = {
			{{0, 1, 0, 0, 1}, 
			 {0, 0, 1, 0, 1}, 
			 {1, 0, 0, 0, 0}, 
			 {0, 0, 1, 0, 0}, 
			 {0, 0, 0, 1, 0}},

			{{0, 1, 0}, 
			 {0, 0, 1}, 
			 {1, 1, 0}},

			{{0, 1, 0, 0}, 
			 {0, 0, 1, 0}, 
			 {0, 0, 0, 1}, 
			 {1, 0, 0, 0}}
		};		

		int[][][] resMat = {
			{{0, 1, 2, 2, 1},
			 {2, 0, 1, 2, 1},
			 {1, 2, 0, 3, 2},
			 {2, 3, 1, 0, 3},
			 {3, 4, 2, 1, 0}},

			{{0, 1, 2},
			 {2, 0, 1},
			 {1, 1, 0}},

			{{0, 1, 2, 3},
			 {3, 0, 1, 2},
			 {2, 3, 0, 1},
			 {1, 2, 3, 0}}
		};

		for(int k = 0; k < 3; k++) {
			int[][] m = mats[k];
			int[][] res = resMat[k];
			for(int i = 0; i < m.length; i++) {
				for(int j = 0; j < m.length; j++) {
					assert ArrayUtils.findShortestPath(m, i, j) == res[i][j];
				}
			}
		}

		System.out.println("Survived");
	}
}


class Q3Tests {
	public static void test() {
		String[] strArray = {"to be or not to be", "my mind is an empty zoo", "", "andy bought candy", "life is not not not fair", "art act"};
		String[] resArray = {"not to", "an empty zoo", "", "andy bought candy", "is not not not", "act"};
		for(int i = 0; i < 6; i++) {
			singleCheck(strArray[i], resArray[i]);
		}
		System.out.println("Survived");
	}
	
	private static void singleCheck(String str, String res) {
		assert StringUtils.findSortedSequence(str) == res;
	}
}

class Q4Tests {
	public static void test() {
		assert !StringUtils.isEditDistanceOne("dog", "god");
		assert StringUtils.isEditDistanceOne("x", "x");
		assert StringUtils.isEditDistanceOne("main", "man");
		assert StringUtils.isEditDistanceOne("man", "main");
		assert StringUtils.isEditDistanceOne("ab", "cab");
		System.out.println("Survived");
	}
}

public class Tests {
	public static void main(String[] args) {
		Q1Tests.test();
		Q2Tests.test();
		Q3Tests.test();
		Q4Tests.test();
		System.out.println("Yeepidy");
	}

}