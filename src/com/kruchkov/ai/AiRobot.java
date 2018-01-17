package com.kruchkov.ai;

import com.kruchkov.di.*;
import java.util.*;

public class AiRobot implements Robot {

    private Field matrixRef = null;

    public AiRobot(final Field matrix) {
        setField(matrix);
    }

    public void setField(final Field matrix) {
        if (matrix == null) {
            System.out.println("Null matrix has been given");
        }
        matrixRef = matrix;
    }

    public TicPoint makeAMove() {
        TicPoint p = getBestMove(move(matchDefence), move(matchAttack));
        if (p != null) {
            return p;
        }

        return randomMove();
    }

    public Result isSomeoneWin() {
        Result r = new Result();
        
        r = this.flexibleCheck(matrixRef.rows(), matrixRef.cols(), true);
        if (r.getPlayer() == Player.NONE) {
            r = this.flexibleCheck(matrixRef.cols(), matrixRef.rows(), false);

            if (r.getPlayer() == Player.NONE) {
                r = isWin(true);
                if (r.getPlayer() == Player.NONE) {
                    r = isWin(false);
                }
            }
        }

        if (!matrixRef.isEmptyCell()) {
            r.setPlayer(Player.DRAW);
        }
        return r;
    }

    private interface Match {
        Result match(Byte[][] pattern, int i, int j);
    }

    private Match matchDefence = new Match() {
        @Override
        public Result match(Byte[][] tpl, int _i, int _j) {
            int _rows = tpl.length;
            int _columns = tpl[0].length;
            Result result = null;
            for (int i = 0, x = _i; i < _rows; ++i, ++x) {
                for (int j = 0, y = _j; j < _columns; ++j, ++y) {
                    if (tpl[i][j] != Symbol.DOES_NOT_MATTER.ordinal()) {
                        if ((int) tpl[i][j] == Symbol.PLACE.ordinal()) {
                            if ((int) matrixRef.get(x, y) != Symbol.EMPTY.ordinal()) {
                                return null;
                            } else if (result == null) {
                                result = new Result(x, y);
                            }
                        } else {
                            if (!matrixRef.get(x, y).equals(tpl[i][j])) {
                                return null;
                            }
                        }
                    }
                }
            }
            return result;
        }
    };

    /*private Match matchDefence = (Byte[][] tpl, int _i, int _j) -> {
        int _rows = tpl.length;
        int _columns = tpl[0].length;
        Result result = null;
        for (int i = 0, x = _i; i < _rows; ++i, ++x) {
            for (int j = 0, y = _j; j < _columns; ++j, ++y) {
                if (tpl[i][j] != Symbol.DOES_NOT_MATTER.ordinal()) {
                    if ((int) tpl[i][j] == Symbol.PLACE.ordinal()) {
                        if ((int) matrixRef.get(x, y) != Symbol.EMPTY.ordinal()) {
                            return null;
                        } else if (result == null) {
                            result = new Result(x, y);
                        }
                    } else {
                        if (!matrixRef.get(x, y).equals(tpl[i][j])) {
                            return null;
                        }
                    }
                }
            }
        }
        return result;
    };

    private Match matchAttack = (Byte[][] tpl, int _i, int _j) -> {
        int _rows = tpl.length;
        int _columns = tpl[0].length;
        Result result = null;
        for (int i = 0, x = _i; i < _rows; ++i, ++x) {
            for (int j = 0, y = _j; j < _columns; ++j, ++y) {
                if (tpl[i][j] != Symbol.DOES_NOT_MATTER.ordinal()) {
                    if ((int) tpl[i][j] == Symbol.PLACE.ordinal()) {
                        if ((int) matrixRef.get(x, y) != Symbol.EMPTY.ordinal()) {
                            return null;
                        } else if (result == null) {
                            result = new Result(x, y);
                        }
                    } else {
                        Byte value = tpl[i][j];
                        if (value.intValue() == 2) {
                            value = (byte) 1;
                        }
                        if (!matrixRef.get(x, y).equals(value)) {
                            return null;
                        }
                    }
                }
            }
        }
        return result;
    };*/

