def jump(nums):
    """
    :type nums: List[int]
    :rtype: int
    """
    farthest = 0  # the farthest index reachable with 'steps' steps
    steps = 0  # the minimum number of steps to reach farthest
    frontier = 0  # the frontier reachable index
    # loop-invariants:
    # current <= farthest <= frontier
    for current, stride in enumerate(nums[:-1]):
        frontier = max(frontier, stride + current)
        if farthest == current:  # farthest is the nearest base to reach frontier in one step
            farthest = frontier  # projectile farthest to frontier
            steps += 1  # increment step
    if len(nums) - 1 <= farthest:
        return steps
    return -1


if __name__ == '__main__':
    tests = [
        [[0], 0],
        [[0, 0], -1],
        [[1, 1, 2, 1, 4], 3],
        [[7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3], 2],
        [[7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], -1],
    ]
    for test in tests:
        ans = jump(test[0])
        assert ans == test[1], '%s, %s' % (test, ans)
