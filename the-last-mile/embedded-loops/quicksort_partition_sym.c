int* part(int* s, int*e) {
    swap(s, s + rand() % (e - s));
    int* const pivot = s;
    while(true) {
         while(s < e && *++s < *pivot) {  // find the next element >= pivot
             ++s;
         }
         while(pivot < e && *--e > *pivot); // find the prev element <= pivot
         if (s < e) {
             swap(s, e); //swap the out-of-place elements
         } else {
             swap(pivot, e);
             return e;
         }
    }
}
