
public class StringUtils {

	public static String findSortedSequence(String str) {
		if(str.isBlank()) {
			return "";
		}
		
		String[] strArray = str.split(" ");
		int[] resBounds = lastLongestStrSeqBounds(strArray); // 2-int array with left and right bound indices of the result sequence
		
		StringBuilder sb = new StringBuilder();
		for(int i = resBounds[0]; i < resBounds[1]; i++) {
			sb.append(strArray[i] + " ");
		}
		sb.append(strArray[resBounds[1]]);
		return sb.toString();
	}

	/**
	* succession
	* lexicographic
	* ONLY CAPS / only lower
	*/
	public static boolean gt(String str, String other) {
		int len1 = str.length();
		int len2 = other.length();
		int minLen = Math.min(len1, len2);
		for(int i = 0; i < minLen; i++) {
			int a = str.charAt(i);
			int b = other.charAt(i);
			if(a != b) {
				return a > b;
			}
		}		
		return len1 > len2;
	}
	public static boolean lt(String str, String other) {
		return gt(other, str);
	}
	public static boolean ge(String str, String other) {
		return gt(str, other) || str.equals(other);
	}
	public static boolean le(String str, String other) {
		return ge(other, str);
	}

	private static int[] lastLongestStrSeqBounds(String[] array) {
		int n = array.length;
		int left = n - 1;
		int right = n - 1;
		int streakRight = n - 1;
		int streakLeft = n - 1;
		
		for(streakLeft = n - 2; streakLeft > -1; streakLeft--) {
			if(le(array[streakLeft], array[streakLeft + 1])) {
				if(streakRight - streakLeft > right - left) {
					left = streakLeft;
					right = streakRight;
				}
			} else {
				streakRight = streakLeft;
			}
		}
		
		int[] res = new int[2];
		res[0] = left;
		res[1] = right;
		return res;
	}	

	
	public static boolean isEditDistanceOne(String a, String b){
		int n1 = a.length();
		int n2 = b.length();
		
		if(Math.abs(n2 - n1) > 1) {
			return false;
		}
		
		if(n2 == n1) {
			int distanceScore = 0;
			for(int i = 0; i < n1; i++) {
				if(a.charAt(i) != b.charAt(i)) {
					distanceScore++;
				}
			}
			return distanceScore < 2;
		}
		
		if(n1 > n2) {
			return isEditDistanceOne(b, a);
		}
		
		int i = 0;
		boolean strike = false;
		for(int j = 0; i < n1; j++) {
			if(b.charAt(j) != a.charAt(i)) {
				if(strike) {
					return false;
				}
				strike = true;
				continue;
			}
			i++;
		}
		return true;
	}
	
}