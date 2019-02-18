import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon titleimg;
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakebody;
	private ImageIcon enemyimg;
	
	private int[]snakexlength=new int[750];
	private int []snakeylength=new int[750];
	private int[] enemyxpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
			475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyypos={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
			475,500,525,550,575};
	
	private Random rand=new Random();
	private int xpos=rand.nextInt(34);
	private int ypos=rand.nextInt(21);
	
	private boolean right=false;
	private boolean left=false;
	private boolean up=false;
	private boolean down=false;
	
	private Timer timer;
	private int delay=100;
	
	private int moves=0;
	private int snakelength=3;
	private int score=0;
	
	public GamePlay()
	{
		System.out.println(xpos+" "+ypos);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer =new Timer(delay,this);
		timer.start();
		
	}
	
	public void paint(Graphics g)
	{
		if(moves==0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;
			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
			
		}
		
		
		//title image
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		titleimg=new ImageIcon("snaketitle.jpg");
		titleimg.paintIcon(this, g, 25, 11);
		
	//set border
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
	//set bg for player
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
		
		for(int a=0;a<snakelength;a++)
		{
			if(a==0&&right)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0&&left)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0&&up)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a==0&&down)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
			if(a!=0)
			{
				snakebody=new ImageIcon("snakeimage.png");
				snakebody.paintIcon(this, g, snakexlength[a], snakeylength[a]);
			}
		}
		enemyimg=new ImageIcon("enemy.png");
		if(enemyxpos[xpos]==snakexlength[0]&&enemyypos[ypos]==snakeylength[0])
		{
			score+=3;
			snakelength++;
			xpos=rand.nextInt(34);
			ypos=rand.nextInt(21);
			System.out.println(xpos+" "+ypos);
		}
		if(enemyypos[ypos]<575&&enemyypos[ypos]>75)
		enemyimg.paintIcon(this, g, enemyxpos[xpos], enemyypos[ypos]);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Score : "+score, 780, 30);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.PLAIN,14));
		g.drawString("Length : "+snakelength, 780, 50);
		
		for(int a=1;a<snakelength;a++)
		{
			if(snakexlength[a]==snakexlength[0]&&snakeylength[a]==snakeylength[0])
			{
				right=left=up=down=false;
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.PLAIN,50));
				g.drawString("Game Over  ", 300, 300);
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial",Font.PLAIN,20));
				g.drawString("Press Space to restart  ", 350, 340);
			}
		}
		
		
		g.dispose();
	}

	public void actionPerformed(ActionEvent arg0) {
		
		timer.start();
		
		if(right)
		{
			for(int r=snakelength-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			
			for(int r=snakelength;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]+25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]>850)
				{
					snakexlength[r]=25;
				}
			}
			repaint();
			
		}
		if(left)
		{
			for(int r=snakelength-1;r>=0;r--)
			{
				snakeylength[r+1]=snakeylength[r];
			}
			
			for(int r=snakelength;r>=0;r--)
			{
				if(r==0)
				{
					snakexlength[r]=snakexlength[r]-25;
				}
				else
				{
					snakexlength[r]=snakexlength[r-1];
				}
				if(snakexlength[r]<25)
				{
					snakexlength[r]=850;
				}
			}
			repaint();
			
		}
		if(down)
		{
			for(int r=snakelength-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			
			for(int r=snakelength;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]+25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]>625)
				{
					snakeylength[r]=75;
				}
			}
			repaint();
			
		}
		if(up)
		{
			for(int r=snakelength-1;r>=0;r--)
			{
				snakexlength[r+1]=snakexlength[r];
			}
			
			for(int r=snakelength;r>=0;r--)
			{
				if(r==0)
				{
					snakeylength[r]=snakeylength[r]-25;
				}
				else
				{
					snakeylength[r]=snakeylength[r-1];
				}
				if(snakeylength[r]<75)
				{
					snakeylength[r]=625;
				}
			}
			repaint();
			
		}
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			snakelength=3;
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			right=true;
			if(!left)
			{
				right=true;
			}
			else
			{
				right=false;
				left=true;
			}
			up=down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			left=true;
			if(!right)
			{
				left=true;
			}
			else
			{
				left=false;
				right=true;
			}
			up=down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			up=true;
			if(!down)
			{
				up=true;
			}
			else
			{
				up=false;
				down=true;
			}
			right=left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			down=true;
			if(!up)
			{
				down=true;
			}
			else
			{
				down=false;
				up=true;
			}
			left=right=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
