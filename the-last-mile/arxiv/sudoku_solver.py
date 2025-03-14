from lc.grid_2d.mat_util import format_mat


class Solution(object):
    def __init__(self):
        self.cache = {}

    def solveSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: void Do not return anything, modify board in-place instead.
        """
        track = self.get_empty_cells(board)  # stack
        # rules = [self.form_col_rules(board),  # array of 9
        #  self.form_row_rules(board),  # array of 9
        #  self.form_cell_rules(board),  # array of 9
        # ]
        # print format_mat(board)
        i = 0
        cell, candidates = track[i]
        candidates.extend(self.retrieve(board, cell))
        while i >= 0:  # when i is retracted to < 0, that means the Sudoku has no solution
            # print 'Track[{}] = {}, {}'.format(i, cell, candidates)
            if candidates:
                # print 'board[{}][{}] = {} '.format(cell[0], cell[1], num)
                board[cell[0]][cell[1]] = candidates.pop()

                i += 1
                if i == len(track):
                    break
                cell, candidates = track[i]
                del candidates[:]
                candidates.extend(self.retrieve(board, cell))
            else:
                i -= 1
                board[cell[0]][cell[1]] = '.'
                cell, candidates = track[i]
        # print format_mat(board)

    def get_empty_cells(self, board):
        cand = [[(x, y) for x in range(9) if board[x][y] == '.'] for y in range(9)]
        return [(item, []) for sublist in cand for item in sublist]

    def retrieve(self, board, cell):
        filled_col = set(board[i][cell[1]] for i in range(9))
        filled_row = set(board[cell[0]][i] for i in range(9))
        cx, cy = (cell[0] / 3) * 3, (cell[1] / 3) * 3
        filled_cell = set(board[x][y] for x in range(cx, cx + 3) for y in range(cy, cy + 3))
        complement = tuple(sorted(filled_col | filled_row | filled_cell))
        if complement not in self.cache:
            candidates = set(str(c + 1) for c in range(9))  # BUG: fail to convert number to string
            self.cache[complement] = candidates - set(complement)
        return self.cache[complement]


if __name__ == '__main__':
    sol = Solution()
    board = [["5", "3", ".", ".", "7", ".", ".", ".", "."],
             ["6", ".", ".", "1", "9", "5", ".", ".", "."],
             [".", "9", "8", ".", ".", ".", ".", "6", "."],
             ["8", ".", ".", ".", "6", ".", ".", ".", "3"],
             ["4", ".", ".", "8", ".", "3", ".", ".", "1"],
             ["7", ".", ".", ".", "2", ".", ".", ".", "6"],
             [".", "6", ".", ".", ".", ".", "2", "8", "."],
             [".", ".", ".", "4", "1", "9", ".", ".", "5"],
             [".", ".", ".", ".", "8", ".", ".", "7", "9"]]

    sol.solveSudoku(board)
