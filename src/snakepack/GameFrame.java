package snakepack;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	GameFrame(){
		this.add(new GamePanel());
		this.setVisible(true);
		this.setLocationRelativeTo(rootPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("SnakeGame");
		this.setResizable(false);
		this.pack();
	}

}
