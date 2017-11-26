package com.kruchkov.di;

public class TickPoint {

    private int x;
    private int y;

    public TickPoint(int _x, int _y) {
        x = _x;
        y = _y;
    }
    public TickPoint(TickPoint point) {
        if (point == null) {
            x = 0;
            y = 0;
        } else {
            x = point.getX();
            y = point.getY();
        }
    }

    public boolean set(int _x, int _y) {
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
    public String toString() {
        return "[" + x + "][" + y + "]";
    }
}