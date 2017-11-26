package com.kruchkov.ai;

import com.kruchkov.di.Field;
import com.kruchkov.di.Player;
import com.kruchkov.di.TickPoint;

public interface Robot {
    void setField(final Field matrix);
    TickPoint makeAMove();
    Player isSomeoneWin();
}