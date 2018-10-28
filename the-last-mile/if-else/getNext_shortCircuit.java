public Object next() {
    if (cache.isEmpty() && !procure()) {
        throw new NoSuchElementException("No element is left");
    }
    return cache.pop();
}
