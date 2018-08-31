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
        if xp < x:
            break
        yp = len(matrix[0]) - y - 1
        if yp < y:
            break
        if xp == x:
            result.extend(matrix[x][y:yp + 1])
            break
        if yp == y:
            result.extend([matrix[i][y] for i in range(x, xp + 1)])
            break
        result.extend(matrix[x][y:yp])
        result.extend([matrix[i][yp] for i in range(x, xp)])
        result.extend(matrix[xp][yp:y:-1])
        result.extend([matrix[i][y] for i in range(xp, x, -1)])
        x += 1
        y += 1
    return result
