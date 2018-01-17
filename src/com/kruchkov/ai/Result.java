package com.kruchkov.ai;

import com.kruchkov.di.Player;
import com.kruchkov.di.TicPoint;

public class Result {
    private TicPoint point = null;
    private TicPoint points[] = null;
    private Player player = Player.NONE;

    Result() {
        constructor(0, 0);
    }

    Result(int _x, int _y) {
        constructor(_x, _y);
    }

    Result(TicPoint p) {
        if (p == null) {
            constructor(0, 0);
        } else {
            constructor(p.getX(), p.getY());
        }
    }

    private void constructor(int _x, int _y) {
        if (_x < 0) {
            _x = 0;
        }
        if (_y < 0) {
            _y = 0;
        }
        point = new TicPoint(_x, _y);

        points = new TicPoint[5];
        for (int i = 0; i < points.length; ++i) {
            points[i] = new TicPoint(0, 0);
        }
    }

    public void printArr() {
        for (TicPoint p : points) {
            System.out.println(p.toString() + "; ");
        }
    }

    public boolean setArrPoint(int index, int i, int j) {
        if (index < 0 || index > 4) {
            return false;
        }
        return points[index].set(i, j);
    }

    public TicPoint getArrPoint(int i) {
        if (i < 0 || i > 4) {
            return null;
        }
        return new TicPoint(points[i]);    // check this
    }

    public boolean setPoint(TicPoint _point) {
        if (_point == null) {
            return false;
        }
        return point.set(_point.getX(), _point.getY());
    }

    public TicPoint getPoint() {
        return new TicPoint(point);
    }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        if (player != null) {
            this.player = player;
        }
    }

    @Override
    public String toString() {
        if (point == null) {
            return "Inner point is null";
        }
        return point.toString();
    }
}