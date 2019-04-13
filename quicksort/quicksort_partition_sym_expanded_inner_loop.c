int* part(int* s, int*e) {
    int* const pivot = s;
    while(true) {
        ++s;
        --e;
        while(s < e && *s < *pivot) {
            ++s;
        }
        while(pivot < e && *e > *pivot) {
            --e;
        }
        if (s < e) {
            swap(s, e); //swap the out-of-place elements
        } else {
            swap(pivot, e);
            return e;
        }
    }
}
