for i in range(10):
    if i % 2:
        print i ** 2
        continue
    print i / 2

# equivalent to:

for i in range(10):
    if i % 2:
        print i ** 2
    else:
        print i / 2
