def spiral_term(m, n):
    """
    Return the last cell [i,j] of a matrix
    if printed in spiral fashion
    :param m: row size
    :param n: column size
    :return: (i,j) terminal indexes
    """
    if m == 0 or n == 0:
        # empty matrix as a base case
        return 0, -1
    elif m == 1:
        # matrix is a row
        return 0, n - 1
    elif n == 1:
        # matrix is a column
        return m - 1, 0
    else:
        x, y = spiral_term(m - 2, n - 2)
        return 1 + x, 1 + y
