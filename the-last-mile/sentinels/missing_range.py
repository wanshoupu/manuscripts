START = 0
END = 100

"""
Given a sorted integer array where the range of elements are [0, 99] inclusive, return its 
missing ranges.

For example, given [0, 1, 3, 50, 75], return ["2", "4->49", "51->74", "76->99"]
"""


class Solution(object):
    def missingRanges(self, arr):
        """
        Get the missing ranges in the interval [START, END]
        :param arr: is sorted integer array
        :return:
        """
        result = []
        start = START
        for n in arr + [END]:  # add sentinel
            if start != n:
                assert start < n
                result.append(start if n - 1 == start else (start, n - 1))
            start = n + 1
        # the sentinel [END + 1] saves the following steps:
        # if start < END:
        #     result.append((start, END))
        # elif start == END:
        #     result.append(END)
        return result
