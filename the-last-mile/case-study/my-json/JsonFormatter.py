from Formatter import Formatter

formatter = Formatter()


def dictToPrettyString(obj):
    items = [formatter.newline + str(k) + ": " + toPrettyString(v)
             for k, v in obj.items()]
    return "{" + formatItems(items) + "}"


def arrayToPrettyString(obj):
    items = [formatter.newline + toPrettyString(v) for v in obj]
    return "[" + formatItems(items) + "]"


def formatItems(items):
    return formatter.indent(','.join(items)) + formatter.newline
