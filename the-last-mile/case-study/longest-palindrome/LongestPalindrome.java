package org.shoupu.string;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        int[] length = new int[s.length() * 2 + 1];
        //length records the max-length of the palindrome centered at the ith position:
        //if i is even it's between two chars in the string
        //else if i is odd, it's one char in the string
        //length[0] = 0 by definition
        for (int i = 1, maxi = 0; i < length.length; ++i) {
            if (maxi + length[maxi] <= i) {
                length[i] = palength(s, i, i);
                maxi = i;
            } else {
                int im = maxi * 2 - i;
                //assert im >= 0
                if (im - length[im] > maxi - length[maxi]) {
                    length[i] = length[im];
                } else {
                    length[i] = palength(s, i, maxi + length[maxi]);
                    maxi = i;
                }
            }

        }

        for (int i = 0, maxi = 0; ; ++i) {
            if (i == length.length) {
                return substring(s, maxi, length[maxi]);
            }
            if (length[i] > length[maxi]) {
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

}
