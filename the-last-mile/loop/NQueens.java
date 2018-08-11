package backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class NQueens {
    public int countNQueensSolution(int n) {
        int[] board = new int[n]; // board records the queens' position
        //board positions are 1..n
        //offboard position is 0
        //initialize with first queen on board, all other queens off-board
        board[0] = 1;
        for(int i = 1, count = 0; ; ){
            if(board[0] == 0) return count;
            if(i == n){
                ++count;
                --i;
            }else{
                //place next queen to a feasiable position or 0, if none found
                feasiblePlace(board, i, n + 1);
                if(board[i] == 0) --i; else ++i;
            }
        }
    }

    public ArrayList<String[]> solveNQueens(int n) {
        int[] board = new int[n]; // board records the queens' position
        //board positions are 1..n
        //offboard position is 0
        //initialize with first queen on board, all other queens off-board
        board[0] = 1;
        ArrayList<String[]> result = new ArrayList<String[]>();
        //BUG: i = 0; loop-condition was board[0] == 0
        //i started at 0 and board[0] was assigned 0
        // which means this for loop is skipped
        for(int i = 1; board[0] != 0; ){
            if(i == n){
                result.add(snapshot(board));
                --i;
            }else{
                //place next queen to a feasiable position or 0, if none found
                feasiblePlace(board, i, n + 1);
                if(board[i] == 0) --i; else ++i;
            }
        }
        return result;
    }

    //place ith queen to the next feasiable position or 0 if none found
    //v = 1 + max_position
    void feasiblePlace(int[] board, int i, int v){
        for(board[i] = (board[i] + 1) % v; board[i] != 0; board[i] = (board[i] + 1) % v){
            for(int j = 0; ; ++j){
                if(j == i) //passed checking!
                    return;
                if(check(board, j, i)) //failed checking
                    break;
            }
        }
        //none feasible position found under current configuration
    }

    //check if position held by dth queen is checked by the sth queen
    //invariant: all queens <= d are on board
    boolean check(int[] board, int s, int d){
        if(board[s] == board[d]
                || board[s] - board[d] == d - s
                || board[d] - board[s] == d - s)
            return true;
        return false;
    }

    String[] snapshot(int[] board){
        String[] shot = new String[board.length];
        for(int i = 0; i < board.length; ++i){
            shot[i] = printRow(board, i);
        }
        return shot;
    }

    String printRow(int[] board, int i){
        char[] ca = new char[board.length];
        Arrays.fill(ca, 0, board.length, '.');
        //board positions are 1..n
        //BUG: ca[i] = 'Q';
        ca[board[i] - 1] = 'Q';
        return new String(ca);
    }

    static private int[] pos = null;
    static int counter = 0;
    static public void solve(int n){
        pos = new int[n];
        for(int j = 0; j < n ; ++j){
            pos[j] = -1;
        }
        counter = 0;
        solver(0);
    }

    /**
     * Pseudo-code
     * if all queens have positioned, print and continue
     *  find next valid position p
     *  if none, return
     *   assign queen position to pos[i]
     *  recursive call to next row
     *    
     *  Bug 1: didn't loop on the current row (failure to find next configuration)
     *  Bug 2: didn't erase the positioning of queen on the current row when return. (failure to find next configuration) 
     *  Bug 3: didn't return after print. (out of bound exception)
     * @param i
     */
    static private void solver(int i) {
        do{
            if(i == pos.length){
                print();
                return;
            }
            Integer p = nextValidPos(i);
            if(p == null){
                pos[i] = -1;
                return;
            }
            pos[i] = p;
            solver(i+1);
        }while(true);
    }

    /**
     * Return the next valid position on the ith row
     * @param i the row number
     * @return the valid position (column number). If none exist, return null
     */
    static private Integer nextValidPos(int i) {
        for(int j = pos[i] + 1; j < pos.length; ++j ){
            if(validate(j, i)){
                return j;
            }
        }
        return null;
    }

    /**
     * check if the position [i, j] is valid
     * @param j the column number
     * @param i the row number
     * @return true if the position [i, j] is valid, false otherwise
     */
    static private boolean validate(int j, int i) {
        for(int k = 0; k < i; ++k){
            if(pos[k] == j
                    || pos[k] + (i - k) == j
                    || pos[k] - (i - k) == j)
                return false;
        }
        return true;
    }

    static private void print() {
        System.out.printf("\nConfiguration %d:\n", ++counter);
        for(int i : pos){
            for(int j = 0; j <= pos.length; ++j){
                System.out.print("- ");
            }
            System.out.println();
            for(int j = 0; j <= pos.length; ++j){
                if(j == i){
                    System.out.printf("|Q");
                }else if(j == pos.length){
                    System.out.println("|");
                }else{
                    System.out.printf("| ");
                }
            }
        }
        for(int j = 0; j <= pos.length; ++j){
            System.out.print("- ");
        }
        System.out.println();
    }
}
