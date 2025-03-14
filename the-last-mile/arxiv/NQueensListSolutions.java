public List<List<String>> solveNQueens(int n) {
    ...
    List<List<String>> result = new ArrayList<>();
    for (int i = 1; board[0] != 0; ) {
        ...
        result.add(snapshot(board));
        ...
    }
    return result;
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
