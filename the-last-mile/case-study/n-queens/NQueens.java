package org.shoupu.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<List<String>> solveNQueens(int n) {
        int[] board = new int[n]; // board records the queens' position
        //board positions are 1..n
        //offboard position is 0
        //initialize with first queen on board, all other queens off-board
        board[0] = 1;
        List<List<String>> result = new ArrayList<>();
        //BUG: i = 0; loop-condition was board[0] == 0
        //i started at 0 and board[0] was assigned 0
        // which means this for loop is skipped
        for (int i = 1; board[0] != 0; ) {
            if (i == n) {
                result.add(snapshot(board));
                --i;
            } else {
                //place next queen to a feasible position or 0, if none found
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

    List<String> snapshot(int[] board) {
        List<String> shot = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            shot.add(printRow(board, i));
        }
        return shot;
    }

    String printRow(int[] board, int i) {
        char[] ca = new char[board.length];
        Arrays.fill(ca, 0, board.length, '.');
        //board positions are 1..n
        //BUG: ca[i] = 'Q';
        ca[board[i] - 1] = 'Q';
        return new String(ca);
    }
}
