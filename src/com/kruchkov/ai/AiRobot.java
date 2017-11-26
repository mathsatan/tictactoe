package com.kruchkov.ai;

import com.kruchkov.di.Field;
import com.kruchkov.di.Player;
import com.kruchkov.di.Symbol;
import com.kruchkov.di.TickPoint;

import java.util.Iterator;
import java.util.Random;

public class AiRobot implements Robot {
    private Field matrixRef = null;

    public AiRobot(final Field matrix) {
        matrixRef = matrix;
    }

    public void setField(final Field matrix) {
        matrixRef = matrix;
    }

    public TickPoint makeAMove() {

        Result defenceMove = isDefence();
        if (defenceMove != null) {
            matrixRef.set(defenceMove.getPoint(), (byte) 1);
            return defenceMove.getPoint();
        } else {
            TickPoint attackMove = attack();
            matrixRef.set(attackMove, (byte) 1);
            return attackMove;
        }
    }
    public Player isSomeoneWin() {
        Player winner = this.flexibleCheck(matrixRef.rows(), matrixRef.cols(), true);
        if (winner == Player.NONE) {
            winner = this.flexibleCheck(matrixRef.cols(), matrixRef.rows(), false);
            if (winner == Player.NONE) {
                winner = isWin(true);
                if (winner == Player.NONE) {
                    winner = isWin(false);
                }
            }
        }

        // first line?
        if (!matrixRef.isEmptyCell()) {
            return Player.DRAW;
        }
        return winner;
    }

    private Result isMatch(Byte[][] tpl, int _i, int _j) {
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

    private Brain brain = new Brain();

    private Result isDefence() {
        int rowCount = matrixRef.rows();
        int colCount = matrixRef.cols();
        Iterator<Byte[][]> it = brain.iterator();

        while (it.hasNext()) {
            Byte[][] template = it.next();
            int _rows = template.length;
            int _columns = template[0].length;
            if (_rows > rowCount || _columns > colCount) {
                return null;   // or exception?
            }

            for (int i = 0; i <= rowCount - _rows; ++i) {
                for (int j = 0; j <= colCount - _columns; ++j) {
                    Result r = isMatch(template, i, j);
                    if (r != null) {
                        return r;
                    }
                }
            }
        }
        return null;
    }

    private TickPoint attack() {
        Random r = new Random();
        for (int i = 0; i < matrixRef.rows(); ++i) {
            for (int j = 0; j < matrixRef.cols(); ++j) {
                Byte item = matrixRef.get(i, j);
                /*if (item == null) {
                    return null;
                }*/
                if (r.nextInt(5) == 0 && item.intValue() == Symbol.EMPTY.ordinal()) {
                    return new TickPoint(i, j);
                }
            }
        }
        for (int i = 0; i < matrixRef.rows(); ++i) {
            for (int j = 0; j < matrixRef.cols(); ++j) {
                if ((int) matrixRef.get(i, j) == Symbol.EMPTY.ordinal()) {
                    return new TickPoint(i, j);
                }
            }
        }
        return null;
    }

    private Player flexibleCheck(final int N, final int M, final boolean flag) {
        Result r = new Result(0, 0);
        Result h = new Result(0, 0);
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
                    r.printArr();
                    return Player.ROBOT;
                }
                if (human == 5) {
                    h.printArr();
                    return Player.HUMAN;
                }
            }
        }
        return Player.NONE;
    }

    private Player isWin(boolean flag) {
        Result r = new Result(0, 0);
        Result h = new Result(0, 0);
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
                        r.printArr();
                        return Player.ROBOT;
                    }
                    if (human == 5) {
                        h.printArr();
                        return Player.HUMAN;
                    }
                }
            }
        }
        return Player.NONE;
    }
}