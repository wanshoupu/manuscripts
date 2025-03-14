def findMaximizedCapital(cap: int, capitals: List[int], profits: List[int], k: int) -> int:
    projects = [(c, p) for c, p in zip(capitals, profits)]
    for _ in range(k):
        if not projects: break
        attainable = [i for i, (c, _) in enumerate(projects) if c <= cap]
        if not attainable: break
        i = max(attainable, key=lambda i: projects[i][1])
        c, p = projects.pop(i)
        cap += p
    return cap
