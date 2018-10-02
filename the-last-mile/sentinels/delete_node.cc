void delete(NODE** header) {
    if (!*header) return;
    NODE* next = *header->next;
    *header->next = NULL;
    *header = next;
}
