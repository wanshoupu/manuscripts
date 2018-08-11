public class NumberOfPath {

	// Return the number of paths from (i,j) to (0,0) that visits all the '0'
	// cells exactly once
	static public int getN(int[][] in, int i, int j) {
		// out of boundary
		// put the check here to avoid copying it four times for i+/-1, j+/-1,
		// etc.
		if (i < 0 || in.length <= i || j < 0 || in[i].length <= j)
			return 0;
		// obstacle
		if (0 != in[i][j])
			return 0;
		in[i][j] = 2;
		int total;
		// reached destination
		if (0 == i && 0 == j) {
			// check if all '0' visited
			if (check(in))
				total = 1;
			else
				total = 0;
		} else {
			total = getN(in, i + 1, j) + getN(in, i - 1, j)
					+ getN(in, i, j + 1) + getN(in, i, j - 1);
		}
		in[i][j] = 0;
		print(in);
		return total;
	}

	static public void print(int[][] in) {
		System.out.println("matrix is:");
		for (int[] r : in) {
			for (int c : r) {
				System.out.printf("%d ", c);
			}
			System.out.println();
		}
		System.out.println();
	}

	static public boolean check(int[][] in) {
		for (int[] r : in) {
			for (int c : r) {
				if (0 == c) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int[][] a = { 
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 1, 0, 1, 0 }, 
				{ 0, 1, 0, 1, 0 },
				{ 0, 0, 0, 1, 0 }
		};
		System.out.printf("%d\n", getN(a, 3, 4));
	}

}
