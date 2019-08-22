"""
"""
from typing import List


class Solution:
    """
    If we consider a linear array:
    w(i) := 2-tuple representing the 'skip' or 'sell' components for the nums[:i] subarray
    w(0) = (0,0)
    w(i) = (max(w(i)[1], w(i)[0]), w(i)[0] + nums[i-1])
    Now that we consider a circular array, which is topologically different, we have to include
    4-tuple with components represent (0,0), (0,1), (1,0), (1,1) respectively
    """

    def sell(self, nums: List[int]) -> int:
        if not nums:
            return 0
        if len(nums) < 4:
            return max(nums)
        result = [(0, nums[1], nums[0], 0)]
        for i in range(2, len(nums) - 1):
            r00, r01, r10, r11 = result[-1]
            result.append((max(r00, r01), nums[i] + r00, max(r10, r11), nums[i] + r10))
        r00, r01, r10, r11 = result[-1]
        return max(r00 + nums[-1], r01, r10, r11)


if __name__ == '__main__':
    tests = [
        [[2, 3, 2], 3],
        [[1, 2, 3, 1], 4],
    ]
    sol = Solution()
    for test, ans in tests:
        print(test)
        result = sol.sell(test)
        assert ans == result, (test, result)
