int* part(int* s, int*e) {
    int * p = s + rand() % (e - s);
    swap(s, p);
    p = s;
    for(int* i = s; i < e; i++ ) {
        if (*i < *s) {
            swap(++p, i); //swap the out-of-place elements
        }
    }
    swap(s, p);
    return p;
}
