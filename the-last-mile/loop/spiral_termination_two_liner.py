def spiral_term(m, n):
    """
    There are two conditions that are relevant:
    out of m and n, who is smaller?
    the parity of the smaller
    Return the position where the clock-wise spiral order terminates
    :param m:
    :param n:
    :return:
    """
    if m <= n:
        return m >> 1, n - 1 - (m >> 1) if m & 1 else (m >> 1) - 1
    return m - 1 - (n >> 1) if n & 1 else n >> 1, (n - 1) >> 1
