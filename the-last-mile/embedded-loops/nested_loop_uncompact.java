for (int i = 0; i + pattern.length() <= text.length(); ++i) {
    for (int j = 0; ; ++j) {
        if (j == pattern.length()) {
            return i;
        }
        if (pattern.charAt(j) != text.charAt(i + j)) {
            break;
        }
    }
}
return -1;
