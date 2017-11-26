package com.kruchkov.di;

public interface Field {
	Byte get(int i, int j);
	boolean set(int i, int j, Byte element);
	boolean set(TickPoint p, Byte element);
	int cols();
	int rows();
	boolean isEmptyCell();
}