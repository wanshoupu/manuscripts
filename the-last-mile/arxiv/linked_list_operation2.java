for(ListNode curr = base;
        head1 != tail1 || head2 != tail2
        ; curr = curr.next){
    if(head2 == tail2 || head1.val < head2.val){
        curr.next = head1;
        head1 = head1.next;
    }
    curr.next = head2;
    head2 = head2.next;
}
// The trick is to analyze the condition for
// head1 increment and head2 increment.
// Not the other way around, i.e., analyze
// whether head1 is null or head2 is null.
