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
        it = matiter(mat)
        result = []
        for i in range(r):
            result.append([it.next() for _ in range(c)])
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
