import java.awt.Color;

import javax.swing.JFrame;

public class Main {
	
	JFrame frame; 
	public Main()
	{
		frame = new JFrame();
		GamePlay game=new GamePlay();
		frame.setTitle("MySnake");
		frame.setBounds(10,10,905,700);
		frame.setBackground(Color.DARK_GRAY);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
	}
	

	public static void main(String[] args) {
		 new Main();
	}

}
