package org.shoupu.linkedList;

import org.shoupu.datastructure.ListNode;

/**
 * Remove Nth Node From End of List
 * Swap pairs of nodes in linked list
 * Add two numbers in linked list
 *
 * @author shoupu
 */
public class LinkedListAlgorithms {
    /**
     * Reverse linked list for every group of k. If the last group is < k, just append
     *
     * @return the processed list
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode base = new ListNode(0);
        base.next = head;
        for (ListNode parent = base, tail = base; ; tail = parent = reverse(parent, tail)) {
            for (int i = 0; i < k; ++i) {
                if (tail.next == null) {
                    return base.next;
                }
                tail = tail.next;
            }
        }
    }

    /**
     * reverse the section in a linked list starting at parent (exclusive), ending at tail (inclusive)
     * Return the new tail of this section (which should be parent.next)
     */
    ListNode reverse(final ListNode parent, final ListNode tail) {
        ListNode newTail = parent.next, head = tail.next, node = parent.next;
        for (; ; ) {
            if (node == tail) {
                node.next = head; //duplicate code
                parent.next = node;
                return newTail;
            }
            ListNode next = node.next;
            node.next = head; //duplicate code
            head = node;
            node = next;
        }
    }

    //Bug: for 2 --> 1 --> null and x = 2, the previous code results in various problems such as null pointer exception, or incorrect answer
    //fix: figure out curr is not always incremented. Two special cases:
    //case 1, when the curr.next node is < x and swapped, curr should not increment
    //case 2, when when less and curr points to same node, incrementing both less and curr is sufficient
    public ListNode partition(ListNode head, int x) {
        ListNode base = new ListNode(0);
        base.next = head;
        for (ListNode less = base, curr = base; curr.next != null; ) {
            //less points to tail of all elements with val < x
            if (curr.next.val >= x) {
                curr = curr.next;
            } else if (less == curr) {
                curr = curr.next;
                less = less.next;
            } else {
                //remove curr.next from its original position
                ListNode tmp = curr.next;
                curr.next = tmp.next;

                //append it to the tail of less
                tmp.next = less.next;
                less.next = tmp;

                //update tail less
                less = less.next;
            }
        }
        return base.next;
    }

    /**
     * Keep one node for each distinct value and delete the extra
     *
     * @param head
     * @return
     */
    public ListNode deleteRedundant(ListNode head) {
        if (head == null) {
            return head;
        }
        for (ListNode b = head, f = head.next; ; f = f.next) {
            if (f == null) {
                b.next = null;
                return head;
            }
            if (b.val != f.val) {
                b = b.next;
                b.val = f.val;
            }
        }
    }

    /**
     * Delete all nodes whose value occurred more than once
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode base = new ListNode(0);
        base.next = head;
        for (ListNode b = base, s = null, f = head; ; f = f.next) {
            if (f == null) {
                b.next = null;
                return base.next;
            }
            //if current node is distinct by comparing with  both the previous and next value
            if ((s == null || s.val != f.val) && (f.next == null || f.val != f.next.val)) {
                b = b.next;
                b.val = f.val;
            }
            s = f;
        }
    }

    public ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode n1 = head, n2 = head;
        for (int i = 0; ; n1 = n1.next) {
            ++i;
            if (n1.next == null && i < n) {
                n %= i;
                i = 0;
                n1 = head;
            } else if (n1.next == null) {
                break;
            } else if (i >= n) {
                n2 = n2.next;
            }
        }
        //n2 points to the parent of the new head, n1 points to tail-node
        //invariant: n2 <= n1
        //not needed statement: if(n1 == n2) return head;
        n1.next = head;
        ListNode newHead = n2.next;
        n2.next = null;
        return newHead;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (n < 1) {
            return head;
        }
        ListNode tail = head;
        for (int i = 0; i < n; ++i) {
            if (tail == null) {
                //case n > length
                return head;
            }
            tail = tail.next;
        }
        //case n == length
        if (tail == null) {
            ListNode result = head.next;
            head.next = null;
            return result;
        }
        //case n < length
        ListNode parent = head;
        while (tail.next != null) {
            tail = tail.next;
            parent = parent.next;
        }
        ListNode tmp = parent.next;
        parent.next = tmp.next;
        tmp.next = null;
        return head;
    }

    public ListNode swapPairs(ListNode head) {
        ListNode result = new ListNode(0);
        result.next = head;
        //parent --> current --> next --> sublist
        ListNode parent = result, current = result.next;
        while (true) {
            //it won't work like this next = current.next; if(current == null || next == null)
            //but it works like this: if(current == null || current.next == null)
            if (current == null || current.next == null) {
                return result.next;
            }
            //only now can we safely talk about next
            ListNode next = current.next, sublist = next.next;
            //update the links in this order: p -1-> n -2-> c -3-> sublist
            parent.next = next;
            next.next = current;
            current.next = sublist;

            //step forward.
            //bug: if you do parent = next, you step to the original 'next' which is wrong!!
            //the safe, less confusing way to step forward is
            parent = parent.next.next;
            current = sublist;
        }
    }

    /**
     * Bug: be careful with the use of for loop:
     * often the increment/decrement occurs under certain condition
     * under such circumstance, they cannot be put in the 3rd place of for(;;)
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(-1);
        ListNode current = result;
        for (int carry = 0; carry != 0 || l1 != null || l2 != null; ) {
            ListNode d = new ListNode(carry);
            if (l1 != null) {
                d.val += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                d.val += l2.val;
                l2 = l2.next;
            }
            carry = d.val / 10;
            d.val %= 10;
            current.next = d;
            current = current.next;
        }
        return result.next;
    }
}
