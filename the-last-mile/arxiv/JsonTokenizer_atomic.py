from collections import deque


def tokenizeStr(chars):
    cache = []
    while chars:
        char = chars.popleft()
        cache.append(char)
        if char == '\\':
            cache.append(chars.popleft())
        elif char == '"':
            return '"' + ''.join(cache)
    raise ValueError('String not properly terminated')


def __tokenize__(chars):
    tokens = []
    cache = []
    while chars:
        char = chars.popleft()
        if char == '"':
            tokens.append(tokenizeStr(chars))
        elif char in PUNCTUATIONS:
            attemptToParseValue(''.join(cache), tokens)
            cache = []  # reset cache
            tokens.append(char)
        else:
            cache.append(char)
    return tokens


def tokenize(string):
    chars = deque(string + EOS)
    return __tokenize__(chars)[:-1]
