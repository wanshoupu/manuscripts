from collections import deque


def assemble(tokens):
    if not tokens:
        raise ValueError('Empty token list')
    tokens = deque(tokens)
    return assembleDispatcher(tokens)
