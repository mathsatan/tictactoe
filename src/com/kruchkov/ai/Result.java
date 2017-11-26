package com.kruchkov.ai;

import com.kruchkov.di.Player;
import com.kruchkov.di.TickPoint;

class Result {
    private TickPoint point = null;
    private TickPoint points[] = null;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player = Player.NONE;

    public Result(int _x, int _y) {
        point = new TickPoint(_x, _y);
        points = new TickPoint[5];
        for (int i = 0; i < points.length; ++i) {
            points[i] = new TickPoint(0, 0);
        }
    }
    public void printArr() {
        for (TickPoint p : points) {
            System.out.println(p.toString() + "; ");
        }
    }

    public boolean setArrPoint(int index, int i, int j) {
        if (index < 0 || index > 4) {
            return false;
        }
        return points[index].set(i, j);
    }
    public TickPoint getArrPoint(int i) {
        if (i < 0 || i > 4) {
            return null;
        }
        return new TickPoint(points[i]);    // check this
        //return points[i];
    }

    public boolean setPoint(TickPoint _point) {
        if (_point == null) {
            return false;
        }
        return point.set(_point.getX(), _point.getY());
    }
    public TickPoint getPoint() {
        return point;
    }

    @Override
    public String toString() {
        if (point == null) {
            return "Inner point is null";
        }
        return point.toString();
    }
}