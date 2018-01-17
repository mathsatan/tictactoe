package com.kruchkov.di;

import javax.swing.*;

public abstract class FieldGui extends JPanel {
	abstract public boolean setCell(int i, int j, Byte value);
}