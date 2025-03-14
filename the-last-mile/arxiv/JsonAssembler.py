from collections import OrderedDict


def assemble(tokens):
    compositionStack = []
    valueStack = []
    for token in tokens:
        if token in BRACKET_MAP:
            compositionStack.append((len(valueStack), token))
        elif token in CLOSING_BRACKETS:
            valueStackStart, opening_bracket = compositionStack.pop()
            checkBracket(opening_bracket, token)
            values = valueStack[valueStackStart:]
            del valueStack[valueStackStart:]
            valueStack.append(
                assembleArray(values) if token == ']' else assembleObject(values))
        else:
            valueStack.append(token)
    checkSingleton(valueStack)
    assert 0 == len(compositionStack)
    return valueStack[0]


BRACKET_MAP = {'{': '}', '[': ']'}
CLOSING_BRACKETS = {'}', ']'}


def assembleArray(values):
    result = []
    index = 0
    while index < len(values):
        result.append(values[index])
        assert index + 1 == len(values) or values[index + 1] == ','
        index += 2
    return result


def assembleObject(values):
    """
    To preserve the original order of entries, we use OrderedDict
    :param values:
    :return:
    """
    result = OrderedDict()
    index = 0
    while index < len(values):
        result[values[index]] = values[index + 2]
        assert values[index + 1] == ':'
        assert index + 3 == len(values) or values[index + 3] == ','
        index += 4
    return result


def checkSingleton(valueStack):
    if len(valueStack) > 1:
        raise ValueError('Expect one value on stack but got {}'.format(len(valueStack)))


def checkBracket(openingBracket, closingBracket):
    if BRACKET_MAP[openingBracket] != closingBracket:
        raise ValueError('Unmatched bracket: {} and {}'.format(openingBracket, closingBracket))


if __name__ == '__main__':
    test = ['{', '"1"', ':', '"\\"1:{1:1}"', ',', '"2"', ':', '2', '}']
    print assemble(test)
