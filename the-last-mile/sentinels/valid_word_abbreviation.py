class Solution(object):
    def validWordAbbreviation(self, word, abbr):
        """
        :type word: str
        :type abbr: str
        :rtype: bool
        """
        i = 0
        k = 0
        for j, c in enumerate(abbr):
            if c.isalpha():
                if k < j:
                    if abbr[k] == '0':
                        return False
                    i += int(abbr[k:j])
                k = j + 1
                if len(word) <= i or word[i] != c:
                    return False
                i += 1
        if k < len(abbr):
            if abbr[k] == '0':
                return False
            i += int(abbr[k:])
        return i == len(word)
