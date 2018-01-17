package com.kruchkov.ai;

import com.kruchkov.di.Field;
import com.kruchkov.di.TicPoint;

public interface Robot {
    void setField(final Field matrix);
    TicPoint makeAMove();
    Result isSomeoneWin();
}