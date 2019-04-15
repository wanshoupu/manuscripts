int* part_nested_loop_var4(int* s, int* e) {
    int* const pivot = s;
    while (true) {
        while (s < e && *pivot <= *--e);
        while (s < e && *s <= *pivot) ++s;
        if (s < e) {
            swap(s, e);
        } else {
            swap(pivot, s);
            return s;
        }
    }
}
