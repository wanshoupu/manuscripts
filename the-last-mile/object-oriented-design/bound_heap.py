import heapq


class BoundHeap(object):
    """
    This represents a bounded heap with size <= given size
    """

    def __init__(self, size):
        """
        Size is the maximum size of the heap
        :param size:
        """
        self.size = size
        self.heap = []

    def push(self, val):
        """
        Push the value into the heap
        # Use __lt__ if available; otherwise, try __le__.
        :param val:
        :return:
        """
        heapq.heappush(self.heap, val)
        if len(self.heap) > self.size:
            heapq.heappop(self.heap)

    def get(self):
        return self.heap


if __name__ == '__main__':
    tests = [
        [],
        [],
        [],
        [],
    ]
