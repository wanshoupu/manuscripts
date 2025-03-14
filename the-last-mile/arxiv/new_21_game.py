"""
Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.
During each draw, she gains an integer number of points randomly from the range [1, W],
where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N
or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
"""


class Solution2:
    def new21Game(self, N: int, K: int, W: int) -> float:
        """
        This is conditional probability problem
        P(n, k) := the probability of staying alive while drawing cards until target is hit or
        passed
        P(n, k) = 0 if n < k
        P(n, k) = SUM { P(n-u, k-u)/W for u in 1..W} if k >= W
        P(n, k) = SUM { P(n-u, k-u)/W if u <= k else P(u<=n)/W for u in 1..W } if k < W
        Implementation wise, one can visualize the recursion relation as
        K
        ^      / / / / / /
        |     / / / / / /
        |    / / / / / /
        |   / / / / / /
        |  / / / / / /
        | / / / / / /
        |/ / / / / /
        -----------------> N
        :param N: 'Death point'
        :param K: 'set goal in mind'
        :param W: max card number in the range [1, W]
        :return: probability to get a score <= N
        """
        if N < K:
            return 0
        # initialized to 1 for all k <= 0
        # index shifted init so that result[-1] corresponds to k = 0
        from collections import deque
        result = deque([float(x < N - K) for x in range(W)])
        for _ in range(K):
            result.append(sum(result) / W)
            result.popleft()
        return result[-1]


class Solution:
    def new21Game(self, N: int, K: int, W: int) -> float:
        """
        This is conditional probability problem
        P(n, k) := the probability of staying alive while drawing cards until target is hit or
        passed
        P(n, k) = 0 if n < k
        P(n, k) = SUM { P(n-u, k-u)/W for u in 1..W} if k >= W
        P(n, k) = SUM { P(n-u, k-u)/W if u <= k else P(u<=n)/W if u > k for u in 1..W } if k < W
        :param N: 'Death point'
        :param K: 'set goal in mind'
        :param W: max card number in the range [1, W]
        :return: probability to get a score <= N
        """
        if N < K:
            return 0
        if K == 0:
            return 1

        def recurse(n, k, lookup):
            if (n, k) not in lookup:
                numerator = sum(recurse(n - u, k - u, lookup) if u < k else float(u <= n) for u in
                                range(1, 1 + W))
                lookup[n, k] = numerator / W
            return lookup[n, k]

        return recurse(N, K, {})


if __name__ == '__main__':
    sol = Solution()
    sol2 = Solution2()
    tests = [
        [9811, 8776, 1096, 3],
        [0, 0, 1, 1.],
        [4, 1, 5, .8],
        [5, 2, 5, .96],
        [6, 3, 5, .952],
        [6, 2, 10, .55],
        [10, 1, 10, 1.0000],
        [6, 1, 10, .600],
        [21, 17, 10, 0.73278],
    ]
    for n, k, w, ans in tests:
        result = sol.new21Game(n, k, w)
        result2 = sol2.new21Game(n, k, w)
        assert abs(result - result2) < 1e6, (n, k, w, result)
