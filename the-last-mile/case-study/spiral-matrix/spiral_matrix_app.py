import spiral_indexes


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
