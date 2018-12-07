Node findAncestor(Node root, int a, int b) {
    if (root == null) return null;
    if (find(root, a) == null || find(root, b) == null)
        return null;
    if (a < root.data && b < root.data)
        return findAncestor(root.left, a, b);
    if (a > root.data && b > root.data)
        return findAncestor(root.right, a, b);
    return root;
}
