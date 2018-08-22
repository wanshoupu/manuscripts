import json
from pprint import pprint
from os import path

if __name__ == '__main__':
    home_dir = path.expanduser(".")
    data_dir = path.join(home_dir, 'test.json')
    # data_dir = 'test.yml'
    with open(data_dir, 'rd') as fd:
        data = json.load(fd)
        pprint(data)
