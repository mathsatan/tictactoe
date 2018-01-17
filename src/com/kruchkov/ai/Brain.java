package com.kruchkov.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Brain implements Iterable<TicMatrix> {

    private static final int[][][] priorityTable = new int[][][] {
            {
                    {5,5,5,310},
                    {200,210,215,220},
                    {250,260,270,280},
                    {300,330,350,360}
            },
            {
                    {10,10,10,209},
                    {205,206,207,208},
                    {201,202,203,204},
                    {400,410,420,430}
            }
    };

    public static int getPriority(boolean type, int p, int i) {
        int t = type ? 1 : 0;
        final int PRIORITY_COUNT = priorityTable[t].length;
        final int IMPOSITION_VALUE = priorityTable[t][0].length;
        if (p < 0 || i < 0) {
            System.out.println("Wrong priority table index");
            return 0;
        }
        if (p >= PRIORITY_COUNT) {
            p = PRIORITY_COUNT - 1;
        }
        if (i >= IMPOSITION_VALUE) {
            i = IMPOSITION_VALUE - 1;
        }
        return priorityTable[t][p][i];
    }

    static List<TicMatrix> brain = new ArrayList<>();
    static {
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,2,4,2,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2},
                {2},
                {4},
                {2},
                {2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,4,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,4,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}
        }));


        brain.add(new TicMatrix(3, new Byte[][] {
                {2,4,2,2,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2},
                {4},
                {2},
                {2},
                {2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,2,2,4,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2},
                {2},
                {2},
                {4},
                {2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {3,3,3,3,2},
                {3,3,3,4,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,4,3,3,3},
                {2,3,3,3,3}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,3,3,3,3},
                {3,4,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,4,3},
                {3,3,3,3,2}
        }));

        brain.add(new TicMatrix(3, new Byte[][] {
                {4,2,2,2,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {4},
                {2},
                {2},
                {2},
                {2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,2,2,2,4}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2},
                {2},
                {2},
                {2},
                {4}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {3,3,3,3,4},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {2,3,3,3,3}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {3,3,3,3,2},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {4,3,3,3,3}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {4,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,2}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {2,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,4}
        }));

        // 3
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,2,2,2,4,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,4,2,2,2,0}
        }));
        brain.add(new TicMatrix(3, new Byte[][] {
                {0},
                {2},
                {2},
                {2},
                {4},
                {0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0},
                {4},
                {2},
                {2},
                {2},
                {0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,4,3},
                {3,3,3,3,3,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,3,3,3,3,3},
                {3,4,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,4,3,3,3,3},
                {0,3,3,3,3,3}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,4,3},
                {3,3,3,2,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}
        }));


        brain.add(new TicMatrix(2, new Byte[][] {
                {0,2,2,4,2,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,2,4,2,2,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0},
                {2},
                {2},
                {4},
                {2},
                {0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0},
                {2},
                {4},
                {2},
                {2},
                {0}
        }));

        brain.add(new TicMatrix(2, new Byte[][] {
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,2,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,4,3,3,3},
                {3,3,3,2,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,2,3,3},
                {3,3,4,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}
        }));
        brain.add(new TicMatrix(2, new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,4,3,3},
                {3,3,2,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}
        }));

        // 2
        brain.add(new TicMatrix(1, new Byte[][] {
                {0,2,2,4,0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0,4,2,2,0}
        }));

        brain.add(new TicMatrix(1, new Byte[][] {
                {0},
                {2},
                {2},
                {4},
                {0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0},
                {4},
                {2},
                {2},
                {0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0,3,3,3,3},
                {3,2,3,3,3},
                {3,3,2,3,3},
                {3,3,3,4,3},
                {3,3,3,3,0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0,3,3,3,3},
                {3,4,3,3,3},
                {3,3,2,3,3},
                {3,3,3,2,3},
                {3,3,3,3,0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {3,3,3,3,0},
                {3,3,3,2,3},
                {3,3,2,3,3},
                {3,4,3,3,3},
                {0,3,3,3,3}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {3,3,3,3,0},
                {3,3,3,4,3},
                {3,3,2,3,3},
                {3,2,3,3,3},
                {0,3,3,3,3}
        }));

        brain.add(new TicMatrix(1, new Byte[][] {
                {0,2,4,2,0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0},
                {2},
                {4},
                {2},
                {0}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {3,3,3,3,0},
                {3,3,3,2,3},
                {3,3,4,3,3},
                {3,2,3,3,3},
                {0,3,3,3,3}
        }));
        brain.add(new TicMatrix(1, new Byte[][] {
                {0,3,3,3,3},
                {3,2,3,3,3},
                {3,3,4,3,3},
                {3,3,3,2,3},
                {3,3,3,3,0}
        }));
        // attack
        brain.add(new TicMatrix(0, new Byte[][] {
                {0,3,3,3,3,3},
                {3,2,3,3,3,3},
                {3,3,0,3,3,3},
                {3,3,3,4,3,3},
                {3,3,3,3,0,3},
                {3,3,3,3,3,0}
        }));
        brain.add(new TicMatrix(0, new Byte[][] {
                {0,3,3,3,3,3},
                {3,0,3,3,3,3},
                {3,3,4,3,3,3},
                {3,3,3,0,3,3},
                {3,3,3,3,2,3},
                {3,3,3,3,3,0}
        }));

        brain.add(new TicMatrix(0, new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,0,3},
                {3,3,3,4,3,3},
                {3,3,0,3,3,3},
                {3,2,3,3,3,3},
                {0,3,3,3,3,3}
        }));
        brain.add(new TicMatrix(0,  new Byte[][] {
                {3,3,3,3,3,0},
                {3,3,3,3,2,3},
                {3,3,3,0,3,3},
                {3,3,4,3,3,3},
                {3,0,3,3,3,3},
                {0,3,3,3,3,3}
        }));

        brain.add(new TicMatrix(0, new Byte[][] {
                {0,2,0,4,0,0}
        }));
        brain.add(new TicMatrix(0, new Byte[][] {
                {0,0,4,0,2,0}
        }));
        brain.add(new TicMatrix(0, new Byte[][] {
                {0},
                {2},
                {0},
                {4},
                {0},
                {0}
        }));
        brain.add(new TicMatrix(0, new Byte[][] {
                {0},
                {0},
                {4},
                {0},
                {2},
                {0}
        }));
    }

    @Override
    public Iterator<TicMatrix> iterator() {
        return brain.iterator();
    }
}