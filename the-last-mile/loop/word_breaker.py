class Solution(object):
    def wordBreak(self, s, wordDict):
        """
        :type s: str
        :type wordDict: List[str]
        :rtype: bool
        """
        dictionary = set(wordDict)
        feasible = [0]  # length of prefixes that are breakable by words in dictionary
        for i in range(1, len(s) + 1):
            f = any(s[j:i] in dictionary for j in feasible)
            if f:
                feasible.append(i)
        return feasible[-1] == len(s)


if __name__ == '__main__':
    sol = Solution()

    p = "catsandog"
    str = ["cats", "dog", "sand", "and", "cat"]
    print (sol.wordBreak(p, str))

    p = "applepenapple"
    str = ["apple", "pen"]
    print (sol.wordBreak(p, str))

    p = "pineapplepenapple"
    str = ["apple", "pen", "applepen", "pine", "pineapple"]
    print sol.wordBreak(p, str)
