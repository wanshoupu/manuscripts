"""
There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move
the ball to adjacent cell or out of the grid boundary in four directions (up, down, left,
right). However, you can at most move N times. Find out the number of paths to move the ball out
of boundary. The answer may be very large, return it after mod 10^9 + 7.
"""

import numpy as np


class Solution2:
    """
    Second implementation based on matrix
    """

    def findPaths(self, m: int, n: int, N: int, i: int, j: int) -> int:
        result = 0
        partial = self._init(m, n)
        for _ in range(N):
            result += int(partial[i, j])
            partial = self._transfer_function(partial)
        return result

    def _transfer_function(self, partial):
        result = np.zeros(partial.shape, dtype=np.uint64)
        result[1:, :] += partial[:-1, :]
        result[:-1, :] += partial[1:, :]
        result[:, 1:] += partial[:, :-1]
        result[:, :-1] += partial[:, 1:]
        return result % (10 ** 9 + 7)

    def _init(self, m, n):
        """
        BUG:
        1. corner cells have two ways of exiting in one step
        2. if a one-cell grid, it has 4 ways of exiting in one step
        :param m:
        :param n:
        :return:
        """
        result = np.zeros((m, n), dtype=np.uint64)
        result[:, 0] += 1
        result[:, -1] += 1
        result[0, :] += 1
        result[-1, :] += 1
        return result


from collections import defaultdict


class Solution:
    """
    First thoughts:
    P(i,j;u) := the number of paths to move from (i,j) to OOB by moving exactly u times
    P(i,j;k,l;u) := the number of paths to move from (i,j) to (k,l) by moving exactly u times
    P(i,j;k,l;u) = P(i+1,j;k,l;u-1) + P(i-1,j;k,l;u-1) + P(i,j+1;k,l;u-1) + P(i,j-1;k,l;u-1)

    Second thoughts:
    P(i,j;u) is NOT NEEDED, as we can require that
    step 1: move to boundary cell;
    step 2: move out in one move.

    P(i,j;k,l;u) can be simplified to P(i,j;0,0;u)
    P(i,j;u) := the number of paths to move ball from (0,0) to (i,j) in u steps
    By symmetry, P(i,j;u) = P(-i,j;u) = P(i,-j;u) = P(-i,-j;u)

    Third thoughts:
    All these formula are to be understood with the constraint of m x n grid.
    Because of the boundary constraints, P(i,j;0,0;u) is not necessarily the same as P(i,j;k,l;u)
    """

    def findPaths(self, m: int, n: int, N: int, i: int, j: int) -> int:
        result = 0
        partial = self._init(m, n)
        for _ in range(N):
            result += partial[i, j]
            partial = self._transfer_function(partial, m, n)
        return result

    def _transfer_function(self, partial, m, n):
        result = defaultdict(int)
        for (i, j), v in partial.items():
            candidates = ((i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1))
            candidates = [(i, j) for i, j in candidates if 0 <= i < m and 0 <= j < n]
            for i, j in candidates:
                result[i, j] = (result[i, j] + v) % (10 ** 9 + 7)
        return result

    def _init(self, m, n):
        """
        BUG:
        1. corner cells have two ways of exiting in one step
        2. if a one-cell grid, it has 4 ways of exiting in one step
        :param m:
        :param n:
        :return:
        """
        result = defaultdict(int)
        for i in range(m):
            result[i, 0] += 1
            result[i, n - 1] += 1
        for i in range(n):
            result[0, i] += 1
            result[m - 1, i] += 1
        return result


if __name__ == '__main__':
    sol = Solution()
    sol2 = Solution2()
    tests = [
        [3, 3, 3, 1, 1, 20],
        [1, 1, 1, 0, 0, 4],
        [1, 1, 2, 0, 0, 4],
        [2, 2, 2, 0, 0, 6],
        [1, 3, 3, 0, 1, 12],
        [3, 2, 51, 0, 0, 12970780255],
        [8, 50, 23, 5, 26, 2914783394],
        [5, 10, 20, 2, 5, 2233842808],
        [5, 36, 50, 3, 15, 18390153432],
        [36, 5, 50, 15, 3, 18390153432],
    ]
    for m, n, N, i, j, ans in tests:
        result = sol.findPaths(m, n, N, i, j)
        result2 = sol2.findPaths(m, n, N, i, j)
        print(np.array([result, result2]).T)
        assert result == result2 == ans, (m, n, N, i, j, result)
