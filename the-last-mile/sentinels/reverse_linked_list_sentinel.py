def reverse_linked_list(ll):
    anchor = Node(None)
    while ll:
        next = ll.next
        ll.next = anchor.next
        anchor.next = ll
        ll = next
    return anchor.next
