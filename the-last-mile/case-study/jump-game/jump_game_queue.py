from collections import deque


def jump(nums):
    """
    DP reduced to greedy algorithm.
    Also it's like a quantum computing algorithm.
    Record the farthest index that can be reached and the minimum number of steps to reach there
    All the indexes between the current index and the farthest can serve as jump board to
    still farther indexes.
    A quantum algorithm would be dispatch demons, one each on all these indexes and see who
    reaches the farthest next.
    :type nums: List[int]
    :rtype: int
    """
    # return self.optimal_impl(nums)
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
    print reaches
    return reaches[0][1]
