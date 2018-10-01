class Solution(object):
    def validWordAbbreviation(self, word, abbr):
        """
        :type word: str
        :type abbr: str
        :rtype: bool
        """
        import re
        indx = 0
        for itm in re.findall(r'\d+|\D+', abbr):
            if itm[0].isalpha():
                if word[indx:indx + len(itm)] != itm:
                    return False
                indx += len(itm)
            elif itm[0] == '0':
                return False
            else:
                indx += int(itm)
        return indx == len(word)
