int* part_nested_loop_var3(int* s, int* e) {
    int* const pivot = s;
    while (true) {
        while (s < e && *pivot <= *--e);
        while (s < e && *++s <= *pivot);
        if (s < e) {
            swap(s, e);
        } else {
            swap(pivot, s);
            return s;
        }
    }
}
