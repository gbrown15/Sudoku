package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JPanel implements UI
{
	private static final long serialVersionUID = 4997452162937996928L;
	
	private GameBoard gameBoard;
	private Dimension windowSize;

	JLabel message = new JLabel();

	public GameWindow(Dimension dim)
	{
		super(null);
		
		setUp(dim);
	}
	
	private void setUp(Dimension dim)
	{
		this.windowSize = dim;
		this.setSize(windowSize);
		this.setBackground(gameBGColor);
		this.setLocation(0, 0);
		this.addButtons();
		
		Font label = new Font("message", 1, 50);
		
		Dimension mid = new Dimension(300, 100);
		Point point = new Point(150, 25);
		
		message.setSize(mid);
		message.setLocation(point);
		message.setFont(label);
		message.setForeground(highlightBG);
		
		this.add(message);
	}
	
	private void displaySolvedStatus()
	{
		this.message.setText((gameBoard.isSolved()) ? "SOLVED!" : "WRONG!");
		
		/**/
	}
	
	private void addButtons()
	{
		JButton checkPuzzle = makeButton("Check", 0);
		this.add(checkPuzzle);
		checkPuzzle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(gameBoard.isSolved());
				
				displaySolvedStatus();
				
			}
		});
		
		JButton test2 = makeButton("Test 2", windowSize.width/4);
		this.add(test2);
		test2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				message.setText("");
			}
		});
		
		JButton test3 = makeButton("Test 3", (windowSize.width/4)*2);
		this.add(test3);
		JButton test4 = makeButton("Test 4", (windowSize.width/4)*3);
		this.add(test4);
	}
	
	private JButton makeButton(String name, int x)
	{
		JButton temp = new JButton(name);
		
		temp.setBackground(new Color(40, 40, 40));
		temp.setForeground(new Color(215, 215, 215));
		temp.setFocusPainted(false);
		temp.setBorderPainted(false);
		
		temp.setSize(windowSize.width/4, 25);
		temp.setLocation(x, windowSize.height - 25);
		
		return temp;
	}
	
	public void addGameBoard()
	{
		gameBoard = new GameBoard();
		
		
		JPanel boardFrame = new JPanel(null);
		Dimension gSize = new Dimension(284, 284);
		boardFrame.setSize(gSize);
		boardFrame.setBackground(menuBGColor);
		
		int x1 = (windowSize.width / 2) - (gSize.width / 2);
		int y1 = (windowSize.height / 2) - (gSize.height / 2);
		boardFrame.setLocation(x1, y1);
		
		boardFrame.addMouseListener(new MouseListener() 
		{
			//GB: prevents the mouseListener on the GameWindow from catching a mouseEntered
			//    event when moving between tiles within the boardFrame
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			}
			@Override
			public void mousePressed(MouseEvent e) 
			{
			}
			@Override
			public void mouseReleased(MouseEvent e) 
			{
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
			}
		});
		
		boardFrame.add(gameBoard);
		
		this.add(boardFrame);
	}
	
	public void windowResized(Dimension dim)
	{
		
	}
}