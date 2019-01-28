board = BoardGame(board_size=8)
(*@\hl{board.printLegend()}@*)
(*@\hl{board.printBoard()}@*)
(*@\hl{move = board.userInput()}@*)
(*@\hl{print "Your move: {}".format(move)}@*)
(*@\hl{board.applyMove(move)}@*)
while not board.gameOver():
    state = board.computerMove()
    if state is None:
        print "Game Over: Draw"
        exit()
    elif state:
        print "Computer wins!"
        exit()
    (*@\hl{board.printLegend()}@*)
    (*@\hl{board.printBoard()}@*)
    (*@\hl{move = board.userInput()}@*)
    (*@\hl{print "Your move: {}".format(move)}@*)
    (*@\hl{board.applyMove(move)}@*)
print "Human win!"
