package shooting;

import javax.swing.JFrame;

public class MyFrame extends JFrame {
	public MyFrame() {
		setTitle("My Game"); 
		add(new MyPanel()); 
		setSize(500, 500); 
		setVisible(true);
	}
	public static void main(String[] args) {
		new MyFrame();
	}
}
