package com.kruchkov.ai;

public class TicMatrix {
    private Byte[][] matrix = null;
    private int priority = 0;
    public TicMatrix(int priority, Byte[][] matrix) {
        if (priority >= 0) {
            this.priority = priority;
        }
        this.matrix = matrix;
    }
    public Byte[][] matrix() {
        return this.matrix;
    }
    public int priority() {
        return this.priority;
    }
    public void priority(int _p) {
        if (_p >= 0) {
            this.priority = _p;
        }
    }
}
