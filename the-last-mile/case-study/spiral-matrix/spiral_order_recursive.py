def spiral_order_recursive(mat, x, y):
    """
    Recursive solution for flattening a 2D matrix in spiral order
    :param mat:
    :param x:
    :param y:
    :return:
    """
    # xp is the terminating index along x-axis
    xp = len(mat) - x - 1
    if xp < x:
        # if there is none left in the matrix
        return []
    # yp is the terinating index along y-axis
    yp = len(mat[x]) - y - 1
    if yp < y:
        # if there is none left in the matrix
        return []
    if xp == x:
        return mat[x][y:yp + 1]
    if yp == y:
        return [mat[i][y] for i in range(x, xp + 1)]
    return mat[x][y:yp] + [mat[i][yp] for i in range(x, xp)] + mat[xp][yp:y:-1] + \
           [mat[i][y] for i in range(xp, x, -1)] + \
           spiral_order_recursive(mat, x + 1, y + 1)
