def spiral_term(m, n):
    """
    Return the position where the clock-wise spiral order terminates
    :param m:
    :param n:
    :return:
    """
    if m == 0 or n == 0:
        # matrix is empty, not real but serves as a base case
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


def test_spiral_term():
    print spiral_term(0, 0)  # empty grid_2d
    print spiral_term(1, 1)  # single element
    print spiral_term(2, 2)  # 2x2 (0,1)
    print spiral_term(3, 2)  # 3x2 (0,1)
    print spiral_term(2, 3)  # 2x3 (0,1)
    print spiral_term(3, 3)  # 3x3 (1,1)
    print spiral_term(3, 4)  # 4x3 (2,1)
    print spiral_term(6, 9)  # 6x9 (


if __name__ == '__main__':
    test_spiral_term()
