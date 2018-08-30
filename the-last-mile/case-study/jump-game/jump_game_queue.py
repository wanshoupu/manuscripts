from collections import deque


def jump(nums):
    """
    DP reduced to greedy algorithm.
    :type nums: List[int]
    :rtype: int
    """
    reaches = deque([(0, 0)])
    for i in range(len(nums)):
        if reaches[0][0] < i:
            reaches.popleft()
        if not reaches:
            return -1
        farthest, n = reaches[-1]
        # farthest is the farthest index
        # n is the number of steps reaching farthest
        if farthest < i + nums[i]:
            reaches.append((i + nums[i], 1 + reaches[0][1]))
    return reaches[0][1]
