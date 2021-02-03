package horse;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Horse extends Thread {
		BufferedImage img = null;
	 	private String name;
	    private int rank = 0;
	    private int speed=3;
	    private int x=0;
	    private int y=0;
	    public volatile boolean goal = false; 
	    
	    public Horse(String name,String img) {
	    	this.name=name;
	    	try {
				this.img = ImageIO.read(new File(img));
			} catch (IOException e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
	    }
	    
	    public void run() {
			  System.out.println(name+ "출발");
				while(true) {
					if(this.update()) {
						break;
					}
					
					try {
						Thread.sleep(10 * (int) (Math.random() * (3+speed*2)));
					} catch (InterruptedException ignore) {
						System.out.println("스레드종료");
					}
			  }
	    }
	    
	    public boolean update() {
	    	x=x+speed;
	    	int critical=1 * (int) (Math.random() * 10);
	    	if(critical>speed) {
	    		x=x+speed*2;
	    	}
	    	if(x>=1000) {
	    		x=1000;
	    		goal=true;
	    		return true;
	    	}else {
	    		return false;
	    	}
	    	
	    }
	    
		public void draw(Graphics g) {
			g.drawImage(img, x, y, null);
		}
	    
	    public String getHName() {
	    	return name;
	    }
	    
	    public boolean isGoal() {
	    	return goal;
	    }
	    
	    public void setSpeed(int speed) {
	    	this.speed=speed;
	    }
	    public void setGoal(boolean goal) {
	    	this.goal=goal;
	    }
	    public int getRank() {
	    	return rank;
	    }
	    public void setRank(int rank) {
	    	this.rank=rank;
	    }
	    public void setX(int x) {
	    	this.x=x;
	    }
	    public void setY(int y) {
	    	this.y=y;
	    }
	    public int getX() {
	    	return x;
	    }
	    public int getY() {
	    	return y;
	    }
	    
	    

	    
}
