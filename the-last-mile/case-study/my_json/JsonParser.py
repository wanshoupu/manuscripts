"""
Limitations:
- escape chars are not processed properly. For example "\"" will be treated as unmatched quotes and throw exception

Input chars may contains
whitespace
newline
"
'
[]
{}
:
,
true
false
null
string
int
float
"""


def unescape(string):
    escapse_seqs = {
        '\\n': '',
        '\\"': '\'',
        '\n': ''
    }
    for e, u in escapse_seqs.items():
        string = string.replace(e, u)
    return string


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
    from collections import OrderedDict
    result = []
    vals += [',']
    for i in range(len(vals)):
        if vals[i] == ',':
            result.append((vals[i - 3], vals[i - 1]))
    return dict(result)


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
    if len(val_stack) != 1:
        raise ValueError('Too many or too few root node: {}'.format(len(val_stack)))
    return val_stack[0]


def __tokenize__(string):
    """
    Take care of " and ' with priority
    :param string: 
    :return: 
    """
    result = []
    start = 0
    quoted = None
    for indx in range(len(string)):
        if quoted:
            if string[indx] == quoted:
                quoted = None
                result.append(string[start:indx])
                start = indx + 1
        elif string[indx] == '"' or string[indx] == "'":
            quoted = string[indx]
            seg = string[start:indx].strip()
            if seg:
                result.append(__parse_val__(seg))
            start = indx + 1
        elif string[indx] == '{' or string[indx] == '}' \
                or string[indx] == '[' or string[indx] == ']' \
                or string[indx] == ',' or string[indx] == ':':
            seg = string[start:indx].strip()
            if seg:
                result.append(__parse_val__(seg))
            result.append(string[indx])
            start = indx + 1
    return result


############### unit tests ####################


if __name__ == "__main__":
    tests = [
        '"abc"',
        '{"a":-1.3}',
        '{"a":-13}',
        '{"a":-1e-13}',
        '{"a":-1.0e-13}',
        '[1,2]',
        '{1:2,3:4}',
        '"\'"',
        "{'1':'\"1:{1:1}','2':2}",
        '{1:2,3:{"5":5,"6":6}}',
    ]
    for test in tests:
        json = parseJson(test)
        print json
        print '============'
