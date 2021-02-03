package shooting;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

class MyPanel extends JPanel implements KeyListener {
	Enemy enemy;
	SpaceShip spaceship;
	Missile missile;

	public MyPanel() {
		super();
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);

		enemy = new Enemy("enemy.png");
		spaceship = new SpaceShip("spaceship.png");
		missile = new Missile("missile.png");
		class MyThread extends Thread {
			public void run() {
				while (true) {
					enemy.update();
					spaceship.update();
					missile.update();
					repaint();
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
				}
			}
		}
		Thread t = new MyThread();
		t.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		enemy.draw(g);
		spaceship.draw(g);
		missile.draw(g);
	}

	public void keyPressed(KeyEvent event) {
		spaceship.keyPressed(event);
		missile.keyPressed(event, spaceship.x, spaceship.y);
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
}


