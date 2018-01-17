package com.kruchkov.ai;

import com.kruchkov.di.TicPoint;

public class Tuple {
    private TicPoint point = null;
    private Pattern pattern = null;

    public Pattern pattern() {
        return pattern;
    }

    public void pattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public TicPoint point() {
        return point;
    }

    public void point(TicPoint point) {
        this.point = point;
    }

    public Tuple(TicPoint point, Pattern pattern) {
        this.point = point;
        this.pattern = pattern;
    }
}
