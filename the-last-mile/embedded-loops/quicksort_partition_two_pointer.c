int* part(int* s, int*e) {
    int* const pivot = s;
    swap(s, s + rand() % (e - s));
    while(true) {
         // the left partition is strictly less than the pivot
         while(s < e && *++s < *pivot); // find the next element >= pivot
         // the right partition is  greater than or equal to the pivot
         while(s < e && *--e >= *pivot); // find the prev element < pivot
         if (e <= s) {
             swap(pivot, s - 1); // insert the pivot element
             return s - 1; // shortening the left partition by shrink back the pivot element
         }
         swap(s, e); //swap the out-of-place elements
    }
}
