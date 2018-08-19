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
        if n < 2:
            return ['0', '1', '8'] if n & 1 else ['']
        result = self.numeral_comb(n - 2)
        return [x + base + self.strobo_map[x] for base in result for x in
                set(self.strobo_map.keys()) - {'0'}]

    def numeral_comb(self, n):
        result = ['0', '1', '8'] if n & 1 else ['']
        while len(result[0]) < n:
            result = [x + base + self.strobo_map[x] for base in result for x in
                      self.strobo_map.keys()]
        return result


if __name__ == '__main__':
    tests = [
        [0, ['']],
        [1, ['0', '1', '8']],
        [2, ["11", "69", "88", "96"]],
        [3, ['101', '609', '906', '808',
             '111', '619', '916', '818',
             '181', '689', '986', '888']],
    ]
    sol = Solution()
    for test in tests:
        ans = sol.findStrobogrammatic(test[0])
        assert set(ans) == set(test[1]), ans
