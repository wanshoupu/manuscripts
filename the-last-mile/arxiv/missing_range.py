class Solution(object):
    def findMissingRanges(self, arr, lower, upper):
        """
        Get the missing ranges in the interval [START, END]
        :param arr: is sorted integer array
        :return:
        """
        result = []
        sweep = lower
        for n in arr:
            if sweep == n - 1:
                result.append(sweep)
            elif sweep < n - 1:
                result.append((sweep, n - 1))
            sweep = n + 1
        if sweep < upper:
            result.append((sweep, upper))
        elif sweep == upper:
            result.append(upper)
        return result
