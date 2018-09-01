class SpiralFlatten(object):
    def flatten(self, mat):
        """
        Public facing method
        :param mat: the 2D matrix
        :return: an array of flattened elements in spiral order
        """
        self.mat = mat
        self.result = []
        self.recurse(0, 0)
        return self.result[:]

    def recurse(self, x, y):
        """
        Recursive solution for flattening a 2D matrix in spiral order
        :param x: the starting row index
        :param y: the starting column index
        """
        xp = len(self.mat) - x - 1  # the ending row index
        if xp < x:
            # no row left unvisited
            return
        yp = len(self.mat[x]) - y - 1  # the ending column index
        if yp < y:
            # no column left unvisited
            return
        if xp == x:
            self.result.extend(self.mat[x][y:yp + 1])
            return
        if yp == y:
            self.result.extend([self.mat[i][y] for i in range(x, xp + 1)])
            return
        self.result.extend(self.cycle(x, y))
        self.recurse(x + 1, y + 1)

    def cycle(self, x, y):
        pass