    private Match matchAttack = new Match() {
        @Override
        public Result match(Byte[][] tpl, int _i, int _j) {
            int _rows = tpl.length;
            int _columns = tpl[0].length;
            Result result = null;
            for (int i = 0, x = _i; i < _rows; ++i, ++x) {
                for (int j = 0, y = _j; j < _columns; ++j, ++y) {
                    if (tpl[i][j] != Symbol.DOES_NOT_MATTER.ordinal()) {
                        if ((int) tpl[i][j] == Symbol.PLACE.ordinal()) {
                            if ((int) matrixRef.get(x, y) != Symbol.EMPTY.ordinal()) {
                                return null;
                            } else if (result == null) {
                                result = new Result(x, y);
                            }
                        } else {
                            Byte value = tpl[i][j];
                            if (value.intValue() == 2) {
                                value = (byte) 1;
                            }
                            if (!matrixRef.get(x, y).equals(value)) {
                                return null;
                            }
                        }
                    }
                }
            }
            return result;
        }
    };

    private List<Variant> move(Match match) {
        int rowCount = matrixRef.rows();
        int colCount = matrixRef.cols();

        ArrayList<Variant> matches = new ArrayList<>();
        Iterator<TicMatrix> it = Brain.brain.iterator();

        while (it.hasNext()) {
            TicMatrix m = it.next();
            Byte[][] template = m.matrix();

            int _rows = template.length;
            int _columns = template[0].length;
            if (_rows > rowCount || _columns > colCount) {
                continue;
            }

            for (int i = 0; i <= rowCount - _rows; ++i) {
                for (int j = 0; j <= colCount - _columns; ++j) {
                    Result r = match.match(template, i, j);
                    if (r != null) {
                        int x = isContains(matches, r.getPoint());
                        if (x != -1) {
                            Variant v = matches.get(x);
                            v.makeChanges(m.priority());
                        } else {
                            Variant var = new Variant(r.getPoint());
                            var.addToPatterns(new Pattern(m.priority(), 1));
                            matches.add(var);
                        }
                    }
                }
            }
        }

        return matches;
    }
        // is contais Variant instance with point p
    private static int isContains(List<Variant> arr, TicPoint p) {
        if (arr == null || p == null) {
            return -1;
        }
        for (int i = 0; i < arr.size(); ++i) {
            if (arr.get(i).point().equals(p)) {
                return i;
            }
        }
        return -1;
    }

    private static TicPoint getBest(Tuple t1, boolean isAttack1, Tuple t2, boolean isAttack2) {
        /*int i = isAttack1 ? 1 : 0;
        int j = isAttack2 ? 1 : 0;
        int p1 = t1.pattern().priority() < 4 ? t1.pattern().priority() : 3;
        int p2 = t2.pattern().priority() < 4 ? t2.pattern().priority() : 3;
        int i1 = t1.pattern().imposition() - 1;
        int i2 = t2.pattern().imposition() - 1;*/

        if (Brain.getPriority(isAttack1, t1.pattern().priority(), t1.pattern().imposition() - 1) >
                Brain.getPriority(isAttack2, t2.pattern().priority(), t2.pattern().imposition() - 1)) {
            return t1.point();
        }
        return t2.point();
    }

    private static Tuple reduceVariants(List<Variant> list, boolean isAttack) {
        //int i = isAttack ? 1 : 0;
        Pattern best = null;
        TicPoint p = null;
        for (Variant v: list) {
            Tuple t = v.getBestVariant(isAttack);
            if (best == null) {
                best = t.pattern();
                p = t.point();
            } else {
                if (Brain.getPriority(isAttack, t.pattern().priority(), t.pattern().imposition() - 1) >
                        Brain.getPriority(isAttack, best.priority(), best.imposition() - 1)) {
                    best = t.pattern();
                    p = t.point();
                }
                /*if (Brain.priorityTable[i][p1][imp1] > Brain.priorityTable[i][p2][imp2]) {
                    best = t.pattern();
                    p = t.point();
                }*/
            }
        }
        if (best != null && p != null) {
            return new Tuple(p, best);
        }
        return null;
    }
    private static TicPoint getBestMove(List<Variant> arr1, List<Variant> arr2) {
        Tuple t1 = reduceVariants(arr1, false);
        Tuple t2 = reduceVariants(arr2, true);
        if (t1 != null && t2 != null) {
            return getBest(t1, false, t2, true);
        }

        if (t1 != null) {
            return t1.point();
        }
        if (t2 != null) {
            return t2.point();
        }
        return null;
        /*Pattern best = null;
        TicPoint p = null;
        for (Variant v: arr1) {
            Tuple t = v.getBestVariant(false);
            Pattern curr = t.pattern;
            if (best == null) {
                best = curr;
                p = t.point;
            } else {
                int imp1 = curr.imposition() - 1;
                int imp2 = best.imposition() - 1;
                if (Brain.priorityTable[0][curr.priority()][imp1] > Brain.priorityTable[0][best.priority()][imp2]) {
                    best = curr;
                    p = t.point;
                }
            }
        }

        Pattern best2 = null;
        TicPoint p2 = null;
        for (Variant v: arr2) {
            Tuple t = v.getBestVariant(true);
            Pattern curr = t.pattern;
            if (best2 == null) {
                best2 = curr;
                p2 = t.point;
            } else {
                int imp1 = curr.imposition() - 1;
                int imp2 = best2.imposition() - 1;
                if (Brain.priorityTable[1][curr.priority()][imp1] > Brain.priorityTable[1][best2.priority()][imp2]) {
                    best2 = curr;
                    p2 = t.point;
                }
            }
        }

        if (best != null && best2 != null) {
            int imp1 = best.imposition() - 1;
            int imp2 = best2.imposition() - 1;
            if (Brain.priorityTable[0][best.priority()][imp1] > Brain.priorityTable[1][best2.priority()][imp2]) {
                return p;
            } else {
                return p2;
            }
        }
        if (best == null && best2 != null) {return p2;}
        return p;*/
    }

