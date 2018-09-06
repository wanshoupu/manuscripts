from collections import OrderedDict


def __parse_list__(string):
    result = []
    for v in string:
        if v != ',':
            result.append(v)
    return result


def __parse_struct__(string):
    """
    Limitation: order of the dictionary is lost during processing
    :param string:
    :return:
    """
    if not string:
        return OrderedDict()
    result = []
    # add sentinel
    string += [',']
    for i in range(len(string)):
        if string[i] == ',':
            result.append((string[i - 3], string[i - 1]))
    return OrderedDict(result)


OPENING_BRACKETS = {'{': '}', '[': ']'}
CLOSING_BRACKETS = {'}': '{', ']': '['}


def __assemble__(tokens):
    struct_stack = []
    val_stack = []
    for indx in range(len(tokens)):
        if tokens[indx] in OPENING_BRACKETS:
            struct_stack.append((len(val_stack), indx))
        elif tokens[indx] in CLOSING_BRACKETS:
            val_stack_start, tokens_start = struct_stack.pop()
            if OPENING_BRACKETS[tokens[tokens_start]] != tokens[indx]:
                raise ValueError(
                    'Unmatched bracket: {} and {}'.format(tokens[tokens_start], tokens[indx]))
            val_stack[val_stack_start:] = [
                __parse_list__(val_stack[val_stack_start:]) if tokens[indx] == ']'
                else __parse_struct__(val_stack[val_stack_start:])]
        else:
            val_stack.append(tokens[indx])
    if len(val_stack) > 1:
        raise ValueError('Too many or too few root node: {}'.format(len(val_stack)))
    return val_stack[0] if val_stack else None
