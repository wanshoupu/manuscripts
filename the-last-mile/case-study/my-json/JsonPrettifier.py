"""
Given a JSON string, return the pretty formatted string.
Optionally checks for syntax errors
"""
from JsonFormatter import toString
from JsonParser import parseJson, unescape


def prettify(json):
    json = unescape(json)
    obj = parseJson(json)
    return toString(obj)


############### unit tests ####################


def unit_tests():
    tests = [
        '"abc"',
        '{"a":-1.3}',
        '{"a":-13}',
        '{"a":-1e-3}',
        '{"a":-1.0e-3}',
        '{"a":-1e-13}',
        '{"a":-1.0e-13}',
        '[1,2]',
        '{1:2,3:4}',
        '"\'"',
        "{'1':'\"1:{1:1}','2':2}",
        '{1:2,3:{"5":5,"6":6}}',
    ]
    for test in tests:
        print prettify(test)
        print '============'


def print_pretty_file(file):
    with open(file, 'r') as fd:
        json = fd.read()
        print prettify(json)


if __name__ == "__main__":
    unit_tests()

    print prettify(
        '{"status":0,"errmsg":null,"jobid":null,"progress":0,"jobstatus":0,"resource":null,"resultCount":0}')

    print prettify('{   "id":1,   "step_number":"TC01",   "title":"step title",   "description":"Sample body content",   "test_steps":"1: step one. 2: step two",   "expected_result":"Does something",   "project_id":1,   "last_saved_by_id":2,   "last_saved_by": {     "id":2,     "firstname":"Jon",     "lastname":"Cole",     "email":"jon@example.com",     "created_at":"2014-02-26T14:33:58Z",     "updated_at":"2014-02-26T14:33:58Z"   },   "step_uploads": [{      "id":1,     "file_name":"image.jpg",     "content_type": "image/jpeg",     "paths": {       "original": {         "name":"original",         "geometry": null,         "url":"http://s3.amazonaws.com/path/to/image"       },       "thumbnail": {         "name":"thumbnail",         "geometry":"100x100#",         "url":"http://s3.amazonaws.com/path/to/image"       }     }   }],   "custom_fields": [{     "id":1,     "name":"Further details",     "value":"Some further details that should be included"   }],   "created_at":"2014-02-26T14:33:58Z",   "updated_at":"2014-02-26T14:33:58Z" } ')