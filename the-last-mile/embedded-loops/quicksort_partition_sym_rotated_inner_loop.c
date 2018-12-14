int* part(int* s, int*e) {
    swap(s, s + rand() % (e - s));
    int* const pivot = s;
    ++s;
    --e;
    while(true) {
        while(s < e && *s < *pivot) {
            ++s;
        }
        while(pivot < e && *e > *pivot) {
            --e;
        }
        if (s < e) {
            swap(s, e); //swap the out-of-place elements
            ++s;
            --e;
        } else {
            swap(pivot, e);
            return e;
        }
    }
}
