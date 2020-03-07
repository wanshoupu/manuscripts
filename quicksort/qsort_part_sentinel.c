int* part(int* s, int*e) {
    init_swap(s, e);
    int* const pivot = s;
    while(true) {
        while(*++s < *pivot);   // find the next element >= pivot
        while(*--e > *pivot); // find the prev element <= pivot
        if (s < e) {
            swap(s, e); //swap the out-of-place elements
        } else {
            swap(pivot, e);
            return e;
        }
    }
}
