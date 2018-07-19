class IteratorArray2D(object):
    def __init__(self, mat):
        self.mat = mat
        self.row = 0
        self.col = -1

    def next(self):
        return self.mat[self.row][self.col]

    def has_next(self):
        if self.row == len(self.mat):
            return False
        self.col += 1
        # don't be tempted to use while loop here. use recursion is simple, elegant, and scalable
        #  for higher dimension
        if self.col == len(self.mat[self.row]):
            self.row += 1
            self.col = -1
            return self.has_next()
        return True


if __name__ == '__main__':
    mat = [
        [],
        [1, 2],
        [3],
        [4],
        [5, 6],
        [],
        [7],
        [8],
        []
    ]
    iterator = IteratorArray2D(mat)
    while iterator.has_next():
        print iterator.next()
