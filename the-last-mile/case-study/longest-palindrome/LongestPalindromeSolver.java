package org.shoupu.string;

public class LongestPalindromeSolver {

    private String input;
    // Specifications of palindromic substrings in the input:
    // The i-th element in array 'lsp' records the max length of palindrome centered
    // on a char or between two adjacent chars of the input string depends on the parity of i:
    // if i is even, it is centered between the (i/2)th char and the (i/2 + 1)th char;
    // if i is odd, it is centered on the (i-1)/2th char in string input
    // lps[0] = 0 by definition
    private int[] lps;

    /*
     * Find the (first) longest palindromic substring in input string
     */
    public String longestPalindrome(String input) {
        this.input = input;
        lps = new int[input.length() * 2 + 1];
        solve();
        return substring(argmax());
    }

    /*
     * Solve the longest palindrome problem and cache the result
     */
    void solve() {
        for (int i = 1, rm = 0; i < lps.length; ++i) {
            // rm is the index of palindrome that extends to the rightmost
            if (getRightBound(rm) <= i) {
                lps[i] = palength(i, i);
                rm = i; // i becomes the rightmost palindrome
                continue;
            }
            int mi = toMirrorImage(rm, i);
            //assert mi >= 0
            if (getLeftBound(mi) > getLeftBound(rm)) {
                // if the reflection palindrome is covered entirely by that at rm
                // then no calculation is needed
                lps[i] = lps[mi];
            } else {
                //calculate the part outside of the right bound of the palindrome at rm
                lps[i] = palength(i, getRightBound(rm));
                rm = i;
            }
        }
    }

    /*
     * Find the index of maximum palindromic substring
     */
    int argmax() {
        int maxi = 0;
        for (int i = 0; i < lps.length; ++i) {
            if (lps[i] > lps[maxi]) {
                maxi = i;
            }
        }
        return maxi;
    }

    int getLeftBound(int i) {
        return i - lps[i];
    }

    int getRightBound(int i) {
        return i + lps[i];
    }

    String substring(int center) {
        //if center is even, length of the palindrome can only be even
        //if center is odd, length of the palindrome can only be odd
        int left = getLeftBound(center) / 2;
        int right = getRightBound(center) / 2;
        return input.substring(left, right);
    }

    /*
     * Find the palindrome in string input centered at center.
     * Note that the portion between center and index has already been proven palindromic
     * so this function will skip checking that part
     */
    int palength(int center, int index) {
        for (int mi = toMirrorImage(center, index); ; ++index, --mi) {
            // if one of the conditions:
            // reaching the left end of input string
            // reaching the right end of input string
            // encountered char mismatch
            if (mi < 0 || index >= lps.length || isMismatch(index, mi)) {
                return index - center - 1;
            }
        }
    }

    /*
     * Determine if there is a mismatch between virtual char at i and j
     * If i is even (so is j), there is no mismatch as virtual chars are always a match
     * @return true if mismatch false otherwise
     */
    boolean isMismatch(int i, int j) {
        return (i & 1) == 1 && input.charAt(i / 2) != input.charAt(j / 2);
    }

    int toMirrorImage(int axis, int x) {
        return 2 * axis - x;
    }
}
