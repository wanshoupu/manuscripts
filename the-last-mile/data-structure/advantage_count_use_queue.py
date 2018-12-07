class Solution(object):
    def advantageCount(self, a, b):
        """
        :type a: List[int]
        :type b: List[int]
        :rtype: List[int]
        """
        from collections import deque
        aSorted = deque(sorted(range(len(a)), key=lambda i: a[i]))
        bSorted = deque(sorted(range(len(b)), key=lambda i: b[i]))
        result = [None] * len(a)
        while aSorted:
            key = bSorted.pop() if a[aSorted[0]] <= b[bSorted[0]] else bSorted.popleft()
            result[key] = a[aSorted.popleft()]
        return result
