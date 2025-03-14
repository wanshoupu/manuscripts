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
    return toPrettyString(obj)


def parseJson(string):
    """
    input is JSON string
    Output may be object of type: list, dictionary, string, number, False, True, or None.
    When it's a dictionary, its keys are of type string and values may be objects of the above
    types again.
    :param string:
    :return: an object representing the information in JSON string
    """
    pass


def toPrettyString(obj):
    """
    Input may be of type: string, number, boolean, dictionary, or None.
    When it's a dictionary, its keys are of type string and values can be
    :param obj obj represents an in-memory data structure of a JSON
    :param indent the overall indent value, default to 0
    :return: pretty format of my_json
    """
    pass
