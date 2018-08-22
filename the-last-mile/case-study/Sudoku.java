package org.shoupu.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class Sudoku {
    private static int cellSize = 3;

    public class Solution {
        public boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < board.length; ++i) {
                if (!isValidSudokuCom(board[i])) {
                    return false;
                }
            }
            for (int j = 0; j < board.length; ++j) {
                char[] col = new char[board.length];
                for (int i = 0; i < board.length; ++i) {
                    col[i] = board[i][j];
                }
                if (!isValidSudokuCom(col)) {
                    return false;
                }
            }

            for (int i = 0; i < board.length / 3; ++i) {
                for (int j = 0; j < board.length / 3; ++j) {
                    char[] cell = new char[board.length];
                    for (int m = 0; m < 3; ++m) {
                        for (int n = 0; n < 3; ++n) {
                            cell[m * 3 + n] = board[i * 3 + m][j * 3 + n];
                        }
                    }
                    if (!isValidSudokuCom(cell)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean isValidSudokuCom(final char[] board) {
            boolean[] exists = new boolean[board.length];
            for (int i = 0; i < board.length; ++i) {
                int j = board[i] - '1';
                if (0 <= j && j < board.length) {
                    //we only check the digits, ignore unfilled cells
                    if (exists[j]) {
                        return false;
                    } else {
                        exists[j] = true;
                    }
                }
            }
            return true;
        }
    }

    /**
     * assume board side length is a multiple of 9
     * for example size is 9 x 9
     *
     * @param board
     */
    static public boolean solve(Integer[][] board) {
        cellSize = (int) Math.sqrt(board.length);
        List<Integer> iindexes = new ArrayList<Integer>();
        List<Integer> jindexes = new ArrayList<Integer>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == null) {
                    iindexes.add(i);
                    jindexes.add(j);
                }
            }
        }

        return findAndFill(board, iindexes, jindexes, 0);
    }

    /**
     * Recursive:
     * find and fill:
     * find next unfilled cell
     * if(no unfilled cell) return true;
     * find the list of feasible numbers
     * if the list is empty, return false
     * for each feasible numbers
     * fill in the cell
     * boolean completed = recursive call "find and fill"
     * if(completed)
     * return true;
     * end of for
     * unfill the cell // (BUG, fixed) forgot to undo the fill and set the cell to null
     *
     */
    private static boolean findAndFill(Integer[][] board, List<Integer> is, List<Integer> js, int current) {
        System.err.printf("%d\n", current);
        if (current == is.size())
            return true;
        int i = is.get(current);
        int j = js.get(current);
        List<Integer> feasibles = getFeasiables(board, i, j);
        if (feasibles.isEmpty()) return false;
        for (Integer f : feasibles) {
            board[i][j] = f;
            if (findAndFill(board, is, js, current + 1))
                return true;
        }
        board[i][j] = null;
        return false;
    }

    private static List<Integer> getFeasiables(Integer[][] board, int i, int j) {
        List<Integer> feasibles = new ArrayList<Integer>();
        for (int candidate = 1; candidate <= board.length; ++candidate) {
            if (compatible(board, i, j, candidate)) {
                feasibles.add(candidate);
            }
        }

        return feasibles;
    }

    /*
     * determine the cell this number belongs to requires a bit of thinking!
     */
    private static boolean compatible(Integer[][] board, int i, int j, int candidate) {
        return compatibleRow(board, i, candidate)
                && compatibleColumn(board, j, candidate)
                && compatibleCell(board, (i / cellSize) * cellSize, (j / cellSize) * cellSize, candidate);
    }

    /*
     * just use a hash set for simplicity
     * Tried to use a boolean array of size 9 and it requires a null test, buggy!
     */
    private static boolean compatibleCell(Integer[][] board, int i, int j, int candidate) {
        HashSet<Integer> existing = new HashSet<Integer>();
        for (int k = i; k < i + cellSize; ++k) {
            for (int k2 = j; k2 < j + cellSize; ++k2) {
                existing.add(board[k][k2]);
            }
        }
        return !existing.contains(candidate);
    }

    private static boolean compatibleColumn(Integer[][] board, int j,
                                            int candidate) {
        HashSet<Integer> existing = new HashSet<Integer>();
        for (int k = 0; k < board.length; ++k) {
            existing.add(board[k][j]);
        }
        return !existing.contains(candidate);
    }

    private static boolean compatibleRow(Integer[][] board, int i, int candidate) {
        HashSet<Integer> existing = new HashSet<Integer>();
        for (int k = 0; k < board.length; ++k) {
            existing.add(board[i][k]);
        }
        return !existing.contains(candidate);
    }
}
