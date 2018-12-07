public Object next() {
    if (cache.isEmpty() && !procure()) {
        throw new NoSuchElementException("No more");
    }
    return cache.pop();
}
