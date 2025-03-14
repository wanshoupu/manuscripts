...
if index < n and (not heap or heap[0][0] >= source[index][0]):
    left, right, val = source[index]
    index += 1
else:
    left, right, val = heapq.heappop(heap)

if l < left:
    if left > r:
        result.append((l, v))
        result.append((r, 0))
        l, r, v = left, right, val
    elif left == r:
        if val == v:
            r = right
        else:
            result.append((l, v))
            l, r, v = left, right, val
    else:
        if val > v:
            result.append((l, v))
            if right < r:
                heapq.heappush(heap, (right, r, v))
            l, r, v = left, right, val
        elif val == v:
            r = max(r, right)
        else:
            if right > r:
                heapq.heappush(heap, (r, right, val))
else:
    if v > val:
        if right > r:
            heapq.heappush(heap, (r, right, val))
    elif v == val:
        r = max(r, right)
    else:
        if right >= r:
            r, v = right, val
        else:
            heapq.heappush(heap, (right, r, v))
            r, v = right, val
...
