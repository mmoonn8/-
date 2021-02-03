package horse;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



public class GamePanel extends JPanel {
	Thread thread=new Thread();
	boolean stop=false;
	Horse[] horse=new Horse[5];
	boolean start=false;
	int cnt=0;
	int rank=0;
	int betHorseRank=0;
	int money=100000;
	JTextArea gameLog = new JTextArea();
	
	public GamePanel() {
		
		this.setLayout(null);
		horseReset();
		JScrollPane sc = new JScrollPane(gameLog);   
		gameLog.setLineWrap(true);
		sc.setBounds(500, 630, 500, 100);
		this.add(sc);
		
		JButton startBtn = new JButton("시작");
		JButton resetBtn = new JButton("초기화");
		startBtn.setBounds(50, 630, 90, 20);
		resetBtn.setBounds(50, 650, 90, 20);
		
		ButtonGroup  group = new ButtonGroup();
		JRadioButton[] horseName = new JRadioButton[horse.length];
		for(int i=0;i<horse.length;i++) {
			horseName[i]=new JRadioButton(horse[i].getHName());
			horseName[i].setBounds(horse[i].getX()+10, horse[i].getY()+90, 90, 20);
			group.add(horseName[i]);
			this.add(horseName[i]);
		}
		
		JLabel pMoney=new JLabel("돈 : "+money);
		JTextField bet = new JTextField("배팅금액 입력");
		bet.setBounds(150, 650, 200, 20);
		pMoney.setBounds(150, 630, 200, 20);
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(!bet.getText().matches("^[0-9]+$")) {
					addLog("배팅할 금액을 정확하게 입력해주세요.");
					return;
				}
				
				group.getElements();
				int betMoney=Integer.parseInt(bet.getText());
				if(betMoney>money) {
					addLog("현재 가진 금액보다 높은 금액을 입력하실 수 없습니다.");
					return;
				}
				
				if(betMoney<1) {
					addLog("마이너스나 0을 입력하실 수 없습니다.");
					return;
				}
				boolean check=false;
				for(int i=0;i<horseName.length;i++) {
					if(horseName[i].isSelected()) {
						check=true;
						break;
					}
				}
				if(!check) {
					addLog("베팅할 말을 선택해주세요.");
					return;
				}
				
				money=money-betMoney;
				pMoney.setText("돈 : "+money);
				addLog("경기가 시작되었습니다.");
				start=true;
				startBtn.setEnabled(false);
				resetBtn.setEnabled(false);
				for(int i=0;i<horse.length;i++) {
					horse[i].start();
					
				}
				thread = new Thread( new Runnable() {
					  public void run() {
						  while(start) {
							  repaint();
							  for(int i=0;i<horse.length;i++) {
								  if(horse[i].goal&&horse[i].getRank()==0) {
									  rank=rank+1;
									  horse[i].setRank(rank);
									  if(horseName[i].isSelected()) {
										  betHorseRank=rank;
									  }
									  addLog(horse[i].getHName()+"이 "+rank+"등으로 들어왔습니다!");
								  }
							  }
							  if(rank==5) {
								  addLog("경기종료. 당신이 배팅한 말이 "+betHorseRank+"등으로 들어왔습니다.");
								  if(betHorseRank==1) {
									  addLog("축하합니다! 당신이 배팅한말이 1등으로 들어왔으므로 배팅한 금액의 2배 액수가 지급됩니다.");
									  money=money+(betMoney*2);
								  }else if(betHorseRank==2) {
									  addLog("축하합니다! 당신이 배팅한말이 2등으로 들어왔으므로 배팅한 금액의 1.5배 액수가 지급됩니다.");
									  money=money+(int)(betMoney*1.5);
								  }else if(betHorseRank==3) {
									  addLog("당신이 배팅한말이 3등으로 들어왔으므로 돈을 돌려드립니다.");
									  money=money+betMoney;
								  }else {
									  addLog("아쉽게도 당신이 배팅한말이 순위권에 들지 못하였습니다...");
								  }
								  pMoney.setText("돈 : "+money);
								  start=false;
								  resetBtn.setEnabled(true);
								  rank=0;
								  betHorseRank=0;
								  break;
								  }
						  }
					  }
					});
				thread.start();
				
			}
		});
		
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				horseReset();
				startBtn.setEnabled(true);
			}
		}
			
		);

		this.add(pMoney);
		this.add(startBtn);
		this.add(resetBtn);
		this.add(bet);
		
		
		
	}
	void addLog(String logText){

		gameLog.append(logText + "\n");  
		gameLog.setCaretPosition(gameLog.getDocument().getLength()); 
		}

	public void horseReset() {
		for(int i=0;i<5;i++) {
			horse[i]=new Horse((i+1)+"번말","horse"+(i+1)+".png");
			horse[i].setX(50);
			horse[i].setY(i*120+10);
			horse[i].setSpeed((int) (Math.random() * 7)+1);
			System.out.println(horse[i].getHName()+"/"+horse[i].getX()+","+horse[i].getY());
			repaint();
		}
		addLog("배팅하고 싶은 말을 선택 후 배팅금액을 입력해주세요.");
		addLog("1등:2배 / 2등:1.5배 / 3등:원금");
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		for(int i=0;i<5;i++) {
			horse[i].draw(g);
		}
	}
	
}

