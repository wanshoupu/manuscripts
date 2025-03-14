class Solution(object):
    def matrixReshape(self, mat, r, c):
        """
        :type mat: List[List[int]]
        :type r: int
        :type c: int
        :rtype: List[List[int]]
        """
        if not mat or not mat[0]:
            return mat
        if len(mat) * len(mat[0]) != r * c:
            return mat
        result = []
        it = matiter(mat)
        for i in range(r):
            row = []
            for j in range(c):
                row.append(it.next())
            result.append(row)
        return result


def matiter(mat):
    for row in mat:
        for cell in row:
            yield cell


if __name__ == '__main__':
    tests = [
        [[[1, 2],
          [3, 4]], 1, 4, [[1, 2, 3, 4]]],
    ]
    sol = Solution()
    for mat, r, c, ans in tests:
        newMat = sol.matrixReshape(mat, r, c)
        assert newMat == ans, (mat, r, c, ans, newMat)
