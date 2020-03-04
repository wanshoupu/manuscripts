public String longestPalindrome(String input) {
    final int n = input.length() * 2 + 1;
    char[] aug = new char[n];
    for (int i = 0; i < n; ++i) {
        aug[i] = (i & 1) == 0 ? 0 : input.charAt(i / 2);
    }
    int maxStart = 0, maxEnd = 0;
    int[] lps = new int[n];
    lps[0] = 1;
    for (int center = 0, j = 1; j < n; ++j) {
        int wing = lps[center] / 2;
        if (j < center + wing) {
            int jmirror = 2 * center - j;
            int jwing = lps[jmirror] / 2;
            if (jmirror - jwing > center - wing) {
                lps[j] = lps[jmirror];
                continue;
            }
        }
        //get the length of the LPS centered on j
        for (int i = center + wing + 1; ; i++) {
            if (2 * j - i < 0 || i == n || aug[2 * j - i] != aug[i]) {
                lps[j] = 2 * (i - 1 - j) + 1;
                break;
            }
        }
        center = j;
        if (maxEnd - maxStart < lps[center] / 2) {
            maxStart = (center + 1 - lps[center] / 2) / 2;
            maxEnd = (center + 1 + lps[center] / 2) / 2;
        }
    } //end for
    return input.substring(maxStart, maxEnd);
}
