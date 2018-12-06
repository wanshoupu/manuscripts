def spiral_term(m, n):
    x, y = 0, -1
    while m and n:
        if 1 == m:
            y += n
            m = 0
        elif 1 == n:
            x += m - 1
            y += 1
            n = 0
        else:
            x += 1
            y += 1
            m -= 2
            n -= 2
    return x, y
