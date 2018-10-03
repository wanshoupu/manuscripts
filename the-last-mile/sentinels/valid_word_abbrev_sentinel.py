class Solution(object):
    def validWordAbbreviation(self, word, abbr):
        i = 0  # index
        k = 0  # starting index of number
        word = word + 'a'
        abbr = abbr + 'a'
        for j in range(len(abbr)):
            if abbr[j].isalpha():
                if k < j:
                    if abbr[k] == '0':
                        return False
                    i += int(abbr[k:j])
                k = j + 1
                if len(word) <= i or word[i] != abbr[j]:
                    return False
                i += 1
        return i == len(word)
