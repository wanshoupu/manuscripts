class Formatter(object):
    def __init__(self):
        self.unit = "  "
        self.newline = "\n"
        self.sep = ','

    def indent(self, string):
        """
        Indent a multi-line string
        :param string: multi-line string
        :return: indented multi-line string
        """
        return string.replace(self.newline, self.newline + self.unit)

    def formatElements(self, items):
        return self.indent(self.sep.join(items)) + self.newline
