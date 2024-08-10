package snakepack;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	// Configurações gerais do game
	//private final int SCREEN_WIDTH = 1300;
	//private final int SCREEN_HEIGHT = 750;
	//private final int GAME_BLOCK_DIM = SCREEN_WIDTH/SCREEN_HEIGHT;
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int GAME_BLOCK_DIM = 25;
	static final int GAME_BLOCKS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(GAME_BLOCK_DIM*GAME_BLOCK_DIM);
	static final int GAME_DELAY = 75;
	static final Color SNAKE_HEAD_COLOR = Color.GREEN;
	static final Color SNAKE_BODY_COLOR = Color.GREEN;
	static final Color APPLE_COLOR = Color.RED;
	int snakeSize = 6;
	int xAxisBodyParts[];
	int yAxisBodyParts[];
	int applesScore;
	int appleXAxis;
	int appleYAxis;
	private char direction = 'D';
	int userInput;
	boolean runningProcess;
	Timer timer;
	private Random rand;
	
	
	//Construtor do JPanel
	GamePanel(){
		rand = new Random();
		runningProcess = false;
		//direction = 'D';
		userInput = KeyEvent.VK_RIGHT;
		xAxisBodyParts = new int[GAME_BLOCKS];
		yAxisBodyParts = new int[GAME_BLOCKS];
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		setFocusable(true);
		addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		runningProcess = true;
		newApple();
		timer = new Timer(GAME_DELAY,this);
		timer.start();
		
	}
	
	public void newApple() {
		appleXAxis = (int)(rand.nextInt(SCREEN_WIDTH/GAME_BLOCK_DIM) * GAME_BLOCK_DIM);
		appleYAxis = (int)(rand.nextInt(SCREEN_HEIGHT/GAME_BLOCK_DIM) * GAME_BLOCK_DIM);
	}
	
	public void showScore(Graphics g) {
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesScore, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesScore))/2, g.getFont().getSize());
	}
	
	public void move() {
		for(int i = snakeSize;i > 0;i--) {
			xAxisBodyParts[i] = xAxisBodyParts[i-1];
			yAxisBodyParts[i] = yAxisBodyParts[i-1];
		}
		
		//xAxisBodyParts[0] += GAME_BLOCK_DIM;
		/*
		switch(userInput) {
			case KeyEvent.VK_RIGHT:
				if(xAxisBodyParts[1] < xAxisBodyParts[0] && yAxisBodyParts[1] != yAxisBodyParts[0]) {
					//xAxisBodyParts[0] = xAxisBodyParts[0] + GAME_BLOCK_DIM;
					System.out.println("KKKK");
				}
				break;
			case KeyEvent.VK_LEFT:
				if(xAxisBodyParts[1] > xAxisBodyParts[0] && yAxisBodyParts[1] != yAxisBodyParts[0]) {
					//xAxisBodyParts[0] = xAxisBodyParts[0] - GAME_BLOCK_DIM;
					System.out.println("KKKK");
				}
				break;
			case KeyEvent.VK_DOWN:
				if(yAxisBodyParts[1] < yAxisBodyParts[0] && xAxisBodyParts[1] != xAxisBodyParts[0]) {
					//yAxisBodyParts[0] = yAxisBodyParts[0] + GAME_BLOCK_DIM;
					System.out.println("KKKK");
				}
				break;
			case KeyEvent.VK_UP:
				if(yAxisBodyParts[1] > yAxisBodyParts[0] && xAxisBodyParts[1] != xAxisBodyParts[0]) {
					//yAxisBodyParts[0] = yAxisBodyParts[0] - GAME_BLOCK_DIM;
					System.out.println("KKKK");
				}
				break;
			default:
				System.out.println("!!!!!!!!!");
				break;
			
			}
		*/
		System.out.println(userInput);
			
			//System.out.println(userInput);
			 
		
		switch(direction) {
		case 'U':
			yAxisBodyParts[0] = yAxisBodyParts[0] - GAME_BLOCK_DIM;
			break;
		case 'D':
			yAxisBodyParts[0] = yAxisBodyParts[0] + GAME_BLOCK_DIM;
			break;
		case 'R':
			xAxisBodyParts[0] = xAxisBodyParts[0] + GAME_BLOCK_DIM;
			break;
		case 'L':
			xAxisBodyParts[0] = xAxisBodyParts[0] - GAME_BLOCK_DIM;
			break;
		}
		
	}
	
	public void checkColision() {
		
		for (int i = 1; i < snakeSize; i++) {
			if(xAxisBodyParts[i] == xAxisBodyParts[0] && yAxisBodyParts[i] == yAxisBodyParts[0]) {
				runningProcess = false;
			
			}
		}
			
			if(xAxisBodyParts[0] < 0) {
				runningProcess = false;
			
			}
			if(xAxisBodyParts[0] > SCREEN_WIDTH) {
				runningProcess = false;
				
			}
			if(yAxisBodyParts[0] < 0) {
				runningProcess = false;
				
			}
			if(yAxisBodyParts[0] > SCREEN_HEIGHT) {
				runningProcess = false;
				
			}
		}
		
	
	
	public void eatApple() {
		if(xAxisBodyParts[0] == appleXAxis && yAxisBodyParts[0] == appleYAxis) {
			applesScore++;
			snakeSize++;
			newApple();
		}
		
	}
	
	public void gameOver(Graphics g) {
		showScore(g);
		//Game Over text
		g.setColor(Color.red);
		g.setFont( new Font("Ink Free",Font.BOLD, 75));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		draw(g);
		
	}
	
	public void draw(Graphics g) {
		if(runningProcess) {
		showScore(g);
		/*	
		for(int i = 0; i < SCREEN_WIDTH;i += GAME_BLOCK_DIM) {
			g.drawLine(0, i, SCREEN_WIDTH, i);
			g.drawLine(i, 0, i, SCREEN_HEIGHT);
		}*/
		
		//Desenhando a maçã
		g.setColor(APPLE_COLOR);
		g.fillOval(appleXAxis, appleYAxis, GAME_BLOCK_DIM, GAME_BLOCK_DIM);
		
		//Desenhando a cobra
		for(int i = 0; i < snakeSize;i++) {
			if(i == 0) 
				g.setColor(Color.green);
			else 
				g.setColor(new Color(45,180,0));
				g.fillRect(xAxisBodyParts[i], yAxisBodyParts[i], GAME_BLOCK_DIM, GAME_BLOCK_DIM);
			}			
	    //System.out.println(direction);
		//System.out.println("x:" + xAxisBodyParts[0] + "y:" + yAxisBodyParts[0]);
	}else {
		gameOver(g);
	}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(runningProcess) {
		move();
		checkColision();
		eatApple();
		}
		
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
			
			
			//System.out.println(userInput);
		}
	
	

}
}
