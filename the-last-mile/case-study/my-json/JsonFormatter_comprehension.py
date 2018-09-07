def __dictToPrettyString__(obj, indent):
    result = [__white__(indent + 1) +  # indentation prefix
              str(k) + ": " + toPrettyString(v, indent + 1) +  # field formatted
              # field separator (for all but last line) and newline.
              (",\n" if n < len(obj) - 1 else "\n")
              for n, (k, v) in enumerate(obj.items())]
    return "{\n" + "".join(result) + __white__(indent) + "}"


def __arrayToPrettyString__(obj, indent):
    result = [__white__(indent + 1)  # indentation prefix
              + toPrettyString(v, indent + 1) +  # field formatted
              # field separator (for all but last line) and newline.
              (",\n" if n < len(obj) - 1 else "\n")
              for n, v in enumerate(obj)]
    return "[\n" + "".join(result) + __white__(indent) + "]"
