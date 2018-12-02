board = BoardGame(board_size=8)
board.printLegend()
board.printBoard()
move = board.getMove()
print "Your move: {}".format(move)
board.applyMove(move)
while not board.gameOver():
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
    board.applyMove(move)
print "Human win!"
