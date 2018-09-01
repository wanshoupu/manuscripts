def spiral_order(matrix):
    """
    Iterative solution for flattening a 2D matrix in spiral order
    :type matrix: List[List[int]]
    :rtype: List[int]
    """
    # return spiral_order_recursive(matrix, 0, 0)
    x = y = 0
    result = []
    while True:
        xp = len(matrix) - 1 - x  # xp is the opposite index of x
        if xp < x:  # no more rows left
            break
        yp = len(matrix[0]) - y - 1
        if yp < y:  # no more columns left
            break
        if xp == x:  # only one row left
            result.extend(matrix[x][y:yp + 1])
            break
        if yp == y:  # only one column left
            result.extend([matrix[i][y] for i in range(x, xp + 1)])
            break
        result.extend(matrix[x][y:yp])  # upper row
        result.extend([matrix[i][yp] for i in range(x, xp)])  # right column
        result.extend(matrix[xp][yp:y:-1])  # bottom row
        result.extend([matrix[i][y] for i in range(xp, x, -1)])  # left column
        x += 1
        y += 1
    return result
