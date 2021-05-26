package sudoku;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GameWindow extends JPanel implements UI
{
	private static final long serialVersionUID = 4997452162937996928L;
	
	private GameBoard gameBoard;
	private Dimension windowSize;

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