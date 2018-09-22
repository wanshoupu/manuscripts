def assembleList(tokens):
    result = []
    while tokens:
        token = tokens.popleft()
        if token == ',':
            continue
        elif token == ']':
            return result
        else:
            result.append(assembleDispatcher(tokens))
    return result
