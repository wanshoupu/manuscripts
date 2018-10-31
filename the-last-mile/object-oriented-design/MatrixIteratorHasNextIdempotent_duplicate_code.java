package org.shoupu.matrix;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MatrixIteratorHasNextIdempotent<T> implements Iterator<T> {
    private final T[][] matrix;
    private int row;
    private int col;
    private Optional<T> cache = Optional.empty(); // Q: why not use null?

    public MatrixIteratorHasNextIdempotent(T[][] matrix) {
        this.matrix = matrix;
        this.row = 0;
        this.col = -1;
    }

    @Override
    public boolean hasNext() {
        if (cache.isPresent()) {
            return true;
        }
        if (this.row == this.matrix.length)
            return false;
        this.col += 1;
        // don't be tempted to use while loop here.
        // use recursion is simple, elegant, and scalable for higher dimension
        if (this.col == this.matrix[this.row].length) {
            this.row += 1;
            this.col = -1;
            return this.hasNext();
        }
        cache = Optional.of(this.matrix[this.row][this.col]);
        return true;
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
