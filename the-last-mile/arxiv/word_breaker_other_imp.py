class Solution(object):
    def wordBreak(self, s, wordDict):
        dp = [False for i in range(len(s))]
        for i in range(len(s)):
            if i - 1 < 0 or dp[i - 1] == True:
                for w in wordDict:
                    if s[i] == w[0] and i + len(w) <= len(s) and s[i:i + len(w)] == w:
                        dp[i + len(w) - 1] = True
        return dp[-1]


class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """

        d = [False] * len(s)

        for i in range(len(s)):
            d[i] = False
            for word in wordDict:
                if i + 1 >= len(word) and word == s[i - len(word) + 1:i + 1] and (
                        i - len(word) < 0 or d[i - len(word)]):
                    d[i] = True
                    break
        return d[-1]

        d[s] = False
        for word in wordDict:
            if startsWith(s, word):
                if self.wordBreak(s[len(word):], wordDict):
                    d[s] = True
                    break

        return d[s]


class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        n = len(s)
        dp = [False for i in range(n + 1)]
        dp[0] = True
        for i in range(1, n + 1):
            for w in wordDict:
                if dp[i - len(w)] and s[i - len(w):i] == w:
                    dp[i] = True
        return dp[-1]


class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        can_break = [False for i in xrange(len(s) + 1)]
        can_break[0] = True

        #         # Solution 1: (Improve from answer) Dynamic Programming: use can_break[] to store whether can break the word
        #         # For each character in s, check word in wordDict, O(n * m), n: len(s), m: len(wordDict)
        #         for i in xrange(len(s)):
        #             if can_break[i]:
        #                 for word in wordDict:
        #                     if s[i:].startswith(word):
        #                         can_break[i + len(word)] = True
        #         return can_break[len(s)]

        # Solution 2: (Look at answer), for each characater in s, check whether it's the ending of word in wordDict
        # O(n * n)
        for i in xrange(len(s)):
            for j in xrange(i + 1):
                if can_break[j] and s[j:(i + 1)] in wordDict:
                    can_break[i + 1] = True
        return can_break[len(s)]


class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if not len(s): return False
        canBreak = [False for i in range(len(s))]
        canBreak[0] = s[0] in wordDict
        for i in range(1, len(s)):
            if s[:i + 1] in wordDict:
                canBreak[i] = True
                continue
            for j in range(i):
                if canBreak[j] and s[j + 1:i + 1] in wordDict:
                    canBreak[i] = True
                    break
        return canBreak[-1]


class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        if len(s) == 0:
            return True
        if not wordDict:
            return False

        dp = [False] * (len(s) + 1)
        wordDict = set(wordDict)
        for i in range(len(s) + 1):
            for j in range(i):
                s1 = s[:j + 1]
                s2 = s[j + 1:i + 1]
                # print s1,s2
                if s1 in wordDict or s1 == '':
                    dp[j] = True
                if dp[j] and (s2 in wordDict or s2 == ''):
                    dp[i] = True
        return dp[-1]

        queue = [0]
        visited = [0]
        while queue:
            curr_idx = queue.pop(0)
            for i in range(curr_idx, len(s) + 1):
                if (i not in visited) and (s[curr_idx:i] in wordDict):
                    if i == len(s):
                        return True
                    queue.append(i)
                    visited.append(i)
        return False
