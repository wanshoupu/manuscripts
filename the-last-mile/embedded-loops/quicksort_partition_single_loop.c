int* part(int* s, int*e) {
    int* const pivot = s;
    swap(s, s + rand() % (e - s));
    while(true) {
        if(s < e && *s < *pivot) {  // find the next element >= pivot
            ++s;
        } else if(s > e || *--e <= *pivot) { // find the prev element <= pivot
            if (e > s) {
                swap(s, e); //swap the out-of-place elements
            } else {
                return e;
            }
        }
    }
}
