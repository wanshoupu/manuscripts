def spiral_term(m, n):
    i, j = 0, -1
    while 1 < m and 1 < n:
        i += 1
        j += 1
        m -= 2
        n -= 2
    if m * n == 0:
        return i, j
    if 1 == n:
        return i + m - 1, j + 1
    return i, j + n
