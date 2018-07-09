class Solution(object):
    def compatible(self, schedules):
        schedules = sorted(schedules)
        time = 0
        for s in schedules:
            if s[0] < time:
                return False
            time = s[1] if time < s[1] else time
        return True


if __name__ == '__main__':
    sol = Solution()
    print sol.compatible([])
    print sol.compatible([(0, 30), (5, 10), (15, 20)])
    print sol.compatible([(0, 30), (35, 40), (45, 70)])
