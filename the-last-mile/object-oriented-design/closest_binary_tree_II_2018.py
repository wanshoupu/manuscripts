from bound_heap import BoundHeap


class TreeNode(object):
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class HeapSolution(object):
    def closestKValues(self, root, k, target):
        heap = BoundHeap(k)
        self.closest_helper(root, target, heap)
        return sorted(x[1] for x in heap.get())

    def closest_helper(self, root, target, heap):
        if not root:
            return
        heap.push((-abs(target - root.val), root.val))
        if root.val < target:
            self.closest_helper(root.right, target, heap)
        elif root.val > target:
            self.closest_helper(root.left, target, heap)
        else:
            self.closest_helper(root.right, target, heap)
            self.closest_helper(root.left, target, heap)


def test1():
    global sol, root, root
    sol = HeapSolution()
    root = TreeNode(1)
    right = TreeNode(2)
    right.left = TreeNode(2)
    root.right = right
    closest = sol.closestKValues(root, 2, 1.2)
    assert closest == [1, 2], closest


if __name__ == '__main__':
    test1()

    root = TreeNode(1)
    root.right = TreeNode(1)
    closest = sol.closestKValues(root, 2, 2.2)
    assert closest == [1, 1], closest

    root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(2)
    assert sol.closestKValues(root, 2, 2.2) == [2, 2]

    root = TreeNode(-1)
    root.left = TreeNode(1)
    root.right = TreeNode(2)
    assert sol.closestKValues(root, 2, -1.2) == [-1, 1]

    # Input: root = [4,2,5,1,3], target = 3.714286, and k = 2
    #
    #     4
    #    / \
    #   2   5
    #  / \
    # 1   3
    #
    # Output: [4,3]
    root = TreeNode(4)
    node1 = TreeNode(1)
    node2 = TreeNode(2)
    node3 = TreeNode(3)
    node5 = TreeNode(5)
    node2.left = node1
    node2.right = node3
    root.left = node2
    root.right = node5
    ans = sol.closestKValues(root, 2, 3.714286)
    assert set(ans) == {4, 3}, ans
