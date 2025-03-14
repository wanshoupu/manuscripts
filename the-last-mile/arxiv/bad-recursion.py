class Node(object):
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None


def fool(root, container):
    if not root:
        return
    container.append(root.val)
    fool(root.left, container)
    fool(root.right, container)


def foo(root, param=''):
    if not root:
        return param
    return param + root.val + foo(root.left, param) + foo(root.right, param)


if __name__ == '__main__':
    root = Node('3')
    root.left = Node('5')
    right = Node('-2')
    right.right = Node('b')
    root.right = right
    result1 = []
    fool(root, result1)
    result1 = ''.join(result1)
    result = foo(root, '')
    assert result == result1
