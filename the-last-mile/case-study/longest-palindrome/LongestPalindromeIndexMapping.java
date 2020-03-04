public String longestPalindrome(String input) {
    int[] lps = new int[input.length() * 2 + 1];
    //lps records the max-length of the palindrome centered at the ith position:
    //if i is even it's between two chars in the string
    //else if i is odd, it's one char in the string
    //lps[0] = 0 by definition
    for (int i = 1, maxi = 0; i < lps.length; ++i) {
        if (maxi + lps[maxi] <= i) {
            lps[i] = palength(input, i, i);
            maxi = i;
        } else {
            int im = maxi * 2 - i;
            //assert im >= 0
            if (im - lps[im] > maxi - lps[maxi]) {
                lps[i] = lps[im];
            } else {
                lps[i] = palength(input, i, maxi + lps[maxi]);
                maxi = i;
            }
        }
    }

    for (int i = 0, maxi = 0; ; ++i) {
        if (i == lps.length) {
            return substring(input, maxi, lps[maxi]);
        }
        if (lps[i] > lps[maxi]) {
            maxi = i;
        }
    }
}

String substring(String str, int c, int len) {
    //if c is even, len can only be even
    //if c is odd, len can only be odd
    int s = (c - len) / 2;
    int e = (c + len) / 2;
    return str.substring(s, e);
}

int palength(String s, int c, int i) {
    final int n = 2 * s.length() + 1;
    for (int mi = 2 * c - i; ; ++i, --mi) {
        if (mi < 0 || i >= n || (i & 1) == 1 && s.charAt(i / 2) != s.charAt(mi / 2)) {
            return i - c - 1;
        }
    }
}
