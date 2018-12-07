# nested conditional
key = 3
if key not in lookup:
    heappush(lookup[key + 1], 1)
else:
    vals = lookup[key]
    # pop the top element, increment it,
    newVal = heappop(vals) + 1
    if not vals:
        lookup.pop(key)
    # push newVal back
    heappush(lookup[key + 1], newVal)

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


# reduce duplicate code
if key not in lookup:
    seq = 1
elif len(lookup[key]) == 1:
    # pop the entry, get first element and increment it,
    seq = lookup.pop(key)[0] + 1
else:
    # pop the top element, increment it,
    seq = hp.heappop(lookup[key]) + 1
# push seq back
hp.heappush(lookup[key + 1], seq)
