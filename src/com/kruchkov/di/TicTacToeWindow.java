package com.kruchkov.di;

import javax.swing.*;

public class TicTacToeWindow extends JFrame {
    public TicTacToeWindow() {
        super("TickTackToe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ServiceInjector injector = new FieldGuiInjector();
        this.add(injector.getField());
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
