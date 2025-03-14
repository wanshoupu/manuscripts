from collections import OrderedDict


def assembleObject(tokens):
    result = OrderedDict()
    while tokens:
        token = tokens[0]
        if token == ',':
            tokens.popleft()
        elif token == '}':
            tokens.popleft()
            return result
        else:
            token = tokens.popleft()
            assert tokens.popleft() == ':', len(tokens)
            result[token] = assembleDispatcher(tokens)
    return result
