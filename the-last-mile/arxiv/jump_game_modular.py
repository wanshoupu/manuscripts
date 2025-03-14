def optimal_impl(nums):
    """
    :type nums: List[int]
    :rtype: int
    """
    farthest, steps = calc(nums[:-1])
    if len(nums) - 1 <= farthest:
        return steps
    else:
        return -1


def calc(nums):
    """
    Calculate the farthest index can be reached and the minimum number of steps to reach
    :param nums:
    :return:
    """
    farthest = 0  # the farthest index reachable with 'number' steps
    number = 0  # the minimum number of steps to reach 'farthest' index
    frontier = 0  # the next reachable index based 'current' index
    # loop-invariants:
    # current <= farthest <= frontier
    for current, steps in enumerate(nums):
        if not (current <= farthest <= frontier):
            break
        frontier = max(frontier, steps + current)
        if farthest == current:  # farthest is the nearest base to reach frontier in one step
            farthest = frontier  # projectile farthest to frontier
            number += 1  # increment step
    return farthest, number
