board = BoardGame(board_size=8)
while True:
    (*@\hl{board.printLegend()}@*)
    (*@\hl{board.printBoard()}@*)
    (*@\hl{move = board.userInput()}@*)
    (*@\hl{print "Your move: {}".format(move)}@*)
    (*@\hl{board.applyMove(move)}@*)
    if board.gameOver(move):
        print "Human win!"
        break
    state = board.computerMove(debug=False)
    if state is None:
        print "Game Over: Draw"
        break
    if state:
        print "Computer wins!"
        break
