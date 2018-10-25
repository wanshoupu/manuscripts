package org.shoupu.matrix;

public class MatrixIteratorHasNextIdempotent<T> extends MatrixIteratorNonIdempotent<T> {

    public MatrixIteratorHasNextIdempotent(T[][] matrix) {
        super(matrix);
    }

    @Override
    public boolean hasNext() {
        if (cache.isPresent()) {
            return true;
        }
        return super.hasNext();
    }
}
