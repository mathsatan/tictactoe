package com.kruchkov.di;

public class TicPoint implements Comparable<TicPoint> {

    private int x;
    private int y;

    public TicPoint() {
        this(0, 0);
    }

    public TicPoint(int _x, int _y) {
        x = _x;
        y = _y;
    }

    public TicPoint(TicPoint point) {
        if (point == null) {
            x = 0;
            y = 0;
        } else {
            x = point.getX();
            y = point.getY();
        }
    }

    public boolean set(int _x, int _y) {
        if (_x < 0 || _y < 0) {
            return false;
        }
        x = _x;
        y = _y;
        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TicPoint)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        TicPoint that = (TicPoint) obj;
        return (this.x == that.x && this.y == that.y);
    }

    public int compareTo(TicPoint other) {
        if(this.x == other.x && this.y == other.y) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "[" + x + "][" + y + "]";
    }
}