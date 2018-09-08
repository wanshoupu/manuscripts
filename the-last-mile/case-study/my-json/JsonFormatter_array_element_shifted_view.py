def __arrayToPrettyString__(obj, indent):
    elements = __arrayElements__(obj, indent + 1)
    result = [e +
              # field separator (for all but last line) and newline.
              ("," if n < len(obj) - 1 else "")
              for n, e in enumerate(elements)]
    return "[" + "".join(result) + "\n" + __white__(indent) + "]"


def __arrayElements__(obj, indent):
    return ["\n" + __white__(indent) +  # indentation prefix
            toPrettyString(v, indent) for n, v in enumerate(obj)]
