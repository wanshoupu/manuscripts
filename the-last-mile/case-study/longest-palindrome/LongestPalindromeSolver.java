package org.shoupu.string;

public class LongestPalindromeSolver {

    private String input;
    private int[] lengths;

    /**
     * Algorithm:
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        this.input = s;
        // Each of the palindrome substrings in s is completely specified by the index and the
        // element in lengths as follows (zero-based index should be understood):
        //
        // The i-th element in array `lengths' records the max length of palindrome centered
        // on a char or centered between two neighboring chars in string input depends on the parity of i:
        // if i is even, it is centered between the (i/2)th char and the (i/2 + 1)th char in string input;
        // if i is odd, it is centered on the (i-1)/2th char in string input
        // lengths[0] = 0 by definition
        lengths = new int[s.length() * 2 + 1];
        solve();
        return constructResult();
    }

    /**
     * Solve the longest palindrome problem and store the result in field lengths
     */
    private void solve() {
        for (int i = 1, irightmost = 0; i < lengths.length; ++i) {
            // irightmost is the index of palindrome that extends to the rightmost
            if (getRightBound(irightmost) <= i) {
                lengths[i] = palength(i, i);
                irightmost = i; // i becomes the rightmost palindrome
                continue;
            }
            int mi = toMirrorImage(irightmost, i);
            //assert mi >= 0
            if (getLeftBound(mi) > getLeftBound(irightmost)) {
                // if the reflection palindrome is covered entirely by that at irightmost
                // then no calculation is needed
                lengths[i] = lengths[mi];
            } else {
                //calculate the part outside of the right bound of the palindrome at irightmost
                lengths[i] = palength(i, getRightBound(irightmost));
                irightmost = i;
            }
        }
    }

    /**
     * Find the maximum palindromic substring based on array {@link #lengths}
     *
     * @return
     */
    private String constructResult() {
        int maxi = 0;
        for (int i = 0; i < lengths.length; ++i) {
            if (lengths[i] > lengths[maxi]) {
                maxi = i;
            }
        }
        return substring(maxi);
    }

    private int getLeftBound(int i) {
        return i - lengths[i];
    }

    private int getRightBound(int i) {
        return i + lengths[i];
    }

    String substring(int center) {
        //if center is even, length of the palindrome can only be even
        //if center is odd, length of the palindrome can only be odd
        int left = getLeftBound(center) / 2;
        int right = getRightBound(center) / 2;
        return input.substring(left, right);
    }

    /**
     * Find the palindrome in string input centered at center.
     * Note that the portion between center and index has already been proven palindromic
     * so this function will skip checking that part
     *
     * @param center
     * @param index
     * @return
     */
    private int palength(int center, int index) {
        for (int mi = toMirrorImage(center, index); ; ++index, --mi) {
            // if one of the conditions:
            // reached the left end of input string
            // reached the right end of input string
            // encountered char mismatch
            if (mi < 0 || index >= lengths.length || isMismatch(index, mi)) {
                return index - center - 1;
            }
        }
    }

    /**
     * Determine if there is a mismatch between virtual char at i and j
     * If i is even (so is j), there is no mismatch as virtual chars are always a match
     *
     * @param i
     * @param j
     * @return
     */
    private boolean isMismatch(int i, int j) {
        return (i & 1) == 1 && input.charAt(i / 2) != input.charAt(j / 2);
    }

    private static int toMirrorImage(int axis, int x) {
        return 2 * axis - x;
    }

}
