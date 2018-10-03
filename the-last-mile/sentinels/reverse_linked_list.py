def reverse_linked_list(ll):
    head = None
    while ll:
        next = ll.next
        ll.next = head if head else None
        head = ll
        ll = next
    return head
