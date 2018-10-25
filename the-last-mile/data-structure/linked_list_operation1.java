for(ListNode curr = base; ; curr = curr.next){
    if(head1 == tail1){
        curr.next = head2;
        head2 = head2.next;
    }
    if(head2 == tail2){
        curr.next = head1;
        head1 = head1.next;
    }
    if(head1.val < head2.val){
        curr.next = head1;
        head1 = head1.next;
    }else{
        curr.next = head2;
        head2 = head2.next;
    }
}
