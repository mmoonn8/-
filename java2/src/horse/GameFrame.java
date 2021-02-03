package horse;

import javax.swing.JButton;
import javax.swing.JFrame;


public class GameFrame extends JFrame {
	GamePanel gp =  new GamePanel();
	public GameFrame() {
		
		setTitle("경마게임"); 
		add(gp); 
		setSize(1200, 800); 
		setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new GameFrame();
	}
}
