def spiral_indexes(m, n):
    # constants
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]

    # variables
    limits = [0, 0, n, m]
    direction = 0
    x = 0
    y = 0
    for v in range(0, m * n):
        yield x, y
        nx = x + dx[direction]
        ny = y + dy[direction]
        if nx < limits[1] or limits[3] <= nx or ny < limits[0] or limits[2] <= ny:
            direction = (direction + 1) % 4
            limits[direction] = limits[direction] + (1 if direction < 2 else -1)
            nx = x + dx[direction]
            ny = y + dy[direction]
        x = nx
        y = ny
