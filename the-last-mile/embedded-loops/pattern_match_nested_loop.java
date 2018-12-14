int search(String haystack, String needle) {
    for (int i = 0; i <= haystack.length(); ++i) {
        int j = 0;
        while (j < needle.length()) {
            if (i + j == haystack.length() || needle.charAt(j) != haystack.charAt(i + j)) {
                break;
            }
            ++j;
        }
        if (j == needle.length()) {
            return i;
        }

    }
    return -1;
}
