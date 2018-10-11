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

    //place ith queen to the next feasible position or 0 if none found
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
}
