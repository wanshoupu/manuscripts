int* part_nested_loop_var2(int* s, int* e) {
    int* const pivot = s;
    while (true) {
        while (s < e && *++s <= *pivot);
        while (s < e && *pivot <= *--e);
        if (s < e) {
            swap(s, e);
        } else {
            swap(pivot, s - 1);
            return s - 1;
        }
    }
}
