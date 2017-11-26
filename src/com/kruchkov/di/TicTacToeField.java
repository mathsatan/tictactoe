package com.kruchkov.di;

import java.util.Arrays;

public class TicTacToeField implements Field {
	private int emptyCellCount = 0;
	private Byte[][] matrix = null;
	private int rowCount;
	private int colCount;
	public TicTacToeField(int n, int m) {
		if (n < 6 || m < 6) {
			System.out.println("Wrong size! Generated matrix[6x6]");
			//matrix = new byte[6][6];
			rowCount = 10;
			colCount = 10;
		} else {
			rowCount = n;
			colCount = m;
		}

		matrix = new Byte[rowCount][colCount];
		for (Byte[] row: matrix) {
			Arrays.fill(row, (byte) 0);
		}
	}
	public int rows() {
		return rowCount;
	}
	public int cols() {
		return colCount;
	}

	public Byte get(int i, int j) {
		if (i < rowCount && j < colCount) {
			return matrix[i][j];
		}
		return null;
	}
	public boolean set(int i, int j, Byte element) {
		if (element == null) {
			return false;
		}
		if (i < rowCount && j < colCount) {
			if ((int) matrix[i][j] == Symbol.EMPTY.ordinal()) {
				matrix[i][j] = element;
				++emptyCellCount;
				return true;
			}

		}
		return false;
	}

	public boolean set(TickPoint p, Byte element) {
		if (p == null) {
			return false;
		}
		return set(p.getX(), p.getY(), element);
	}

	public boolean isEmptyCell() {
		return emptyCellCount < rowCount * colCount;
	}

	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < rowCount; ++i) {
			str += Arrays.toString(matrix[i]) + "\n";
		}
		return str;
	}
}