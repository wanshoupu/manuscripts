def assembleDispatcher(tokens):
    token = tokens.popleft()
    if token == '{':
        return assembleObject(tokens)
    if token == '[':
        return assembleArray(tokens)
    return token


def assembleObject(tokens):
    """
    A stub for assembling objects
    :param tokens:
    :return:
    """
    pass


def assembleArray(tokens):
    """
    A stub for assembling arrays
    :param tokens:
    :return:
    """
    pass
