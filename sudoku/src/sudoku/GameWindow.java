package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameWindow extends JPanel implements UI
{
	private static final long serialVersionUID = 4997452162937996928L;
	
	//private boolean tileActive = false;
	private GameBoard gameBoard;
	private Dimension windowSize;

	//private InfoBox infoBox;
//	private Tile[] tiles = new Tile[81];
//	private int highlighted = -1;
//	private int activeID, activeBox, activeRow, activeCol;
//	
//	private Map<Integer, Tile[]> boxes = new HashMap<Integer, Tile[]>();
//	private Map<Integer, Tile[]> rows  = new HashMap<Integer, Tile[]>();
//	private Map<Integer, Tile[]> cols  = new HashMap<Integer, Tile[]>();

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
	

	/*
	GB: Tile indices
	
     ____________________________________________
	|              |              |              | 
	| [ 0][ 1][ 2] | [ 9][10][11] | [18][19][20] | 00
	| [ 3][ 4][ 5] | [12][13][14] | [21][22][23] | 01
	| [ 6][ 7][ 8] | [15][16][17] | [24][25][26] | 02
	|______________|______________|______________|
	|              |              |              |
	| [27][28][29] | [36][37][38] | [45][46][47] | 03
	| [30][31][32] | [39][40][41] | [48][49][50] | 04
	| [33][34][35] | [42][43][44] | [51][52][53] | 05
	|______________|______________|______________|
	|              |              |              |
	| [54][55][56] | [63][64][65] | [72][73][74] | 06
	| [57][58][59] | [66][67][68] | [75][76][77] | 07
	| [60][61][62] | [69][70][71] | [78][79][80] | 08
	|______________|______________|______________|
       00  01  02     03  04  05     06  07  08
       
	 */
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
		{//GB: prevents the mouseListener on the GameWindow from catching a mouseEntered
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
//
//	public void displayInfo(int id, int box, int row, int col)
//	{
//		
//		if(tileActive) return;
//		
//		if(infoBox.getVisible() == false)
//		{
//			gameWindow.add(infoBox);
//			infoBox.setVisible(true);
//		}
//		
//		infoBox.display(id, box, row, col);
//	}

	
	
//	public void setActive(int id, int box, int row, int col)
//	{
//		if(tileActive == true)
//		{
//			this.tileActive = false;
//		}
//		
//		this.displayInfo(id, box, row, col);
//		
//		this.tileActive = true;
//		
//		activeID  = id;
//		activeBox = box;
//		activeRow = row;
//		activeCol = col;
//		
//		tiles[activeID].highlighted = true;
//		
//		gameBoard.secondaryHighlighting();
//	}
	
	////////////////////////////////////////////////////
//	public void setInactive()
//	{
//		for(Tile tile : tiles)
//		{
//			tile.highlighted = false;
//			tile.setBackground(gameBGColor);
//		}
//		this.tileActive = false;
//	}
//	
//	private void secondaryHighlighting()
//	{
//		Color bgOne, bgTwo;
//		if(this.tileActive == true)
//		{
//			bgOne = influenceHighlightOne;
//			bgTwo = influenceHighlightTwo;
//		}
//		else
//		{
//			bgOne = bgTwo = gameBGColor;
//		}
//		
//		for(Tile rTile : rows.get(this.activeRow))
//		{
//			if(rTile.highlighted == false)
//			{
//				rTile.setBackground(bgTwo);
//				rTile.highlighted = true;
//			}
//		}
//		for(Tile cTile : cols.get(this.activeCol))
//		{
//			if(cTile.highlighted == false)
//			{
//				cTile.setBackground(bgTwo);
//				cTile.highlighted = true;
//			}
//		}
//		for(Tile bTile : boxes.get(this.activeBox))
//		{
//			if(bTile.highlighted == false)
//			{
//				bTile.setBackground(bgOne);
//				bTile.highlighted = true;
//			}
//		}
//	}
	/////////////////////////////////////////////////////
	
	
	
	
	
//	private JPanel gameBoardSection(int sectionNumber)
//	{
//		JPanel temp = new JPanel(null);
//		temp.setBackground(bGColor);
//		temp.setSize(92, 92);
//		
//		int id = (sectionNumber * 9);
//		int row = 0;
//		int col = 0;
//		for(int y = 0; y < 3; y++)
//		{
//			
//			int pY = 31 * y;
//			for(int x = 0; x < 3; x++)
//			{
//				int pX = 31 * x;
//				
//				Tile tile = new Tile(id, sectionNumber, col, row);
//				
//				col++;
//				
//				tile.setLocation(pX, pY);
//				
//				tile.addMouseListener(new MouseListener() 
//				{
//					@Override
//					public void mouseClicked(MouseEvent e) 
//					{
//					}
//
//					@Override
//					public void mousePressed(MouseEvent e) 
//					{
//						colorRefresh(tile.id);
//						tile.setBackground(highlightBG);
//						if(tile.highlighted == true && activeID == tile.getID())
//						{
//							
//							tile.highlighted = false;
//							tile.setBackground(gameBGColor);
//
//							setInactive();
//						}
//						else
//						{
//							setInactive();
//							tile.setBackground(highlightBG);
//							tile.highlighted = true;
//
//							
//							setActive(tile.getID(), tile.getBox(), tile.getRow(), tile.getCol());
//						}
//						
//					}
//
//					@Override
//					public void mouseReleased(MouseEvent e) 
//					{
//					}
//
//					@Override
//					public void mouseEntered(MouseEvent e) 
//					{
//						displayInfo(tile.getID(), tile.getBox(), tile.getRow(), tile.getCol());
//						
//						if(tile.highlighted == false)
//						{
//							tile.setBackground(highlightBG);
//						}
//					}
//
//					@Override
//					public void mouseExited(MouseEvent e) 
//					{
//						//hideInfo();
//						if(tile.highlighted == false)
//						{
//							tile.setBackground(gameBGColor);
//						}
//					}
//					
//				});
//				
//				tiles[id++] = tile;
//				
//				temp.add(tile);
//			}
//			row++;
//			col = 0;
//		}
//		return temp;
//	}
	
	public void windowResized(Dimension dim)
	{
		
	}
	
//	public void colorRefresh(int id)
//	{
//		if(highlighted != -1)
//		{
//			tiles[highlighted].setBackground(gameBGColor);
//		}
//		highlighted = id;
//	}
}