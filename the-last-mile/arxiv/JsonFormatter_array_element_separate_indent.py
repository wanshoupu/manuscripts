def toPrettyString(obj):
    if type(obj) is list:
        return arrayToPrettyString(obj)
    return str(obj)


whitespace = "  "


def indent(string):
    return string.replace("\n", "\n" + whitespace)


def arrayToPrettyString(obj):
    elements = arrayElements(obj)
    return "[" + indent(",".join(elements)) + "\n" + "]"


def arrayElements(obj):
    return ["\n" + toPrettyString(v) for v in obj]
