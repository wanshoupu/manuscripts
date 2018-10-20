result = [0, 1]


def fibonacci(n):
    if n < 0:
        raise ValueError('Invalid input')
    for i in range(len(result), n + 1):
        result.append(result[i - 2] + result[i - 1])
    return result[n]
