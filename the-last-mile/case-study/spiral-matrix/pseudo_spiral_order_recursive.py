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
        return self.result

    def recurse(self, x, y):
        """
        Recursive solution for flattening a 2D matrix in spiral order
        :param x: the starting row index
        :param y: the starting column index
        """
        if len(self.mat) <= 2 * x or len(self.mat[x]) <= 2 * y:
            # nothing left unvisited
            return

        xp = len(self.mat) - x  # the ending row index
        yp = len(self.mat[x]) - y  # the ending column index
        if x == xp - 1:  # base case 1D row array
            self.result.extend(self.mat[x][y:yp])
            return

        if y == yp - 1:  # base case 1D column array
            self.result.extend([self.mat[i][y] for i in range(x, xp)])
            return

        self.result.extend(self.get_cycle(x, y))

        self.recurse(x + 1, y + 1)

    def get_cycle(self, x, y):
        xp = len(self.mat) - x - 1
        yp = len(self.mat[x]) - y - 1
        # return the concatenation of the upper row, right column, bottom row backward, and
        # left column backward
        return self.mat[x][y:yp] + [self.mat[i][yp] for i in range(x, xp)] + \
               self.mat[xp][yp:y:-1] + [self.mat[i][y] for i in range(xp, x, -1)]


if __name__ == '__main__':
    test_suit = [
        {'test': [], 'ans': []},
        {'test': [[1]], 'ans': [1]},
        {'test': [
            [1], [2], [3],
        ], 'ans': [1, 2, 3]},
        {'test': [
            [1, 2, 3],
            [6, 5, 4],
        ], 'ans': [1, 2, 3, 4, 5, 6]},
        {'test': [
            [1, 2, 3],
            [8, 9, 4],
            [7, 6, 5],
        ], 'ans': [1, 2, 3, 4, 5, 6, 7, 8, 9]},
        {'test': [
            [1, 2, 3, 4],
            [10, 11, 12, 5],
            [9, 8, 7, 6],
        ], 'ans': [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]},
    ]
    for test in test_suit:
        solver = SpiralFlatten()
        ans = solver.flatten(test['test'])
        assert test['ans'] == ans, ans
