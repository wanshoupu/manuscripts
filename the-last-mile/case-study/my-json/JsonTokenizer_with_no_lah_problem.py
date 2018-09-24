QUOTE = '"'
EOS = chr(0)
PUNCTUATIONS = {'{', '}', '[', ']', ',', ':', EOS}
"""
I tried to split up the quoted-unquoted segments
and then pass the unquoted segments for further parsing.
With a brief victory, I failed miserably.
The escape sequences caught me, for example, '\\\\\\"'.
"""


def tokenize(string):
    """
    Take care of quote with priority
    Single quote is regular char and has no special meaning.
    Single quote cannot be used as string quote in JSON
    :param string:
    :return:
    """
    result = []
    string += EOS  # sentinel for EOS
    start, indx, quoted = 0, 0, False
    while indx < len(string):
        char = string[indx]
        seg = string[start:indx]
        indx += 1
        if quoted:
            if char == '\\':  # escape sequence
                indx += 1
                # OPTIONAL: validate escape sequence
            elif char == QUOTE:  # end of quote
                quoted = False
                result.append(QUOTE + seg + QUOTE)
                start = indx
        elif char == QUOTE:  # start of quote
            quoted = True
            start = indx
        elif char in PUNCTUATIONS:
            attemptParseValue(seg, result)
            result.append(char)
            start = indx
    return result[:-1]


def attemptParseValue(seg, result):
    seg = seg.strip()
    if not seg:
        return

    if validateValue(seg):
        result.append(seg)
    else:
        raise ValueError('Invalid JSON value "{}"'.format(seg))


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
    tests = ['{"1":"\\"1:{1:1}","4":[true, null], "2":{"1":"\\"1:{1:1}","2":2}}',
             '{"4":[true, null], "true":{"a": null,"foo":false},"2":{"1":"\\"1:{1:1}","2":2}}',
             '{"a":-1.0e-13,"null":false, "foo":[], "bar":{}}',
             '{ \t "" : 1 }        \n'
             '"\\\\"',
             '"\\"\\\\"',
             ]
    for test in tests:
        print tokenize(test)
