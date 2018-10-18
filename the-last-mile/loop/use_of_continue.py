def foo(n):
    result = []
    for i in range(n):
        if 0 == i % 2:
            print 'Stump'
        elif 0 == i % 3:
            print 'Clap'
        elif 0 == i % 5:
            continue
        else:
            print i
        result.append(i)
    return result


if __name__ == '__main__':
    print foo(100)

