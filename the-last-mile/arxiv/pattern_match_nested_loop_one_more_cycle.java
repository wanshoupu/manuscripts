int search(String haystack, String needle) {
    for (int i = 0; i + needle.length() <= haystack.length(); ++i) {
        for (int j = 0; ; ++j) {
            if (j == needle.length()) {
                return i;
            }
            if (needle.charAt(j) != haystack.charAt(i + j)) {
                break;
            }
        }
    }
    return -1;
}
