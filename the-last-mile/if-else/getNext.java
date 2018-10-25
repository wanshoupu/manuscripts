public Object next() {
    if (!cache.isPresent()) {
        if (!procure()) {
            throw new NoSuchElementException("No element is left");
        }
    }
    result = cache.get();
    cache = Optional.empty();
    return cache.get();
}
