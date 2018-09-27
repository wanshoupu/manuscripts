class LinkedList(object):
    def __init__(self, val=0):
        self.val = val
        self.next = None


def reverse_linked_list(ll):
    anchor = LinkedList()
    while ll:
        next = ll.next
        ll.next = anchor.next
        anchor.next = ll
        ll = next
    result = anchor.next
    anchor.next = None
    return result
