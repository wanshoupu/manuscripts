def __arrayToPrettyString__(obj, indent):
    elements = __arrayElements__(obj)
    result = [__white__(indent + 1)  # indentation prefix
              + e +
              # field separator (for all but last line) and newline.
              (",\n" if n < len(obj) - 1 else "\n")
              for n, e in enumerate(elements)]
    return "[\n" + "".join(result) + __white__(indent) + "]"


def __arrayElements__(obj, indent):
    return [toPrettyString(v, indent + 1) for n, v in enumerate(obj)]
