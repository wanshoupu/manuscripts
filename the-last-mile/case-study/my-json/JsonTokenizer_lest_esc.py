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
            attemptToParseValue(string[start:indx], result)
            start = indx + 1
        elif string[indx] == '{' or string[indx] == '}' \
                or string[indx] == '[' or string[indx] == ']' \
                or string[indx] == ',' or string[indx] == ':':
            attemptToParseValue(string[start:indx], result)
            result.append(string[indx])
            start = indx + 1
        indx += 1
    # loop and a half problem below
    attemptToParseValue(string[start:indx], result)
    return result


def attemptToParseValue(seg, result):
    seg = seg.strip()
    if not seg:
        return

    if validateValue(seg):
        result.append(seg)
    else:
        raise ValueError('Invalid JSON value {}'.format(seg))

def validateNumber(seg):
    seg = seg.lower()
    try:
        float(seg)
    except:  # catch all exception
        return False
    return True


def validateValue(seg):
    if seg == 'null' or seg == 'true' or seg == 'false':
        return True
    return validateNumber(seg)

if __name__ == '__main__':
    test = '{"1":"\\"1:{1:1}","2":2}'
    print tokenize(test)
