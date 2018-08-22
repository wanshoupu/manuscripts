"""
A json obj may be data of type string, number, true, false, null, dictionary
or list of json obj. When a dictionary is involved, its keys must be
of type string
"""


def toString(json, indent=0):
    """
    Input may be of type: string, number, boolean, dictionary, or None.
    When it's a dictionary, its keys are of type string and values can be 
    :param json: 
    :return: string format of my-json
    """
    if json is None:
        return "null"
    if type(json) is list:
        return __arrayToString__(json, indent)
    if type(json) is dict:
        return __dictToString__(json, indent)
    if type(json) is str:
        return '"' + json + '"'
    if type(json) is bool:
        return str(json).lower()
    return str(json)


def __white__(indent):
    return ' ' * indent * 2


def __dictToString__(json, indent):
    result = ["{\n"]
    count = 0
    for k, v in json.items():
        count += 1
        result.append(__white__(indent + 1))
        result.append(toString(k))
        result.append(" : ")
        result.append(toString(v, indent + 1))
        result.append(",\n" if count < len(json) else "\n")
    result.append(__white__(indent))
    result.append("}")
    return ''.join(result)


def __arrayToString__(json, indent):
    result = ["[\n"]
    count = 0
    for v in json:
        count += 1
        result.append(__white__(indent + 1))
        result.append(toString(v, indent + 1))
        result.append(",\n" if count < len(json) else "\n")
    result.append(__white__(indent))
    result.append("]")
    return ''.join(result)


############### unit tests ####################


def generate_val():
    seed = rd.randrange(5)
    if seed == 0:
        return "adb"
    if seed == 1:
        return -1.5e-5
    if seed == 2:
        return -50
    if seed == 3:
        return False
    if seed == 4:
        return True
    return None


def build_json_obj():
    return {"abc": generate_val(), "1": generate_val(),
            "obj": {"abc": generate_val(), "1": generate_val()},
            "bc": generate_val(), "10": generate_val()}


def build_json_arr():
    return ["abc", generate_val(), "1", generate_val(),
            "obj", {"abc": generate_val(), "1": generate_val()},
            "bc", generate_val(), "10", generate_val()]


def build_json_mix():
    return {"abc": generate_val(), "1": generate_val(),
            "obj": {"abc": generate_val(), "1": generate_val()},
            "bc": generate_val(), "10": ["abc", generate_val(), "1", generate_val(),
                                         "obj", {"abc": generate_val(), "1": generate_val()},
                                         "bc", generate_val(), "10", generate_val()]}


if __name__ == "__main__":
    import random as rd

    json = build_json_mix()
    print toString(json)
