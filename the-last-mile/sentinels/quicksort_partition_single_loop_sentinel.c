int* part(int* s, int*e) {
    init_swap(s, e);
    int* const pivot = s++;
    --e;
    while(true) {
         if (*s < *pivot) {  // find the next element >= pivot
             ++s;
         } else if (*e > *pivot) { // find the prev element <= pivot
             --e;
         } else if (s < e) {
             swap(s++, e--); //swap the out-of-place elements
         } else {
             swap(pivot, e);
             return e;
         }
    }
}
