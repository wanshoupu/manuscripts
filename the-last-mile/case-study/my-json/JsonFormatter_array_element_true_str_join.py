def __arrayToPrettyString__(obj, indent):
    elements = __arrayElements__(obj, indent + 1)
    return "[" + ",".join(elements) + "\n" + __white__(indent) + "]"
