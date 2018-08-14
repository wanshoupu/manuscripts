Map<Integer, Set<Integer>> jumpMap = new HashMap<>();
Set<Integer> jumps = jumpMap.getOrDefault(key, Collections.emptySet());
if (jumps.isEmpty()) {
    return false;
} else if (jumps.contains(index)) {
    return true;
} else {
    // do something else
}
