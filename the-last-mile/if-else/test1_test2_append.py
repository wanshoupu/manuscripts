# verbose
if a < 0:
    if q[-1] > q[0]:
        arr.append(q.popleft())
    else:
        arr.append(q.pop())
else:
    if q[-1] < q[0]:
        arr.append(q.popleft())
    else:
        arr.append(q.pop())

#one-liner
arr.append(q.popleft() if (a < 0) == (q[-1] > q[0]) else q.pop())
