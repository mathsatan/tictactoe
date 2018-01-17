package com.kruchkov.ai;

public class Pattern {
    private int priority = 0;
    private int imposition = 1;

    public Pattern(int p, int i) {
        this.priority = p;
        this.imposition = i;
    }
    public Pattern() {
        this(1, 1);
    }

    public Pattern(int priority, Byte[][] pattern) {
        if (priority > 0) {
            this.priority = priority;
        }
    }

    public int imposition() {
        return this.imposition;
    }
    public void incImposition() {
        ++this.imposition;
    }
    public boolean imposition(int _p) {
        if (_p < 0) {
            return false;
        }
        this.imposition = _p;
        return true;
    }

    public int priority() {
        return this.priority;
    }
    public boolean priority(int _p) {
        if (_p < 0) {
            return false;
        }
        this.priority = _p;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pattern other = (Pattern) o;

        return this.priority == other.priority && this.imposition == other.imposition;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.priority;
        hash = hash * 31 + this.imposition;
        return hash;
    }

    @Override public String toString() {
        return priority + "; " + imposition + "\n";
    }
}
