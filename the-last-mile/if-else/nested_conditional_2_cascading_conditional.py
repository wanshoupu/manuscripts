# nested conditional

if n not in lookup:
    heappush(lookup[n + 1], 1)
else:
    vals = lookup[n]
    # pop the top element, increment it,
    newVal = heappop(vals) + 1
    if not vals:
        lookup.pop(n)
    # push newVal back
    heappush(lookup[n + 1], newVal)

# Refactored cascading conditional
if n not in lookup:
    heappush(lookup[n + 1], 1)
elif len(lookup[n]) == 1:
    val, = lookup.pop(n)  # unpack into val
    heappush(lookup[n + 1], val + 1)
else:
    # pop the top element, increment it,
    newVal = heappop(lookup[n]) + 1
    # push newVal back
    heappush(lookup[n + 1], newVal)

