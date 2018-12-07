board = BoardGame(board_size=8)
board.printLegend()
board.printBoard()
move = board.getMove()
print "Your move: {}".format(move)
while True:
    board.applyMove(move)
    if board.gameOver():
        print "Human win!"
    state = board.computerMove()
    if state is None:
        print "Game Over: Draw"
        exit()
    elif state:
        print "Computer wins!"
        exit()
    board.printLegend()
    board.printBoard()
    move = board.userInput()
    print "Your move: {}".format(move)
