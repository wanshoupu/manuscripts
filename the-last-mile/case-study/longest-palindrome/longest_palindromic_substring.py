import numpy as np

class Solution(object):
    def longestPalindrome(self, s):
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
