class Solution(object):
    def findMissingRanges(self, arr, lower, upper):
        """
        Get the missing ranges in the interval [START, END]
        :param arr: is sorted integer array
        :return:
        """
        result = []
        sweep = lower
        for n in arr + [upper + 1]:  # append sentinel element
            if sweep == n - 1:
                result.append(sweep)
            elif sweep < n - 1:
                result.append((sweep, n - 1))
            sweep = n + 1
        return result
