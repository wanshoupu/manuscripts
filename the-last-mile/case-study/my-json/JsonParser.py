from collections import OrderedDict


def parseJson(string):
    """
    input is my_json represented as string
    Output may be of type: string, number, False, True, list, dictionary, or None.
    When it's a dictionary, its keys are of type string and values can be 
    :param string:
    :return: obj representation of my_json data
    """
    preproc = __tokenize__(string)
    return __structurize__(preproc)


OPENING_BRACKETS = {'{': '}', '[': ']'}
CLOSING_BRACKETS = {'}': '{', ']': '['}


def __parse_num__(seg):
    seg = seg.lower()
    if seg.find('.') >= 0 or seg.find('e') >= 0:
        return float(seg)
    return int(seg)


def __parse_val__(seg):
    if seg == 'null':
        return None
    elif seg == 'true':
        return True
    elif seg == 'false':
        return False
    return __parse_num__(seg)


def __parse_list__(vals):
    result = []
    for v in vals:
        if v != ',':
            result.append(v)
    return result


def __parse_struct__(vals):
    """
    Limitation: order of the dictionary is lost during processing
    :param vals: 
    :return: 
    """
    if not vals:
        return OrderedDict()
    result = []
    # add sentinel
    vals += [',']
    for i in range(len(vals)):
        if vals[i] == ',':
            result.append((vals[i - 3], vals[i - 1]))
    return OrderedDict(result)


def __structurize__(tokens):
    """
    Break down into array and/or dictionary
    Takes care of [ ] { }
    :param tokens:
    :return:
    """
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


def __tokenize__(string):
    """
    Take care of quote with priority
    Single quote is regular char and has no special meaning.
    Single quote cannot be used as string quote in JSON
    :param string: 
    :return: 
    """
    result = []
    start, indx = 0, 0
    quoted = None
    while indx < len(string):
        if string[indx] == '\\':
            indx += 2
            continue
        if quoted:  # TODO need to simplify the logic
            # don't be tempted to combine this two if's together.
            if string[indx] == quoted:
                quoted = None
                result.append(string[start:indx])
                start = indx + 1
        elif string[indx] == '"':
            quoted = string[indx]
            attempt_parse_value(string[start:indx], result)
            start = indx + 1
        elif string[indx] == '{' or string[indx] == '}' \
                or string[indx] == '[' or string[indx] == ']' \
                or string[indx] == ',' or string[indx] == ':':
            attempt_parse_value(string[start:indx], result)
            result.append(string[indx])
            start = indx + 1
        indx += 1
    # loop and a half problem below
    attempt_parse_value(string[start:indx], result)
    return result


def attempt_parse_value(seg, result):
    seg = seg.strip()
    if seg:
        result.append(__parse_val__(seg))
