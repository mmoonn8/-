package pong;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;




public class GameBoard extends JPanel implements KeyListener {
	Ball ball;
	Racquet racquet1;
	Racquet racquet2;
	
	public GameBoard() {
		ball = new Ball(this,Color.red);
		this.setBackground(Color.green);
		racquet1 = new Racquet(this,10,150,Color.blue,1);
		racquet2 = new Racquet(this,560,150,Color.yellow,2);
		setFocusable(true);
		addKeyListener(this);
		}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		racquet1.KeyPressed(e);
		racquet2.KeyPressed(e);
	}
	
	private void move() {
		ball.move();
		racquet1.move();
		racquet2.move();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		racquet1.keyReleased(e);
		racquet2.keyReleased(e);
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ball.draw(g2d);
		racquet1.draw(g2d);
		racquet2.draw(g2d);
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong ����");
		frame.setSize(600,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GameBoard game = new GameBoard();
		frame.add(game);
		
		while(true) {
			game.move();
			game.repaint();
			try {
				Thread.sleep(10);
				
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}