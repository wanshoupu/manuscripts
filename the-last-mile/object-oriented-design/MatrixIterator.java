package org.shoupu.matrix;

import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private final T[][] matrix;
    private int row;
    private int col;

    public MatrixIterator(T[][] matrix) {
        this.matrix = matrix;
        this.row = 0;
        this.col = -1;
    }

    @Override
    public boolean hasNext() {
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
        return true;
    }

    @Override
    public T next() {
        return this.matrix[this.row][this.col];
    }
}
