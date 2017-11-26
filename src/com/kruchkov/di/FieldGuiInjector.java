package com.kruchkov.di;

import com.kruchkov.ai.AiRobot;
import com.kruchkov.ai.Robot;

public class FieldGuiInjector implements ServiceInjector {
	@Override
	public FieldGui getField() {
		Field f = new TicTacToeField(10, 10);
		Robot r = new AiRobot(f);
		return new MyDIApplication(f, r);
	}
}