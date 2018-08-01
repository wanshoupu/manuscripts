Node findAncestor(Node root, int a, int b){
    if(root == null) return null;
    if(a < root.val && b < root.val)
        return findAncestor(root.left, a, b);
    if(a > root.val && b > root.val)
        return findAncestor(root.right, a, b);
    if(find(root, a) == null || find(root, b) == null)
        return null;
    return root;
}