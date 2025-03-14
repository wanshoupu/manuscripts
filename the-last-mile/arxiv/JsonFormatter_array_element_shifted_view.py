def arrayToPrettyString(obj, indent):
    elements = arrayElements(obj, indent + 1)
    result = [e +
              # field separator (for all but last line) and newline.
              ("," if n < len(obj) - 1 else "")
              for n, e in enumerate(elements)]
    return "[" + "".join(result) + "\n" + whitespace(indent) + "]"


def arrayElements(obj, indent):
    return ["\n" + whitespace(indent) +  # indentation prefix
            toPrettyString(v, indent) for n, v in enumerate(obj)]
