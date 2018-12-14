int search(String haystack, String needle) {
    int i = 0, j = 0;
    while (j < needle.length() && i + needle.length() < haystack.length()) {
        if (needle.charAt(j) == haystack.charAt(i + j)) {
            ++j;
        } else {
            j = 0;
            ++i;
        }
    }
    return j == needle.length() ? i : -1;
}