    private static void print(List<Variant> list) {
        for (Variant v: list) {
            System.out.println("Point: " + v.point().getX() + ", " + v.point().getY());
            for (Pattern p : v.patterns()) {
                System.out.println("\t Pattern: prior: " + p.priority() + "; imp: " + p.imposition());
            }
        }
    }

    private TicPoint randomMove() {

        Random r = new Random();
        for (int i = 0; i < matrixRef.rows(); ++i) {
            for (int j = 0; j < matrixRef.cols(); ++j) {
                int x = r.nextInt(matrixRef.rows());
                int y = r.nextInt(matrixRef.cols());
                Byte item = matrixRef.get(x, y);
                if (item.intValue() == Symbol.EMPTY.ordinal()) {
                    return new TicPoint(x, y);
                }
            }
        }
        for (int i = 0; i < matrixRef.rows(); ++i) {
            for (int j = 0; j < matrixRef.cols(); ++j) {
                if ((int) matrixRef.get(i, j) == Symbol.EMPTY.ordinal()) {
                    return new TicPoint(i, j);
                }
            }
        }
        return null;
    }

    private Result flexibleCheck(final int N, final int M, final boolean flag) {
        Result r = new Result(0, 0);
        r.setPlayer(Player.ROBOT);
        Result h = new Result(0, 0);
        h.setPlayer(Player.HUMAN);
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j <= M - 5; ++j) {
                int k = j;
                int robot = 0;
                int human = 0;
                while (k < j + 5) {
                    if (flag) {
                        if (matrixRef.get(i, k) == 1) {
                            r.setArrPoint(k - j, i, k);
                            ++robot;
                        } else if (matrixRef.get(i, k) == 2) {
                            h.setArrPoint(k - j, i, k);
                            ++human;
                        }
                    } else {
                        if (matrixRef.get(k, i) == 1) {
                            r.setArrPoint(k - j, k, i);
                            ++robot;
                        } else if (matrixRef.get(k, i) == 2) {
                            h.setArrPoint(k - j, k, i);
                            ++human;
                        }
                    }
                    ++k;
                }
                if (robot == 5) {
                    return r;
                }
                if (human == 5) {
                    return h;
                }
            }
        }
        return new Result();
    }

    private Result isWin(boolean flag) {
        Result r = new Result();
        r.setPlayer(Player.ROBOT);
        Result h = new Result();
        h.setPlayer(Player.HUMAN);
        for (int i = 0; i <= matrixRef.rows() - 5; ++i) {
            for (int j = 0; j <= matrixRef.cols() - 5; ++j) {
                int robot = 0;
                int human = 0;
                for (int k = 0; k < 5; ++k) {
                    int a = i + k;
                    int b = flag ? j + k : j + 4 - k;
                    if (matrixRef.get(a, b).intValue() == 1) {
                        r.setArrPoint(k, a, b);
                        ++robot;
                    } else if (matrixRef.get(a, b).intValue() == 2) {
                        h.setArrPoint(k, a, b);
                        ++human;
                    } else {
                        break;
                    }

                    if (robot == 5) {
                        return r;
                    }
                    if (human == 5) {
                        return h;
                    }
                }
            }
        }
        return new Result();
    }
}