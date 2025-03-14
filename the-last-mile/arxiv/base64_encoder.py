# -*- coding: utf-8 -*-

class Base64Encoder(object):
    def __init__(self):
        self.chars = [chr(ord('A') + x) for x in range(0, 25)] + \
                     [chr(ord('a') + x) for x in range(0, 25)] + \
                     [chr(ord('0') + x) for x in range(0, 9)] + ['+', '/']
        self.padding = '='
        self.BIT_LENGTH = 6
        print (self.chars)

    def encode(self, input_bytes):
        '''
        Circular buffer implementation
        :param input_bytes:
        :return:
        '''
        buff = [0x00, 0x00]  # buff of 2 bytes
        LENGTH = len(buff) * 8
        bit_start = 0  # offset index to the starting bit in the buff
        bit_end = 0  # index to the 1-pass-last bit in the buff, 0 or 8
        i = 0  # index to the next byte in the input_bytes, if any
        length = len(input_bytes)
        output = []
        while bit_start != bit_end or i < length:  # loop-condition: either buff non-empty or input still last
            '''
            case buff not enough & input non-empty
            case buff not enough & input empty
            '''
            if self.len(bit_start, bit_end, LENGTH) < self.BIT_LENGTH:
                if i < length:
                    byte = input_bytes[i]
                    i += 1
                else:
                    byte = 0x00
                buff[bit_end / 8] = byte
                bit_end = (bit_end + 8) % LENGTH
            seg = self.get_seg(buff, bit_start)
            bit_start = (bit_start + self.BIT_LENGTH) % LENGTH
            output.append(self.encode_seg(seg))
        return output

    def encode_seg(self, seg):
        return self.chars[seg]

    def len(self, start, end, length):
        pass

    def get_seg(self, buff, start):
        i = start / 8
        offset = start % 8
        if offset <= 2:
            byte = buff[i] >> (2 - offset)
        else:
            patch = offset - 2
            byte = buff[i] << patch | buff[(i + 1) % 2] >> (8 - patch)
        return self.get_index(byte)

    def get_index(self, byte):
        return byte & ((1 << 6) - 1)


if __name__ == '__main__':
    b64encoder = Base64Encoder()
    encoded = b64encoder.encode([146, 226, 136, 130, 225, 136])
    print (encoded)
    # string = u"^¡£¢¢¢ åƒ∂\u1234ƒ√√ ç©ß¨¨∂∑∂ß∂".encode('utf-8')
    # encoded = b64encoder.encode([ord(c) for c in string])
    # print (string)
    # print ([ord(c) for c in string])
    # print (encoded)
