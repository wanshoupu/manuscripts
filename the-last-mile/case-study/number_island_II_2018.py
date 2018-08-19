WATER = 0


def format_mat(mat):
    return '|\n'.join(['|' + ''.join(['{:^3}'.format(item) for item in row])
                       for row in mat]) + '|\n'


class Solution(object):
    def numIslands2(self, m, n, positions):
        """
        Union-find data structure

        :type m: int
        :type n: int
        :type positions: List[List[int]]
        :rtype: List[int]
        """
        res = [0]
        # land stores the land positions (key) : leader position (value)
        land = {}
        for p in positions:
            leaders = [self.leader(land, x) for x in self.neigh(m, n, land, *p)]
            res.append(res[-1] - len(leaders) + 1 if leaders else res[-1] + 1)
            if leaders:
                elected = self.elect_leader(land, leaders)
                land[tuple(p)] = elected
            else:
                land[tuple(p)] = None
        return res[1:]

    def elect_leader(self, land, leaders):
        """
        Random election for a leader among the leaders.
        Put the new leader as the parent of other leaders in land
        :param land:
        :param leaders:
        :return:
        """
        elected = leaders.pop()
        for l in leaders:
            land[l] = elected
        return elected

    def neigh(self, m, n, land, u, v):
        """
        Get the neighboring land
        :param m:
        :param n:
        :param land:
        :param p:
        :return:
        """
        return filter(lambda (x, y): 0 <= x < m and 0 <= y < n and (x, y) in land,
                      [(u, v + 1), (u, v - 1), (u + 1, v), (u - 1, v)])

    def leader(self, land, p):
        """
        Look for the leader position for p
        :param land: union-find data structure where
        key is position,
        value is parent position
        :param p: p must be in land otherwise raise error
        :return: leader
        """
        while land[p]:
            p = land[p]
        return p


if __name__ == '__main__':
    import random as r

    m = 8
    n = 10
    num_positions = r.randint(50, 100)
    positions = list(
        set((r.randrange(0, m), r.randrange(0, n)) for x in range(0, num_positions)))
    # positions = [(0, 1), (1, 2), (0, 2)]

    print positions
    sol = Solution()
    print 'number of island is {}'.format(sol.numIslands2(m, n, positions))
