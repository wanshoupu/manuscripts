def plausible(seed):
    return {1, 2, 3, 4} - {colors.get(n, 0) for n in graph[seed]}


def color(seed):
    plausible_colors = plausible(seed)
    for c in plausible_colors:
        colors[seed] = c
        for nei in graph[seed]:
            if nei not in colors:
                if not color(nei):
                    break
        else:
            return True
    return False


N = 10
graph = {i: set() for i in range(N)}
colors = {}
color(0)
