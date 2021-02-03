package ball;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MyPanel extends JPanel {
	static final int BOARD_WIDTH = 600;
	static final int BOARD_HEIGHT = 300;
	private Ball ball = new Ball();
	private Ball ball2 = new Ball();
	Thread thread=new Thread();
	Runnable task;
	boolean stop=false;
	

	public MyPanel() {
		JMenu menu = new JMenu("option");
		JMenuItem item[] = new JMenuItem[2];
		MenuActionListener al=new MenuActionListener();
		item[0]=new JMenuItem("stop");
		item[0].addActionListener(al);
		item[1]=new JMenuItem("start");
		item[1].addActionListener(al);
		menu.add(item[0]);
		menu.add(item[1]);
		JMenuBar mb = new JMenuBar();
		mb.add(menu);
		this.add(mb);
		this.setBackground(Color.YELLOW);
		ball2.setColor(Color.BLUE);
		ball2.setSpeed(5, 5);
		task = () -> {
			System.out.println("스레드시작");
			while (!stop) {
				ball.update();
				ball2.update();
				
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException ignore) {
				}
				
			}
			System.out.println("스레드종료");
		};
		thread=new Thread(task);
		thread.start();

	}
	
	
	
	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("stop")) {
				stop=true;

			}else {
				if(stop) {
					stop=false;
					thread=new Thread(task);
					thread.start();
				}
			}
			
		}
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ball.draw(g);
		ball2.draw(g);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(MyPanel.BOARD_WIDTH, MyPanel.BOARD_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(new MyPanel());
	}
}
