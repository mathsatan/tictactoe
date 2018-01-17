package com.kruchkov.di;

import com.kruchkov.ai.Robot;
import com.kruchkov.ai.Result;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyDIApplication extends FieldGui {
	private int cellWidth = 30;
	private int cellHeight = 30;
	private Field model = null;
	private Robot robot = null;

	private static final Logger log = Logger.getLogger(MyDIApplication.class);
	static {
		PropertyConfigurator.configure("log4j.properties");
	}

	private static BufferedImage imageHuman = null;
	private static BufferedImage imageAi = null;

	private int rows = 10;
	private int cols = 10;


	private Point p1 = null;
	private Point p2 = null;

	private GuiFunction gui = null;
	private interface GuiFunction {
		void func(Graphics g);
	}

	public boolean setCell(int i, int j, Byte value) {
		if (value == null || i >= rows || j >= cols || i < 0 || j < 0) {
			log.warn("Cell setter fails with invalid arguments");
			return false;
		}
		return model.set(i, j, value);
	}

	protected void startNewGame() {
		this.model.reset();
		this.p1 = this.p2 = null;
		this.repaint();
		log.info("New game started");
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
			imageHuman = ImageIO.read(new File("res/image/player_1.png"));
			imageAi = ImageIO.read(new File("res/image/player_ai.png"));
		} catch (IOException e) {
			log.error("Image not found! Exception: " + e.getMessage());
			System.exit(0);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		this.updateMatrix.func(g);

		if (gui != null) {
			gui.func(g);
			gui = null;
		}
	}
	private GuiFunction drawWinnerLine = (Graphics g) -> {
		if (p1 != null && p2 != null) {
			g.setColor(Color.red);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
			g2.setStroke(new BasicStroke(1));
			g.setColor(Color.black);
		}
	};

	private GuiFunction updateMatrix = (Graphics g) -> {
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
					log.error("Null cell in [i, j]");
					System.exit(0);
				}

				if (item.intValue() == 1) {
					g.drawImage(imageAi, i * cellWidth + (cellWidth - imageAi.getWidth()) / 2, j * cellHeight + (cellHeight - imageAi.getHeight()) / 2, MyDIApplication.this);
				} else {
					if (item.intValue() == 2) {
						g.drawImage(imageHuman, i * cellWidth + (cellWidth - imageHuman.getWidth()) / 2, j * cellHeight + (cellHeight - imageHuman.getHeight()) / 2, MyDIApplication.this);
					}
				}
			}
		}
	};

	private void drawWinnerLine(TicPoint a, TicPoint b) {
		if (a == null || b == null) {
			return;
		}
		p1 = new Point(a.getX() * cellWidth + cellWidth / 2, a.getY() * cellHeight + cellHeight / 2);
		p2 = new Point(b.getX() * cellWidth + cellWidth / 2, b.getY() * cellHeight + cellHeight / 2);
		this.gui = this.drawWinnerLine;
		this.repaint();
	}

	private void gameOver(Result result) {
		if (result == null) {
			return;
		}

		Player winner = result.getPlayer();

		String msg = "";
		switch (winner.ordinal()) {
			case 1: msg = "Robot wins!"; break;
			case 2: msg = "You win!"; break;
			case 3: msg = "Draw!"; break;
			default: msg = "Something went wrong :o";
		}
		if (winner.ordinal() == 1 || winner.ordinal() == 2) {
			drawWinnerLine(result.getArrPoint(0), result.getArrPoint(4));
		}
		try {
			JOptionPane.showMessageDialog(this, msg);
			startNewGame();
		} catch (HeadlessException e) {
			log.error(e.getMessage());
		}
	}

	private class MyMouse extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int c = (int) Math.floor(e.getX() / cellHeight);
			int r = (int) Math.floor(e.getY() / cellWidth);

			Byte item = model.get(c, r);
			if (item == null) {
				log.error("Null value in [" + c + "][" + r + "]");
				System.exit(0);
			}
			if (item.intValue() == 0) {
				if (setCell(c, r, (byte) 2)) {
					repaint();

					Result res = robot.isSomeoneWin();
					Player winner = res.getPlayer();

					if (winner == Player.DRAW) {
						gameOver(res);
						return;
					}
					if (winner == Player.NONE) {
						TicPoint robotMove = robot.makeAMove();
						if (robotMove == null) {
							log.error("Robot can not make a move");
							System.exit(0);
						}

						if (setCell(robotMove.getX(), robotMove.getY(), (byte) 1)) {
							repaint();
						}
						res = robot.isSomeoneWin();
						winner = res.getPlayer();

						if (winner == Player.DRAW || winner != Player.NONE) {
							gameOver(res);
						}
					} else {
						gameOver(res);
					}
				}
			}
		}
	}
}