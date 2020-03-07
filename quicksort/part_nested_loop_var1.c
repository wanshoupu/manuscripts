int* part_nested_loop_var1(int* s, int* e) {
    int* const pivot = s;
    while (true) {
        while (s < e && *++s <= *pivot);
        while (pivot < e && *pivot <= *--e);
        if (s < e) {
            swap(s, e);
        } else {
            swap(pivot, e);
            return e;
        }
    }
}
