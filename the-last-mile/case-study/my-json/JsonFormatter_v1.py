def toPrettyString(obj, indent=0):
    """
    Input may be of type: string, number, boolean, dictionary, or None.
    When it's a dictionary, its keys are of type string and values can be 
    :param obj obj represents an in-memory data structure of a JSON
    :param indent the overall indent value, default to 0
    :return: pretty format of my_json
    """
    if type(obj) is list:
        return arrayToPrettyString(obj, indent)
    if issubclass(type(obj), dict):
        return dictToPrettyString(obj, indent)
    # everything else is primitive types just convert to str
    return str(obj)


def whitespaces(indent):
    return " " * indent * 2


def dictToPrettyString(obj, indent):
    result = ["{\n"]
    count = 0
    for k, v in obj.items():
        count += 1
        result.append(whitespaces(indent + 1))
        result.append(toPrettyString(k))
        result.append(": ")
        result.append(toPrettyString(v, indent + 1))
        result.append(",\n" if count < len(obj) else "\n")
    result.append(whitespaces(indent))
    result.append("}")
    return ''.join(result)


def arrayToPrettyString(obj, indent):
    result = ["[\n"]
    count = 0
    for v in obj:
        count += 1
        result.append(whitespaces(indent + 1))
        result.append(toPrettyString(v, indent + 1))
        result.append(",\n" if count < len(obj) else "\n")
    result.append(whitespaces(indent))
    result.append("]")
    return ''.join(result)
