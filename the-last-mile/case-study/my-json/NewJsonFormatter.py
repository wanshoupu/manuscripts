from Formatter import Formatter

formatter = Formatter()


def __dictToPrettyString__(obj):
    items = [formatter.newline + str(k) + ": " + toPrettyString(v)
             for k, v in obj.items()]
    return "{" + formatter.formatElements(items) + "}"


def __arrayToPrettyString__(obj):
    items = [formatter.newline + toPrettyString(v) for v in obj]
    return "[" + formatter.formatElements(items) + "]"
