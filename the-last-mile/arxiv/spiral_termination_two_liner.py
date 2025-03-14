def spiral_term(m, n):
    """
    There are two conditions that are relevant:
    the minimum between m and n and
    the parity of the minimal value
    Return the position where the clock-wise spiral order terminates
    :param m:
    :param n:
    :return:
    """
    if m <= n:
        return m >> 1, n - 1 - (m >> 1) if m & 1 else (m >> 1) - 1
    return m - 1 - (n >> 1) if n & 1 else n >> 1, (n - 1) >> 1
