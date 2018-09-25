from Formatter import Formatter

formatter = Formatter()


def toPrettyString(obj):
    """
    Input may be of type: string, number, boolean, dictionary, or None.
    When it's a dictionary, its keys are of type string and values can be
    :param obj obj represents an in-memory data structure of a JSON
    :param indent the overall indent value, default to 0
    :return: string format of my_json
    """
    if type(obj) is list:
        return arrayToPrettyString(obj)
    if issubclass(type(obj), dict):
        return dictToPrettyString(obj)
    return str(obj)


def dictToPrettyString(obj):
    items = [formatter.newline + str(k) + ": " + toPrettyString(v)
             for k, v in obj.items()]
    return "{" + formatter.formatElements(items) + "}"


def arrayToPrettyString(obj):
    items = [formatter.newline + toPrettyString(v) for v in obj]
    return "[" + formatter.formatElements(items) + "]"
