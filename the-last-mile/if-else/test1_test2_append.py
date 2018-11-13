# verbose
if a < 0:
    if interm[-1] > interm[0]:
        result.append(interm.popleft())
    else:
        result.append(interm.pop())
else:
    if interm[-1] < interm[0]:
        result.append(interm.popleft())
    else:
        result.append(interm.pop())

#one-liner
result.append(interm.popleft() if (a < 0) == (interm[-1] > interm[0]) else interm.pop())
