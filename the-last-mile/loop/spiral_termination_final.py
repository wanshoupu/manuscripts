def spiral_term(m, n):
    i, j = 0, 0
    while 2 < m and 2 < n:
        i += 1
        j += 1
        m -= 2
        n -= 2
    if m == 1:
        return i, j + n - 1
    if n == 1:
        return i + m - 1, j
    return i + 1, j
