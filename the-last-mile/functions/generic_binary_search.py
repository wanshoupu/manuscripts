lower_bound, higher_bound = ...  # inclusive bound, exclusive bound
while lower_bound + 1 < higher_bound:
    m = (lower_bound + higher_bound) // 2
    if branching_condition_met(m):
        lower_bound = m
    else:
        higher_bound = m
assert lower_bound + 1 == higher_bound
# choose one of the two choices
if lower_bound_works:
    return lower_bound
if higher_bound_works:
    return higher_bound
