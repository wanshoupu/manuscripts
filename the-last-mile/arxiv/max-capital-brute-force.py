def findMaximizedCapital(cap: int, capitals: List[int], profits: List[int], k: int) -> int:
    projects = [(c, p) for c, p in zip(capitals, profits)]
    projects.sort(reverse=True)
    heap = []
    for _ in range(k):
        while projects and projects[-1][0] <= cap:
            c, p = projects.pop()
            heappush(heap, (-p, p))
        if not heap: break
        _, p = heappop(heap)
        cap += p
    return cap
