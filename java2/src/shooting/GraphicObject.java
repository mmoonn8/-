package shooting;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

class GraphicObject {
	BufferedImage img = null;
	int x = 0, y = 0;

	public GraphicObject(String name) {
		try {
			img = ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	public void update() {

	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	public void keyPressed(KeyEvent event) {
	}
}

class Missile extends GraphicObject {

	boolean launched = false;

	public Missile(String name) {
		super(name);
		y = -200;
	}

	public void update() {

		if (launched)
			y -= 1;
		if (y < -100)
			launched = false;

	}

	public void keyPressed(KeyEvent event, int x, int y) {
		if (event.getKeyCode() == KeyEvent.VK_SPACE) {

			launched = true;
			this.x = x;
			this.y = y;
		}
	}

}

class Enemy extends GraphicObject {
	int dx = -10;

	public Enemy(String name) {
		super(name);
		x = 500;
		y = 0;
	}

	public void update() {
		x += dx;
		if (x < 0)
			dx = +10;
		if (x > 500)
			dx = -10;
	}
}

class SpaceShip extends GraphicObject {
	public SpaceShip(String name) {
		super(name);
		x = 150;
		y = 350;
	}

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			x -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			x += 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			y -= 10;
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			y += 10;
		}
	}
}
