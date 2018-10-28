public Object next() {
    if (cache.isEmpty()) {
        if (!procure()) {
            throw new NoSuchElementException("No element is left");
        }
    }
    return cache.pop();
}
