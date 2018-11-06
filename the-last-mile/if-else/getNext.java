public Object next() {
    if (cache.isEmpty()) {
        if (!procure()) {
            throw new NoSuchElementException("No more");
        }
    }
    return cache.pop();
}
