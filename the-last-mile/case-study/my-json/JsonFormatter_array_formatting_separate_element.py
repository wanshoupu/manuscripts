def arrayToPrettyString(obj, indent):
    elements = arrayElements(obj, indent)
    result = [e +
              # field separator (for all but last line) and newline.
              (",\n" if n < len(obj) - 1 else "\n")
              for n, e in enumerate(elements)]
    return "[\n" + "".join(result) + whitespace(indent) + "]"


def arrayElements(obj, indent):
    return [whitespace(indent + 1) +  # indentation prefix
            toPrettyString(v, indent + 1) for n, v in enumerate(obj)]
