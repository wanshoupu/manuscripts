def assembleDispatcher(tokens):
    token = tokens.popleft()
    if token == '{':
        return assembleStruct(tokens)
    if token == '[':
        return assembleList(tokens)
    return token
