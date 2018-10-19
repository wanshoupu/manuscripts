for (int i = 0, j = 0; ; ) {
    //The order of the following two if...return blocks MATTERS!
    if (j == pattern.length()) {
        return i;
    }
    if (i + j == text.length()) {
        return -1;
    }
    if (pattern.charAt(j) == text.charAt(i + j)) {
        ++j;
    } else {
        ++i;
        j = 0;
    }
}
