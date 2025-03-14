package org.shoupu.matrix;

import java.util.NoSuchElementException;
import java.util.Optional;

public class MatrixIteratorNonIdempotent<T> extends MatrixIterator<T> {
    protected Optional<T> cache = Optional.empty(); // Q: why not use null?

    public MatrixIteratorNonIdempotent(T[][] matrix) {
        super(matrix);
    }

    @Override
    public boolean hasNext() {
        boolean result = super.hasNext();
        if (result) {
            cache = Optional.of(super.next());
        }
        return result;
    }

    @Override
    public T next() {
        if (!cache.isPresent() && !hasNext()) {
            throw new NoSuchElementException("No element is left");
        }
        T result = cache.get();
        cache = Optional.empty();
        return result;
    }
}
