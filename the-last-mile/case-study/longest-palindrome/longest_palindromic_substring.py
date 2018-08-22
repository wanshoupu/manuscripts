import numpy as np


class Solution(object):
    def longestPalindrome(self, s):
        """
        A O(N) implementation using reflection Manacher's Algorithm
        https://en.wikipedia.org/wiki/Longest_palindromic_substring
        Here is a link for java implementation
        https://algs4.cs.princeton.edu/53substring/Manacher.java.html
        :type s: str
        :rtype: str
        """
        lengths = [0, 1]  # the lengths of the palindromes
        front = 1  # the front of charted area
        for i in range(2, 2 * len(s) + 1):
            if front + lengths[front] <= i:
                lengths.append(self.palength(s, i, i))
                front = i
            else:
                j = 2 * front - i
                assert 0 <= j
                if j - lengths[j] > front - lengths[front]:
                    lengths.append(lengths[j])
                else:
                    lengths.append(self.palength(s, i, front + lengths[front]))
                    front = i
        maxi = np.argmax(lengths)
        return s[(maxi - lengths[maxi]) / 2:(maxi + lengths[maxi]) / 2]

    def palength(self, s, i, j):
        length = 2 * len(s) + 1
        while True:
            mj = 2 * i - j
            if mj < 0 or j >= length or (j & 1) == 1 and s[mj / 2] != s[j / 2]:
                break
            j += 1
        return j - i - 1


if __name__ == '__main__':
    sol = Solution()
    test_str = ['eceba', 'abcbbbbcccbdddadacb', 'abc', 'aaabbbbcde', 'aaabbbbccddeeff']
    for test in test_str:
        palindrome = sol.longestPalindrome(test)
        print test, palindrome
