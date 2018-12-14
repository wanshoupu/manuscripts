int search(String haystack, String needle) {
        int i = 0, j = 0;
        while (i + needle.length() <= haystack.length()) {
            if (j == needle.length()) {
                return i;
            }
            if (needle.charAt(j) != haystack.charAt(i + j)) {
                j = 0;
                ++i;
            } else
                ++j;
        }
        return -1;
}
