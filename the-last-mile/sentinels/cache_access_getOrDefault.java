// cache stores active machine ids (integer) keyed by rack number (integer)
Map<Integer, Set<Integer>> cache = new HashMap<>();
public boolean isAlive(int rackId, int machineId) {
    Set<Integer> rack = cache.get(rackId);
    if (rack == null) return false;
    return rack.contains(machineId);
}
