######################################################################
# A fictitious Python code to illustrate the idea of a function tree #
######################################################################

def main():
    if foo():
        bar()
    else:
        baz()


def bar():
    ts = getNow()
    if ts < 0:
        print invokeRPC(ts)
    else:
        raise ValueError('ts < 0')


def baz():
    # TBD
    pass


def foo():
    if cached():
        print 'is cached'
    else:
        print 'is not true'


def cached():
    # TBD
    pass


def getNow():
    # get the current timestamp
    pass


def invokeRPC(ts):
    # Invokes an remote procedure call
    pass
