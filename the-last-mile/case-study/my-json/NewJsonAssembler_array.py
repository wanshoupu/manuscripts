def assembleArray(tokens):
    result = []
    while tokens:
        token = tokens[0]
        if token == ']':
            tokens.popleft()
            return result
        elif token == ',':
            tokens.popleft()
        else:
            result.append(assembleDispatcher(tokens))
    return result
