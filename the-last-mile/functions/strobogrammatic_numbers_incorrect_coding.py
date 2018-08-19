class Solution(object):
    """
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
    Find all strobogrammatic numbers that are of length = n.
    """

    def __init__(self):
        self.strobo_map = {'0': '0', '1': '1', '8': '8', '6': '9', '9': '6'}

    def findStrobogrammatic(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        result = ['0', '1', '8'] if n & 1 else ['']
        while len(result[0]) < n:
            result = [x + base + self.strobo_map[x] for base in result for x in
                      self.strobo_map.keys()]
        return list(str(y) for y in set(int(x) for x in result))


if __name__ == '__main__':
    tests = [
        # [0, ['']],
        [1, ['0', '1', '8']],
        [2, ['00', "11", "69", "88", "96"]],
        [3, ['101', '000', '609', '906', '808',
             '111', '010', '619', '916', '818',
             '181', '080', '689', '986', '888']],
    ]
    sol = Solution()
    for test in tests:
        ans = sol.findStrobogrammatic(test[0])
        assert set(ans) == set(test[1]), ans
