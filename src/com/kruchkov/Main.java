package com.kruchkov;

import com.kruchkov.ai.AiRobot;
import com.kruchkov.ai.Robot;
import com.kruchkov.di.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        /*ServiceInjector injector = new FieldGuiInjector();
        FieldGui app = injector.getField();*/
        JFrame frame = new JFrame("TickTackToe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Field f = new TicTacToeField(10, 10);
        Robot r = new AiRobot(f);

        frame.add(new MyDIApplication(f, r));
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
