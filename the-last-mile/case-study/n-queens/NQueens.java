public class NQueens {
    public int totalNQueens(int n) {
        // board is an array of size n
        // the ith element records the column position of the ith row of a queen
        // valid column positions are 1..n, off-board position is 0
        int[] board = new int[n];
        // initialize with 0th queen on board to 1, while all other queens are off-board (0)
        board[0] = 1;
        int result = 0;
        // terminate when all queens are moved off-board at which point, all the feasible
        // configurations would have been enumerated.

        //BUG: i = 0; loop-condition was board[0] == 0
        //i started at 0 and board[0] was assigned 0
        // which means this for loop is skipped
        for (int i = 1; board[0] != 0; ) {
            if (i == n) {
                ++result;
                --i;
            } else {
                //place ith queen to the next feasible position or 0, if none found
                place(board, i);
                if (board[i] == 0) --i;
                else ++i;
            }
        }
        return result;
    }

    /**
     * place ith queen to the next feasible position.
     * If not possible, place at the 0th row at the ith column as a sentinel
     *
     * @param board array size of n where n is board size value is the row number,
     *              with 0 denote off-board place
     * @param i     the queen on ith column
     */
    void place(int[] board, int i) {
        int m = board.length + 1; // modulus
        for (board[i] = (board[i] + 1) % m; board[i] != 0; board[i] = (board[i] + 1) % m) {
            // place at the first feasible position
            if (feasible(board, i)) return;
        }
        // leave board[i] = 0 because none feasible position can be found under current
        // configuration
    }

    /**
     * Check to see if the newly placed ith queen is feasible with all previous queens
     *
     * @param board the current board with all queens placed up to the ith
     * @param i     the ith index
     * @return true if the ith queen is at peace with all other queens, false otherwise
     */
    boolean feasible(int[] board, int i) {
        // query the jth queen for possible attack for j = 0, 1, ..., i-1
        for (int j = 0; j < i; ++j) {
            if (board[j] == board[i] // same row
                    || board[j] - board[i] == i - j // same anti-diagonal
                    || board[i] - board[j] == i - j // same diagonal
            ) return false;
        }
        // all previous queens are at peace with ith queen
        return true;
    }
}
