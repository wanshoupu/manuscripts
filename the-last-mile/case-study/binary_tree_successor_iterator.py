from datastructure.tree_node import TreeNode

class Successor(object):
    """
    This implementation doesn't do recursion.
    It relies on building bridges between leaf node and their ancestors using the NULL right pointer
    After the iteration passes from leaf node back to the ancestor node, the bridge should be immediately
    demolished. So after a complete iteration, the original tree structure should be intact.
    """

    def __init__(self, root, curr=None, limit=-1):
        """
        root is the root node of binary tree
        curr is a starting node in binary tree
        :param root:
        :param curr:
        :param limit:
        """
        self.anchor = TreeNode(0)
        self.anchor.right = root
        self.curr = self.anchor
        self.limit = limit
        self.counter = 0

    def __iter__(self):
        return self

    def next(self):
        self.goto_next()
        self.counter += 1
        return self.curr.val

    def goto_next(self):
        if self.counter == self.limit or self.curr.right is None:
            raise StopIteration()
        if self.is_bridge(self.curr, self.curr.right):
            self.goto_ancestor()
        else:
            self.goto_leftmost_leaf_node(self.curr)

    def goto_ancestor(self):
        # pass bridge
        ancestor = self.curr.right
        # demolish bridge
        self.curr.right = None
        self.curr = ancestor

    def is_bridge(self, leaf_node, ancestor):
        node = ancestor.left
        while node:
            if node is leaf_node:
                return True
            node = node.right
        return False

    def goto_leftmost_leaf_node(self, root):
        """
        Build bridges along the way
        :param root:
        :return:
        """
        node = root.right
        while node.left:
            # build bridge
            rightmost = self.find_rightmost_leaf_node(node.left)
            rightmost.right = node
            # descend to left subtree
            node = node.left
        self.curr = node

    def find_rightmost_leaf_node(self, node):
        while node.right:
            node = node.right
        return node


def test_1():
    global root, sol
    root = TreeNode(1)
    right = TreeNode(2)
    right.left = TreeNode(2)
    root.right = right
    sol = Successor(root, right, 0)
    try:
        sol.next()
    except StopIteration:
        return
    assert False


def test_2():
    root = TreeNode(-1)
    root.right = TreeNode(1)
    sol = Successor(root, root)
    iterated_val = [x for x in sol]
    assert iterated_val == [-1, 1], iterated_val


def test_3():
    root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(2)
    sol = Successor(root, root.left)
    iterated_val = [x for x in sol]
    assert iterated_val == [1, 2, 2], iterated_val


import random


def build_random_tree(count=5, min_val=-1000, max_val=1000):
    if count:
        root = TreeNode(random.randint(min_val, max_val))
        left_count = random.randint(0, count - 1)
        root.left = build_random_tree(left_count, min_val, root.val)
        root.right = build_random_tree(count - left_count - 1, root.val, max_val)
        return root
    else:
        return None


def test_random():
    root = build_random_tree(random.randint(0, 10))
    print_tree(root)
    sol = Successor(root)
    iterated_val = [x for x in sol]
    expected = [x for x in tree_iter(root)]
    print
    print iterated_val
    print_tree(root)
    print


def print_tree(root):
    if root:
        print_tree(root.left)
        print str(root.val) + ',',
        print_tree(root.right)


def test_tree_iter():
    root = build_random_tree(random.randint(0, 15))
    print_tree(root)
    print
    print [x for x in tree_iter(root)]


def tree_iter(root):
    if root:
        tree_iter(root.left)
        yield root.val
        tree_iter(root.right)


def test_mess_up_with_multi_iter():
    """
    Expect mess up with multiple iterators, since the implementation modifies the original
    data structure. Problems can be
    1. Program never terminate
    2. Tree is messed up by premature termination of iterator
    3. Incomplete iteration
    :return:
    """
    count = random.randint(0, 10)
    root = build_random_tree(count)
    print_tree(root)
    sol1 = Successor(root)
    sol2 = Successor(root)
    arr1 = []
    arr2 = []
    try:
        for i in range(count):
            arr1.append(sol1.next())
            arr1.append(sol1.next())
            arr2.append(sol2.next())
            arr1.append(sol1.next())
            arr2.append(sol2.next())
            arr2.append(sol2.next())
    except StopIteration:
        pass
    print arr1
    print arr2

    # tree messed up by premature termination of iterator
    print_tree(root)


if __name__ == '__main__':
    test_1()
    test_2()
    test_3()
    test_tree_iter()
    test_random()
    # test_mess_up_with_multi_iter()
