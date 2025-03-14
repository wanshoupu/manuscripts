class Counter(dict):
    """
    A 'smart' counter class based on dictionary
    This counter can only handle non-negative counts.
    """

    def decrement(self, key):
        if self[key] > 1:
            self[key] -= 1
        else:
            self.pop(key)

    def increment(self, key):
        self[key] = self.get(key, 0) + 1


class Solution(object):
    """
    Longest Substring with At Most K Distinct Characters
    This is a typical coding oriented problem. Algorithm wise it's simple. But how to code up
    concise, readable program is challenging.

    1. Need a counter class that's not available in built-in Python
    2. Embedded Loop push-up to eliminate embedded loop.
    This 2. may not always be possible. It may need change of data structure to support this push-up
    """

    def lengthOfLongestSubstringKDistinct(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: int
        """
        if k == 0:
            return 0
        distinct, start = Counter(), 0
        maxLength = 0
        for curr in range(len(s)):
            distinct.increment(s[curr])
            while len(distinct) > k:
                distinct.decrement(s[start])
                start += 1
            maxLength = max(maxLength, 1 + curr - start)
        return maxLength

    def lengthOfUniqCharSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        from collections import OrderedDict
        od = OrderedDict()
        maxLength = 0
        for c in s:
            while c in od:
                od.popitem(last=False)
            od[c] = None
            maxLength = max(maxLength, len(od))
        return maxLength


def test_lengthOfUniqCharSubstring():
    sol = Solution()
    tests = [
        ['abc', 3],
        ['aaabbbbcde', 4],
        ['aaabbbbccddeeff', 2],
    ]
    for test in tests:
        k_distinct = sol.lengthOfUniqCharSubstring(test[0])
        print test, k_distinct
        assert k_distinct == test[1], k_distinct


def test_lengthOfLongestSubstringKDistinct():
    sol = Solution()
    tests = [
        ['abc', 3, 3],
        ['aaabbbbcde', 3, 8],
        ['aaabbbbccddeeff', 3, 9],
    ]
    for test in tests:
        k_distinct = sol.lengthOfLongestSubstringKDistinct(test[0], test[1])
        print test, k_distinct
        assert k_distinct == test[2], k_distinct


if __name__ == '__main__':
    test_lengthOfUniqCharSubstring()
    test_lengthOfLongestSubstringKDistinct()
