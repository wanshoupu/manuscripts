int* part(int* s, int*e) {
    int* const pivot = s++;
    --e;$\label{quicksort:pre-increment}$
    while(true) {
         if (s < e && *s < *pivot) {  // find the next element >= pivot
             ++s;
         } else if (pivot < e && *e > *pivot) { // find the prev element <= pivot
             --e;
         } else if (s < e) {
             swap(s++, e--); //swap the out-of-place elements
         } else {
             swap(pivot, e);
             return e;
         }
    }
}
