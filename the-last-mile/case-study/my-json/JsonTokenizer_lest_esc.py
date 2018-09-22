def tokenize(string):
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
        if quoted:  # TODO need to simplify the logic
            # don't be tempted to combine this two if's together.
            if string[indx] == quoted:
                quoted = None
                result.append('"' + string[start:indx] + '"')
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
    if not seg:
        return

    if __validate_val__(seg):
        result.append(seg)
    else:
        raise ValueError('Invalid JSON value {}'.format(seg))

def __validate_num__(seg):
    seg = seg.lower()
    try:
        float(seg)
    except:  # catch all exception
        return False
    return True


def __validate_val__(seg):
    if seg == 'null' or seg == 'true' or seg == 'false':
        return True
    return __validate_num__(seg)

if __name__ == '__main__':
    test = '{"1":"\\"1:{1:1}","2":2}'
    print tokenize(test)
