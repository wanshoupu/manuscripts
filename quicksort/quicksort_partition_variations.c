/*
For elements equal to the pivot, we don't stop
*/
int* part_nested_loop_var1(int* s, int* e) {
    int* const pivot = s;
    while (true) {
        // '<=' or '<' is not critical. But '<=' brings more saving on unnecessary swaps.
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

/**
 *Found another variation of partition scheme based on part_nested_loop
 *Diff wise, it lies between part_nested_loop and part_nested_loop_var
 *- the order of the two while loops is identical to part_nested_loop
 *- the boundary checking is identical to part_nested_loop_var
 *- the return value is shifted toward the left by one unit
 */
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

/**
 *Variation of partition scheme from part_nested_loop.
 *The two differs in three ways
 *- order of the two while loops
 *- increment condition for equal element pointer
 *- return pointer choice between s and e
 */
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

/*
Find yet another variation of implementation where the second nested while-loop
is made post-incremental.
This will remove the difficulty encountered in loop-thinning.
*/
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
