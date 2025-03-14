package org.shoupu.binarySearch;

public class BinarySearch {
    interface Predicate {
        int compare(int c);
    }
    /*
    Conventional implementation
    public boolean searchMatrix(final int[][] matrix, final int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;
        Predicate prow = new Predicate(){
            //find a row index i such that target >= first element in the row && (i + 1 == length || target < matrix[(i+1)][0])
            //or find i such that target >= matrix[i][0] && (i + 1 == matrix.length || target < matrix[i+1][0]
            @Override
            public int compare(int i){
                if(target >= matrix[i][0] && (i + 1 == matrix.length || target < matrix[i+1][0])){
                    return 0;
                }else if(target < matrix[i][0]){
                    return -1;
                }else{
                    return 1;
                }
            }
        };
        final int rowi = binarySearch(0, matrix.length, prow);
        //sanity check can be skipped:
        //if(matrix[rowi][0] > target) return false;
        
        Predicate pcol = new Predicate(){
            @Override
            public int compare(int j){
                if(target > matrix[rowi][j]){
                    return 1;
                }else if(target < matrix[rowi][j]){
                    return -1;
                }else{
                    return 0;
                }
            }
        };
        
        int coli = binarySearch(0, matrix[rowi].length, pcol);
        //sanity check
        if(coli == matrix[rowi].length || matrix[rowi][coli] != target) return false;
        else return true;
    }
*/

    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(final int[][] matrix, final int target) {
        if (matrix.length == 0) return false;

        final int n = matrix.length * matrix[0].length;
        Predicate p = new Predicate() {
            @Override
            public int compare(int c) {
                //depending on the problem, these boundary testing may not always be necessary
                if (c == -1 || c == n)
                    return 0;
                //bug: matrix[c / matrix.length][c % matrix[0].length], to convert to row-index and column-index, use ONLY the row-length
                return target - matrix[c / matrix[0].length][c % matrix[0].length];
            }
        };
        int x = binarySearch(0, n, p);

        return x != -1 && x != n && matrix[x / matrix[0].length][x % matrix[0].length] == target;
    }

    public int[] searchRange(final int[] arr, final int target) {
        Predicate leftComp = new Predicate() {
            @Override
            public int compare(int c) {
                //bug: the condition included 's == e || ' before. But this should be in the binary search function, not in this predicate
                if ((c - 1 == -1 || arr[c - 1] < target) && (c == arr.length || arr[c] >= target)) {
                    return 0;
                }
                //bug: 1. OOB should use arr[c] > target 
                //bug: 2. wrong condition: should use arr[c] >= target (match the second operand above)
                if (arr[c - 1] >= target) {
                    return -1;
                }
                return 1;
            }
        };
        Predicate rightComp = new Predicate() {
            @Override
            public int compare(int c) {
                if ((c == -1 || arr[c] <= target) && (c + 1 == arr.length || arr[c + 1] > target)) {
                    return 0;
                }
                //bug: 1. OOB should use arr[c] > target 
                if (arr[c] > target) {
                    return -1;
                }
                return 1;
            }
        };
        int leftEnd = binarySearch(0, arr.length, leftComp);
        if (leftEnd >= arr.length || arr[leftEnd] != target || leftEnd - 1 >= 0 && arr[leftEnd - 1] >= target)
            leftEnd = -1;
        int rightEnd = binarySearch(0, arr.length, rightComp);
        if (rightEnd < 0 || arr[rightEnd] != target || rightEnd + 1 < arr.length && arr[rightEnd + 1] <= target)
            rightEnd = -1;
        return new int[]{leftEnd, rightEnd};
    }


    /**
     * Core binary search algorithm taking two integers and a predicate interface.
     *
     * @param start
     * @param end
     * @param predicate
     * @return
     */
    int binarySearch(int start, int end, Predicate predicate) {
        if (start == end)
            return start;
        int middle = (start + end) / 2;
        int direction = predicate.compare(middle);
        if (direction < 0)
            return binarySearch(start, middle, predicate);
        if (direction > 0)
            return binarySearch(middle + 1, end, predicate);
        return middle;
    }


    /**
     * Binary search for the index of the target in a sorted array
     * Return the index in the array
     *
     * @param arr
     * @param target
     * @return
     */
    public int binarySearch(final int[] arr, final int target) {
        Predicate p = new Predicate() {
            @Override
            public int compare(int c) {
                if (c < 0 || c >= arr.length)
                    return 0;
                return target - arr[c];
            }
        };
        int i = binarySearch(0, arr.length, p);
        if (i < 0 || i > arr.length || arr[i] != target)
            return -1;
        return i;
    }

    /**
     * Search a target number in a rotated sorted integer array
     *
     * @param A
     * @param target
     * @return
     */
    public int search(final int[] A, final int target) {
        if (A.length == 0) return -1;

        //find a position i such that A[i] >= A[0] && (i + 1 >= A.length || A[i + 1] < A[0])
        Predicate p1 = new Predicate() {
            int bound = A[0];

            @Override
            public int compare(int c) {
                if (A[c] >= bound && (c + 1 == A.length || A[c + 1] < bound))
                    return 0;
                if (A[c] < bound) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        //length1 is the length of the first sorted section, if the array is sorted, all elements belong to the first section
        int length1 = binarySearch(0, A.length, p1) + 1;

        //regular binary search predicate
        Predicate p2 = new Predicate() {
            @Override
            public int compare(int c) {
                if (c < 0 || c >= A.length || A[c] == target)
                    return 0;
                return target - A[c];
            }
        };

        int i = (target < A[0]) ? binarySearch(length1, A.length, p2) : binarySearch(0, length1, p2);
        if (i < 0 || i >= A.length || A[i] != target)
            return -1;
        return i;

    }

}