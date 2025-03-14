package org.shoupu.BinarySearchTree;

/**
 * Find the nth node in a binary search tree (or binary tree with the same definition of number
 * as in BST)
 * The nth node problem is similar to a group of other binary tree problems
 * which do not have parent pointer. Basically, you need a helper structure
 * to get more information than what's being asked.
 *
 * Another similar problem is finding the longest path of a binary tree
 *
 * @author shoupu
 */
public class NthNode2018 {
    /**
     * The key of this problem is to decide what convention makes sense.
     * You should reject the '0th' element and use 1st as the first element.
     *
     * @param root
     * @param n
     * @return
     */
    public static Node nthNode(Node root, int n) {
        if (n < 1) return null;
        Object obj = helper(root, n);
        if (obj instanceof Node) {
            return (Node) obj;
        }
        return null;
    }

    /**
     * If there are sufficient nodes in the tree, return the nth node (starting with 1st, 2nd, etc.)
     * Else if there are less than n nodes, return the count of nodes
     *
     * @param root root of tree
     * @param n    ordinal number > 0
     * @return the nth node or number of node if the total number of nodes in this tree is less
     * than the number given
     */
    private static Object helper(Node root, int n) {
        if (root == null) return 0;
        Object left = helper(root.left, n);
        if (left instanceof Node) {
            return left;
        }
        int leftCount = (int) left;
        if (leftCount + 1 == n) {
            return root;
        }
        Object right = helper(root.right, n - leftCount - 1);
        if (right instanceof Node) {
            return right;
        }
        return leftCount + 1 + (int) right;
    }
}
