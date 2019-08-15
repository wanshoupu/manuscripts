def findShapes(image):
    todo = [(x, y) for x in range(len(image)) for y in range(len(image[0])) if image[x][y] == 0]
    discovered = set()
    result = []
    while todo:
        shape = []
        stack = [todo.pop()]
        while stack:
            i, j = stack.pop()
            discovered.add((i, j))
            cands = [(x, y) for x, y in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)] if
                     0 <= x < len(image[0]) and 0 <= y < len(image) and image[x][y] == 0 and (
                         x, y) not in discovered]
            stack.extend(cands)
            shape.append((i, j))
        result.append(shape)
    return result
