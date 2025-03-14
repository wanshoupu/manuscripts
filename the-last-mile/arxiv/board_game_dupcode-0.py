...
while True:
    if board.gameOver():
        print "Human win!"
        exit()
    state = board.computerMove()
    ...
