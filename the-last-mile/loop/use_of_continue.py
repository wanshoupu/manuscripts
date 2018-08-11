class Solution(object):
    def trap(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        stack = []
        i, water, bottom = 0, 0, 0
        # i=index,
        # water = total water accumulated so far,
        # bottom = the bottom of the rectangle to be calculated
        while i < len(height):
            if stack:
                j = stack[-1]
                # j and i are the indexes of the ends
                bar = min(height[j], height[i])
                water += (bar - bottom) * (i - j - 1)
                bottom = bar
                # if stack[-1] <= height[i], pop stack else keep
                if height[j] <= height[i]:
                    stack.pop()
                    # not done with accumulating water at this end
                    # wait to push i into stack
                    continue
            stack.append(i)
            i += 1
        return water


def original_test_case():
    array = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
    array = [2, 1, 2]
    array = [2, 1, 3, 1, 2]
    sol = Solution()
    print array, sol.trap(array)


def random_test_case():
    n = 9
    sol = Solution()
    for i in range(10):
        array = [random.randint(1, n) for x in range(n)]
        print array, sol.trap(array)


if __name__ == '__main__':
    import random

    original_test_case()
    random_test_case()
