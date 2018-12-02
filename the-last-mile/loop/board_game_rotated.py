board = BoardGame(board_size=8)
while True:
    board.printLegend()
    board.printBoard()
    move = board.getMove()
    print "Your move: {}".format(move)
    board.applyMove(move)
    if board.gameOver(move):
        print "Human win!"
        break
    state = board.computerMove(debug=False)
    if state is None:
        print "Game Over: Draw"
        break
    elif state:
        print "Computer wins!"
        break
board.printBoard()
