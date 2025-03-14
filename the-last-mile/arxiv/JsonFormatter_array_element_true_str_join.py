def arrayToPrettyString(obj, indent):
    elements = arrayElements(obj, indent + 1)
    return "[" + ",".join(elements) + "\n" + whitespace(indent) + "]"
