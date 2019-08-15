def findShapes(image):
    todo = {(x, y) for x in range(len(image)) for y in range(len(image[0])) if image[x][y] == 0}
    result = []
    while todo:
        shape = dfs(image, todo.pop())
        todo -= shape
        result.append(shape)
    return result


def dfs(image, seed):
    shape = set()
    stack = [seed]
    discovered = set()
    while stack:
        i, j = stack.pop()
        discovered.add((i, j))
        cands = [(x, y) for x, y in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]
                 if 0 <= x < len(image[0]) and 0 <= y < len(image)
                 and (x, y) not in discovered and image[x][y] == 0]
        stack.extend(cands)
        shape.add((i, j))
    return shape
