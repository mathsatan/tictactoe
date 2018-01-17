package com.kruchkov.di;

import com.kruchkov.ai.Brain;
import com.kruchkov.ai.Pattern;
import com.kruchkov.ai.Tuple;

import java.util.ArrayList;
import java.util.List;

public class Variant {
    private TicPoint point = null;
    private List<Pattern> patterns = new ArrayList<>();

    public Variant(TicPoint p) {
        this.point = p;
    }

    public Variant(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.point = new TicPoint(x, y);
        }
    }

    public List<Pattern> patterns() {
        return patterns;
    }

    public boolean addToPatterns(Pattern pattern) {
        if (pattern == null) {
            return false;
        }

        int index = patterns.indexOf(pattern);
        if (index == -1) {
            this.patterns.add(pattern);
        } else {
            Pattern p = patterns.get(index);
            p.incImposition();
        }

        return true;
    }


    public TicPoint point() {
        return this.point;
    }

    public boolean point(TicPoint _p) {
        if (_p == null) {
            return false;
        }
        this.point = _p;
        return true;
    }

    public void makeChanges(int priority) {
        for (Pattern p: patterns) {
            if (p.priority() == priority) {
                p.incImposition();
                return;
            }
        }
        patterns.add(new Pattern(priority, 1));
    }

    public Tuple getBestVariant(boolean isAttack) {
        //int i = isAttack ? 1 : 0;

        Pattern best = null;
        for (Pattern p : patterns) {
            if (best == null) {
                best = p;
            } else {
                /*int imp1 = p.imposition() - 1;
                int imp2 = best.imposition() - 1;
                int p1 = p.priority() < 4 ? p.priority() : 3;
                int p2 = best.priority() < 4 ? best.priority() : 3;*/

                //if (Brain.priorityTable[i][p1][imp1] > Brain.priorityTable[i][p2][imp2]) {
                if (Brain.getPriority(isAttack, p.priority(), p.imposition() - 1) > Brain.getPriority(isAttack, best.priority(), best.imposition() - 1)) {
                    best = p;
                }
            }
        }

        return new Tuple(this.point, best);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variant other = (Variant) o;

        return this.point.getX() == other.point.getX() && this.point.getY() == other.point.getY();
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + this.point.getX();
        hash = hash * 31 + this.point.getY();
        return hash;
    }

}