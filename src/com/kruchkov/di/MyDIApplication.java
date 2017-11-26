package com.kruchkov.di;

import com.kruchkov.ai.Robot;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyDIApplication extends JPanel implements FieldGui {
	private int cellWidth = 20;
	private int cellHeight = 20;
	private Field model = null;
	private Robot robot = null;

	private static BufferedImage imageHuman = null;
	private static BufferedImage imageAi = null;

	private int rows = 10;
	private int cols = 10;

	public boolean setCell(int i, int j, Byte value) {
		if (value == null || i >= rows || j >= cols || i < 0 || j < 0) {
			return false;
		}
		if (model.set(i, j, value)) {
			return true;
		}
		return false;
	}

	public MyDIApplication(final Field f, final Robot r) {
		this.model = f;
		this.robot = r;
		this.addMouseListener(new MyMouse());
		rows = model.rows();
		cols = model.cols();
		this.setPreferredSize(new Dimension(rows * cellHeight, cols * cellWidth));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
		this.setBackground(Color.WHITE);

		try {
			imageHuman = ImageIO.read(new File("C:\\Users\\soulf\\IdeaProjects\\di\\res\\image\\player_1.png"));
			imageAi = ImageIO.read(new File("C:\\Users\\soulf\\IdeaProjects\\di\\res\\image\\player_ai.png"));
		} catch (IOException e) {
			System.out.println("Image not found! Exception: " + e.getMessage());
			System.exit(0);
		}
	}


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		updateMatrix(g);
		if (p1 != null && p2 != null) {
			g.setColor(Color.red);
			g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
		}
	}

	private void updateMatrix(Graphics g) {
		for (int i = 0; i < rows - 1; ++i) {
			g.drawLine(0, i*cellHeight + cellHeight, getWidth(), i*cellHeight + cellHeight);
		}
		for (int j = 0; j < cols - 1; ++j) {
			g.drawLine(j*cellWidth + cellWidth, 0, j*cellWidth + cellWidth, getHeight());
		}

		for (int i = 0; i < model.rows(); ++i) {
			for (int j = 0; j < model.cols(); ++j) {
				Byte item = model.get(i, j);
				if (item == null) {
					System.out.println("Null cell in [i, j]");
					System.exit(0);
				}
				if (item.intValue() == 1) {
					g.drawImage(imageAi, i * cellWidth + 2, j*cellHeight + 2, this);
				} else {
					if (item.intValue() == 2) {
						g.drawImage(imageHuman, i * cellWidth + 2, j*cellHeight + 2, this);
					}
				}
			}
		}
	}

	private Point p1 = null;
	private Point p2 = null;
	private void drawWinnerLine(TickPoint a, TickPoint b) {
		if (a == null || b == null) {
			return;
		}
		p1 = new Point(a.getX() * (cellWidth - 1) + cellWidth / 2, a.getY() * (cellHeight - 1) + cellHeight / 2);
		p2 = new Point(b.getX() * (cellWidth - 1) + cellWidth / 2, b.getY() * (cellHeight - 1) + cellHeight / 2);
		repaint();
	}
	private void gameOver(Player winner) {
		if (winner == null) {
			return;
		}
		String msg = null;
		switch (winner.ordinal()) {
			case 1: msg = "Robot wins!"; break;
			case 2: msg = "You win!"; break;
			case 3: msg = "Draw!"; break;
			default: msg = "Something went wrong :o";
		}
		try {
			JOptionPane.showMessageDialog(this, msg);
		} catch (HeadlessException e) {
			System.out.println(e.getMessage());
		}
	}

	private class MyMouse extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int c = (int) Math.floor(e.getX() / cellHeight);
			int r = (int) Math.floor(e.getY() / cellWidth);

			Byte item = model.get(c, r);
			if (item == null) {
				System.out.println("Null value in [" + c + "][" + r + "]");
				System.exit(0);
			}
			if (item.intValue() == 0) {
				if (setCell(c, r, (byte) 2)) {
					repaint();

					Player winner = robot.isSomeoneWin();
					if (winner == Player.DRAW) {
						gameOver(winner);
						return;
					}
					if (winner == Player.NONE) {
						TickPoint robotMove = robot.makeAMove();
						if (robotMove == null) {
							System.out.println("Robot can not make a move");
							System.exit(0);
						}

						if (setCell(robotMove.getX(), robotMove.getY(), (byte) 1)) {
							repaint();
						}
						winner = robot.isSomeoneWin();
						if (winner == Player.DRAW || winner != Player.NONE) {
							gameOver(winner);
						}
					} else {
						gameOver(winner);
					}
				}
			}
		}
	}
}