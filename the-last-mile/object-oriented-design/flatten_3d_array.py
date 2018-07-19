class IteratorArray3D(object):
    def __init__(self, mat3d):
        self.mat = mat3d
        self.row = 0
        self.col = 0
        self.slice = -1

    def next(self):
        return self.mat[self.row][self.col][self.slice]

    def has_next(self):
        if self.row == len(self.mat):
            return False
        if self.col == len(self.mat[self.row]):
            self.row += 1
            self.col = 0
            # self.slice = -1  # this is not needed here
            return self.has_next()
        self.slice += 1
        if self.slice == len(self.mat[self.row][self.col]):
            self.col += 1
            self.slice = -1  # the last dimension is treated as a ring
            return self.has_next()
        return True


if __name__ == '__main__':
    mat = [
        [[]],
        [[10], [20]],
        [[30]],
        [[40]],
        [[50], [60, 70]],
        [[]],
        [[80], [90]],
        [[]]
    ]
    iterator = IteratorArray3D(mat)
    while iterator.has_next():
        print iterator.next()
