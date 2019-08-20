"""
We have a sequence of books: the i-th book has thickness books[i][0] and height books[i][1].

We want to place these books in order onto bookcase shelves that have total width shelf_width.

We choose some of the books to place on this shelf (such that the sum of their thickness is <=
shelf_width), then build another level of shelf of the bookcase so that the total height of the
bookcase has increased by the maximum height of the books we just put down.  We repeat this
process until there are no more books to place.

Note again that at each step of the above process, the order of the books we place is the same
order as the given sequence of books.  For example, if we have an ordered list of 5 books,
we might place the first and second book onto the first shelf, the third book on the second
shelf, and the fourth and fifth book on the last shelf.

Return the minimum possible height that the total bookshelf can be after placing shelves in this
manner.
"""
from typing import List


class Solution:
    def minHeightShelves(self, books: List[List[int]], shelf_width: int) -> int:
        result = [0]
        for i in range(len(books)):
            k, width, max_h, heights = 0, 0, 0, []
            while i >= k and width + books[i - k][0] <= shelf_width:
                max_h = max(max_h, books[i - k][1])
                heights.append(max_h + result[i - k])
                width += books[i - k][0]
                k += 1
            result.append(min(heights))
        return result[-1]


if __name__ == '__main__':
    tests = [
        [[[1, 1], [2, 3], [2, 3], [1, 1], [1, 1], [1, 1], [1, 2]], 4, 6],
    ]
    for test, amount, ans in tests:
        sol = Solution()
        result = sol.minHeightShelves(test, amount)
        assert result == ans, (test, amount, result)
