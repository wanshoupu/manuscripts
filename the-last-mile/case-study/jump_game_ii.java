class Solution(object):
    """
    Given an array of non-negative integers, you are initially positioned at the first index of
    the array.
    Each element in the array represents your maximum jump length at that position.
    Your goal is to reach the last index in the minimum number of jumps.
    For example:
    Given array A = [2,3,1,1,4]
    """

    def jump(self, nums):
        """
        DP reduced to greedy algorithm
        Also it's like a quantum computing algorithm
        Record the farthest index that can be reached and the minimum number of steps to reach
        there.
        All the indexes between the current index and the farthest can serve as jump board to
        still farther indexes.
        A quantum algorithm would be dispatch demons, one each on all these indexes and see who
        reaches the farthest next.
        :type nums: List[int]
        :rtype: int
        """
        reaches = [(0, 0)]
        curr = 0  # index into reaches
        for i in range(len(nums) + 1):
            farthest, n = reaches[-1]
            # farthest is the farthest index
            # n is the number of steps reaching farthest
            if len(nums) - 1 <= farthest:
                return n
            if reaches[curr][0] < i:
                curr += 1
            if farthest < i + nums[i]:
                reaches.append((i + nums[i], 1 + reaches[curr][1]))


if __name__ == '__main__':
    sol = Solution()
    jump = sol.jump([1, 1, 2, 1, 4])
    assert jump == 3, jump

    sol = Solution()
    jump = sol.jump([7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3])
    assert jump == 2, jump

    sol = Solution()
    jump = sol.jump([0, 0, 0])
    assert jump == 2, jump
