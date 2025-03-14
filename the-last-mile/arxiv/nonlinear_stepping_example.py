def is_happy(n):
    discovered = set()
    while n not in discovered:
        discovered.add(n)
        n = sum(int(i) ** 2 for i in str(n))
    return n == 1
