int search(String haystack, String needle) {
    int i = 0, j = 0;
    while (i + needle.length() <= haystack.length()) {
        while (true) {~\label{line:haystack-needle}~
            if (j == needle.length()) {
                return i;
            }
            if (needle.charAt(j) != haystack.charAt(i + j)) {
                break;
            }
            ++j;
        }
        ++i;
        j = 0;
    }
    return -1;
}
