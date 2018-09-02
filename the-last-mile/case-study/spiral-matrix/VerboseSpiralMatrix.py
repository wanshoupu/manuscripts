def spiral_order(mat):
    """
    Flatten
    :param mat:
    :return:
    """
    result = []
    # For empty matrix, the following 'j2 = matrix[0].length' would cause OOB exception
    # So check this is not the case
    if not mat:
        return result
    # i1, i2, j1, j2 are the top, bottom, left, and right bounds for the 'remaining' matrix
    i1, i2, j1, j2 = 0, len(mat), 0, len(mat[0])
    # s is a zero-based ordinal number that is used to keep track of direction of motion
    s = 0
    while i1 < i2 and j1 < j2:
        if s % 4 == 0:  # upper row
            result.extend(mat[i1][j1:j2])
            i1 += 1
        elif s % 4 == 1:  # right column
            j2 -= 1  # pre-increment because of '1-pass-index' notation
            result.extend([mat[i][j2] for i in range(i1, i2)])
        elif s % 4 == 2:  # bottom row leftward
            i2 -= 1  # pre-increment because of '1-pass-index' notation
            # Beware of the unexpected behavior of expr: mat[i2][j2 - 1:j1 - 1:-1]
            result.extend([mat[i2][i] for i in range(j2 - 1, j1 - 1, -1)])
        else:  # left column upward
            result.extend([mat[i][j1] for i in range(i2 - 1, i1 - 1, -1)])
            j1 += 1
        s += 1
    return result
