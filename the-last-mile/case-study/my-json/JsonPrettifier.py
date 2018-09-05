def prettify(json):
    """
    This function reformats an input json string.
    Limitations:
    float number literals: original form of the literal float numbers may not be preserved.
    for example 1.e5 will be changed to 100000.0
    :param json:
    :return: formatted potentially multi-line Json
    """
    obj = parseJson(json)
    return toPrettyFormat(obj)

def parseJson(string):
    pass

def toPrettyFormat(obj):
    pass
