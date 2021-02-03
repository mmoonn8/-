package ball;

import java.awt.*;
import javax.swing.*;

class Ball {

	private int x = 100;
	private int y = 100;
	private int size = 30;
	private int xSpeed = 10;
	private int ySpeed = 10;
	private Color color = Color.RED;

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, size, size);
	}
	
	public void setSpeed(int xSpeed,int ySpeed) {
		this.xSpeed=xSpeed;
		this.ySpeed=ySpeed;
	}
	public void setColor(Color color) {
		this.color=color;
	}

	public void update() {
		x += xSpeed;
		y += ySpeed;
		if ((x + size) > MyPanel.BOARD_WIDTH - size || x < 0) {
			xSpeed =-xSpeed;
		}
		if ((y + size) > MyPanel.BOARD_HEIGHT - size || y < 0) {
			ySpeed =-ySpeed;
		}
	}
}
