class Solution(object):
    def __init__(self):
        self.strobo_set = {0, 1, 6, 8, 9}

    def strobogramatic_count(self, low, high):
        """
        Return the strobogrammatic numbers low <= x <= high (inclusive both ends)
        :param low:
        :param high:
        :return:
        """
        return self.strobo_count_no_greater_than(high) - self.strobo_count_no_greater_than(low) + \
               1 if self.strobo_test(low) else 0

    def strobo_count_no_greater_than(self, num, exclude_zero=True):
        if not num:
            return 1
        """
        THe total count comes from 3 parts:
        - the part that have the same length as the original num but lesser first digit
        - the part that have shorter length than the original num
        - the part that have the same highest digit as the original num (recursive)
        :param num:
        :param exclude_zero:
        :return:
        """
        if len(num) == 1:
            return sum(1 for s in self.strobo_set if s < int(num)) - 1 if exclude_zero else 0
        # part 1
        multiple = self.get_strobo_multiple(num[0])
        if not exclude_zero:
            multiple += 1
        if num[0] in self.strobo_set and not self.strobo_test(num[0], num[-1]):
            multiple -= 1
        count = multiple * self.strobo_count_given_len(len(num) - 2)
        # print 'part 1:', count

        # part 2
        for l in range(1, len(num)):
            count += self.strobo_count_given_len(l)
        # print 'part 1+2:', count

        # part 3
        if len(num) >= 2 and self.strobo_test(num[0] + num[-1]):
            count += self.strobo_count_no_greater_than(num[1:-1], False)
        # print 'part 1+2+3:', count
        return count

    def strobo_count_given_len(self, length):
        if length == 0:
            return 1
        if length == 1:
            return 3
        assert length > 1
        g1 = self.strobo_count_allow_zero(length - 2)
        g2 = self.strobo_count_allow_zero(length)
        return g2 - g1

    def strobo_count_allow_zero(self, length):
        if length == 0:
            return 1
        if length == 1:
            return 3
        assert length > 1
        return 5 * self.strobo_count_allow_zero(length - 2)

    def get_strobo_multiple(self, digit):
        """
        Excluding leading zero deducted
        0-> 0, 1-> 1, 6-> 9, 8-> 8, 9-> 6
        :param digit: 
        :return: 
        """
        assert digit > 0
        multiples = [0, 1, 1, 1, 1, 1, 2, 2, 3]
        return multiples[int(digit) - 1]

    def strobo_test(self, num):
        for i in range(len(num)):
            c1 = num[i]
            c2 = num[-i]
        if not (c1 == '0' == c2
                or c1 == '1' == c2
                or c1 == '8' == c2
                or c1 == '6' and c2 == '9'):
            return False
        return True


if __name__ == '__main__':
    sol = Solution()
    # for i in [0, 1, 2, 3, 4]:
    #     print i, sol.strobo_count_allow_zero(i), sol.strobo_count_given_len(i)
    # print '"no greater than 50"', sol.strobo_count_no_greater_than('50')
    print '"no greater than 888"', sol.strobo_count_no_greater_than('888')
    print '"no greater than 100"', sol.strobo_count_no_greater_than('100')
    print '"no greater than 50"', sol.strobo_count_no_greater_than('50')
    print "('50', '100')", sol.strobogramatic_count('50', '100')
