public ArrayList<Integer> spiralOrder(int[][] matrix) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    // For empty matrix, the following 'j2 = matrix[0].length' would cause OOB exception
    // So check this is not the case
    if (matrix.length == 0) return result;

    // i1, i2, j1, j2 are the top, bottom, left, and right bounds for the 'remaining' matrix
    int i1 = 0, i2 = matrix.length;
    int j1 = 0, j2 = matrix[0].length;
    // s is a zero-based ordinal number that is used to keep track of direction of motion
    int s = 0;
    while (i1 < i2 && j1 < j2) {
        switch (s % 4) {
            case 0: // going right on top
                for (int j = j1; j < j2; ++j) {
                    result.add(matrix[i1][j]);
                }
                ++i1;
                break;
            case 1: // going down on right
                --j2; // pre-increment because of '1-pass-index' notation
                for (int i = i1; i < i2; ++i) {
                    result.add(matrix[i][j2]);
                }
                break;
            case 2: // going left on bottom
                --i2; // pre-increment because of '1-pass-index' notation
                for (int j = j2; j > j1; ) {
                    result.add(matrix[i2][--j]);
                }
                break;
            case 3: // going up on left
                for (int i = i2; i > i1; ) {
                    result.add(matrix[--i][j1]);
                }
                ++j1;
                break;
        }
        ++s;
    }
    return result;
}
