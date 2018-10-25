public Object next() {
    if (!cache.isPresent() && !procure()) {
        throw new NoSuchElementException("No element is left");
    }
    return cache.get();
}
