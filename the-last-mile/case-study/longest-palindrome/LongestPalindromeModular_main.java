package org.shoupu.string;

public class LongestPalindromeSolver {

    private String input;
    // The i-th element in array 'lsp' records the max length of palindrome centered
    // on a char or between two adjacent chars of the input string depends on the parity of i:
    // if i is even, it is centered between the (i/2)th char and the (i/2 + 1)th char;
    // if i is odd, it is centered on the (i-1)/2th char in string input
    // pss[0] = 0 by definition
    private int[] pss;

    public String longestPalindrome(String input) {
        this.input = input;
        pss = new int[input.length() * 2 + 1];
        solve();
        return substring(argmax());
    }
}
