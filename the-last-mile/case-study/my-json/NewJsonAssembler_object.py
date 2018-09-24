from collections import OrderedDict


def assembleObject(tokens):
    result = OrderedDict()
    while tokens:
        token = tokens.popleft()
        if token == ',':
            continue
        elif token == '}':
            return result
        else:
            assert tokens.popleft() == ':'
            result[token] = assembleDispatcher(tokens)
    return result
