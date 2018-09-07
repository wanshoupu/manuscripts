def parseJson(string):
    """
    input is JSON string
    Output may be object of type: list, dictionary, string, number, False, True, or None.
    When it's a dictionary, its keys are of type string and values may be objects of the above
    types again.
    :param string:
    :return: an object representing the information in JSON string
    """
    preproc = __tokenize__(string)
    return __assemble__(preproc)


def __tokenize__(string):
    """
    Break up the JSON-reprsenting string into smallest building blocks for JSON components
    such as comma, bracket, string, number, etc.
    :param string:
    :return:
    """
    pass


def __assemble__(tokens):
    """
    Takes care of structural construction for: [ ] { }
    Parse tokens and assemble them into data structures like arrays, dictionaries, etc.
    :param tokens: are the atomic building blocks for JSON data such as comma, bracket, string,
    number, etc.
    :return: an object as specified in function 'parseJson'
    """
    pass
