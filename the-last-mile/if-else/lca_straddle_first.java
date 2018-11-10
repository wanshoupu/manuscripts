Node findAncestor(Node root, int a, int b) {
    if (root == null) return null;
    if (Math.max(a,b) >= root.data || Math.min(a,b) <= root.data)
        return root
    if (a < root.data && b < root.data)
        return findAncestor(root.left, a, b);
    if (a > root.data && b > root.data)
        return findAncestor(root.right, a, b);
    if (find(root, a) == null || find(root, b) == null)
        return null;
    return root;
}
