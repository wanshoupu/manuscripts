import random
from lc.grid_2d.mat_util import format_mat


def spiral_indexes(m, n):
    # constants
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    # variables
    limits = [0, 0, n, m]
    direction = 0
    x = 0
    y = 0
    for v in range(0, m * n):
        yield x, y
        nx = x + dx[direction]
        ny = y + dy[direction]
        if nx < limits[1] or limits[3] <= nx or ny < limits[0] or limits[2] <= ny:
            direction = (direction + 1) % 4
            limits[direction] = limits[direction] + (1 if direction < 2 else -1)
            nx = x + dx[direction]
            ny = y + dy[direction]
        x = nx
        y = ny


class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        m = len(matrix)
        n = len(matrix[0]) if m else 0
        res = []
        for x, y in spiral_indexes(m, n):
            res.append(matrix[x][y])
        return res

    def generateSquareMatrix(self, n):
        """
        :type n: int
        :rtype: List[List[int]]
        """
        return self.generateMatrix(n, n)

    def generateMatrix(self, m, n):
        """
        :type m: int
        :type n: int
        :rtype: List[List[int]]
        """
        result = [[0 for x in range(0, n)] for y in range(0, m)]
        v = 1
        for x, y in spiral_indexes(m, n):
            result[x][y] = v
            v = v + 1
            # print format_mat(result)
        return result


def perindex(m, n):
    """
    Generator function that yields the indexes of perimeter sides of a matrix of dimension m x n in
    clockwise order.
    :param m: row size of the matrix
    :param n: column size of the matrix
    :return: array of indexes starting at [0,0]
    """
    if m == 0 or n == 0:
        return []
    if m == 1:
        return [(0, x) for x in range(0, n)]
    if n == 1:
        return [(x, 0) for x in range(0, m)]
    return [(0, x) for x in range(0, n)] + \
           [(x, n - 1) for x in range(1, m)] + \
           [(m - 1, x - 1) for x in range(n - 1, 0, -1)] + \
           [(x, 0) for x in range(m - 2, 0, -1)]


def offset(tuples, (x, y)):
    """
    offset the 2-tuples by an offset coordinate (x, y)
    :param tuples:
    :param x
    :param y
    :return: array of tuples offset by (x,y)
    """
    return [(t[0] + x, t[1] + y) for t in tuples]


def index_gen(m, n):
    result = []
    i = 0
    while m > 0 and n > 0:
        tuples = perindex(m, n)
        perim = offset(tuples, (i, i))
        result.extend(perim)
        m -= 2
        n -= 2
        i += 1
    return result


def testSpiralOrder():
    sol = Solution()
    for i in range(0, 10):
        m = random.randrange(0, 10)
        n = random.randrange(0, 10)
        spiral_matrix = sol.generateMatrix(m, n)
        print format_mat(spiral_matrix)
        print sol.spiralOrder(spiral_matrix)


def testPerindexes(m, n):
    print 'matrix {} x {}'.format(m, n)
    print index_gen(m, n)


def testRandomPerindexes():
    for i in range(0, 10):
        m = random.randrange(0, 10)
        n = random.randrange(0, 10)
        testPerindexes(m, n)


def crossExamine():
    sol = Solution()
    for i in range(0, 10):
        m = random.randrange(0, 10)
        n = random.randrange(0, 10)
        print 'matrix {} x {}'.format(m, n)
        spiral_matrix = sol.generateMatrix(m, n)
        print format_mat(spiral_matrix)
        print sol.spiralOrder(spiral_matrix)
        print index_gen(m, n)


if __name__ == '__main__':
    # testPerindexes(4, 3)
    testRandomPerindexes()
