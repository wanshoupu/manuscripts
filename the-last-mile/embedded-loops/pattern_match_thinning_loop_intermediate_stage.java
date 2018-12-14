int search(String haystack, String needle) {
    int i = 0, j = 0;
    while (i + needle.length() <= haystack.length()) {
        while (true) {
            if (j == needle.length()) {
                return i;
            }
            if (needle.charAt(j) != haystack.charAt(i + j)) {
                j = 0;
                break;
            }
            ++j;
        }
        ++i;
    }
    return -1;
}
