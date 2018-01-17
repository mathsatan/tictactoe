package com.kruchkov.di;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.Arrays;

public class TicTacToeField implements Field {
	private int emptyCellCount = 0;
	private Byte[][] matrix = null;
	private int rowCount;
	private int colCount;
	private static final Logger log = Logger.getLogger(TicTacToeField.class);

	public TicTacToeField(int n, int m) {
		PropertyConfigurator.configure("log4j.properties");
		if (n < 10 || m < 10) {
			log.warn("Wrong size! Generated matrix[10x10]");
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
		/*matrix = new Byte[][]{
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,1,1,1,0,1,1,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{0,0,0,2,0,2,0,0,0,0},
				{0,0,0,0,2,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0}
		};*/
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
		log.warn("Trying to get by wrong index");
		return null;
	}
	public boolean set(int i, int j, Byte element) {
		if (element == null) {
			log.warn("Trying to set null value");
			return false;
		}
		if (i < rowCount && j < colCount) {
			if ((int) matrix[i][j] == Symbol.EMPTY.ordinal()) {
				matrix[i][j] = element;
				++emptyCellCount;
				return true;
			}

		}

		log.warn("Setter fails with indexes: [" + i + "][" + j + "] and value: " + element);
		return false;
	}

	public boolean set(TicPoint p, Byte element) {
		if (p == null || element == null) {
			log.warn("Trying to set null value or null point");
			return false;
		}
		return set(p.getX(), p.getY(), element);
	}

	public boolean isEmptyCell() {
		return emptyCellCount <= rowCount * colCount;
	}

	public void reset() {
		for (Byte[] row: matrix) {
			Arrays.fill(row, (byte) 0);
		}
		emptyCellCount = 0;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < rowCount; ++i) {
			str.append(matrix[i]).append("\n");
		}
		return str.toString();
	}
}