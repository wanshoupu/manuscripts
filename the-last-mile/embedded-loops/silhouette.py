import heapq


def getSkyline(buildings):
    """
    data structure
    1. sort the building by their starting point and if same starting point, heighest goes first
    2. use max-heap for height
    3. result is stored as list of (x,y) each point is one of 4 possible turning point
        a. upturn U, the climbing point
        b. plateau P, the top point
        c. downturn D, the cliff point
        d. valley V, the bottom point
    The algorithm is as follows until all buildings have been processed and heap is empty:
    1. clean up heap
    2. if any V, add to result
    3. if U, add U to result, also add P to result
    4. if next building overlaps with heap top, replenish heap
    5. else it must be a downturn, so add D
    6. go to 1.
    :type buildings: List[(start, end, height)]
    :rtype: List[List[int]]
    """
    if not buildings:
        return []
    buildings.sort(lambda b1, b2: b1[0] - b2[0] if b1[0] != b2[0] else b2[1] - b1[1])
    heap = [(0, buildings[0][0], buildings[-1][1])]
    '''
    (-height, start, end)
    negated height is used as the heap key: the actual height = - heap[0]
    '''
    result = [(buildings[0][0], 0)]
    i = 0
    while heap:
        assert heap
        if heap[0][2] <= result[-1][0]:
            heapq.heappop(heap)
        elif result[-1][1] > -heap[0][0]:
            result.append((result[-1][0], -heap[0][0]))  # valley point
        elif -heap[0][0] > result[-1][1]:
            result.append((heap[0][1], result[-1][1]))  # upturn
            result.append((heap[0][1], -heap[0][0]))  # plateau
        elif i < len(buildings) and buildings[i][0] <= heap[0][2]:
            heapq.heappush(heap, (-buildings[i][2], buildings[i][0], buildings[i][1]))
            i += 1
        else:
            assert -heap[0][0] == result[-1][1]
            result.append((heap[0][2], -heap[0][0]))  # downturn

    # This is to append the last point for the skyline
    result.append((result[-1][0], 0))
    return result
