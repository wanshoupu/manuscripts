# Refactored cascading conditional
if key not in lookup:
    heappush(lookup[key + 1], 1)
elif len(lookup[key]) == 1:
    val, = lookup.pop(key)  # unpack into val
    heappush(lookup[key + 1], val + 1)
else:
    # pop the top element, increment it,
    newVal = heappop(lookup[key]) + 1
    # push newVal back
    heappush(lookup[key + 1], newVal)

