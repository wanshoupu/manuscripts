// cache stores active machine ids (integer) keyed by rack number (integer)
Map<Integer, Set<Integer>> cache = new HashMap<>();
public boolean isAlive(int rackId, int machineId) {
    Set<Integer> jumps = cache.getOrDefault(rackId, Collections.emptySet());
    return jumps.contains(machineId);
}
