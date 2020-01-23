package sudoku;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
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
public class GameBoard extends JPanel implements UI 
{
	private static final long serialVersionUID = 8666559917665853898L;
	
	private int activeID, activeBox, activeRow, activeCol;
	
	private Map<Integer, Tile[]> boxes = new HashMap<Integer, Tile[]>();
	private Map<Integer, Tile[]> rows  = new HashMap<Integer, Tile[]>();
	private Map<Integer, Tile[]> cols  = new HashMap<Integer, Tile[]>();
	
	private int highlighted = -1;
	private Tile[] tiles = new Tile[81];

	public boolean tileActive = false; 
	
	public GameBoard()//JPanel gameWindow)
	{
		super(null);
		//this.gameWindow = gameWindow;


		this.setSize(280, 280);
		this.setBackground(menuBGColor);
		
		this.setLocation(2, 2);
		

		for(int y = 0; y < 3; y++)
		{
			int pY = 94 * y;
			for(int x = 0; x < 3; x++)
			{
				int pX = 94 * x;
				JPanel temp = this.gameBoardSection(x + (y * 3));
				
				temp.setLocation(pX, pY);
				
				this.add(temp);
			}
		}
		
		for(int i = 0; i < 9; i++)
		{
			Tile[] row = new Tile[9];
			Tile[] col = new Tile[9];
			Tile[] box = new Tile[9];
			
			this.rows.put(i, row);
			this.cols.put(i, col);
			this.boxes.put(i, box);
		}
		
		int test = 0;
		for(Tile tile : tiles)
		{
			if(test < 5)
			{
				System.out.println(tile.getID());
				test++;
			}
			
			int rowNum = tile.getRow();
			int colNum = tile.getCol();
			int boxNum = tile.getBox();
			
			int boxRow = tile.getBlockSubRow();
			int boxCol = tile.getBlockSubCol();
			
			Tile[] row = this.rows.get(rowNum);
			row[colNum] = tile;
			this.rows.put(rowNum, row);
			
			Tile[] col = this.cols.get(colNum);
			col[rowNum] = tile;
			this.cols.put(colNum, col);
			
			Tile[] box = this.boxes.get(boxNum);
			box[(boxRow * 3) + boxCol] = tile;
		}
		
		generateSolutions();
	}
	
	private void generateSolutions()
	{
		for(Tile tile : tiles)
		{
			int rowNum = tile.getRow();
			int colNum = tile.getCol();
			int boxNum = tile.getBox();
			
			Tile[] row = this.rows.get(rowNum);
			Tile[] col = this.cols.get(colNum);
			Tile[] box = this.boxes.get(boxNum);
			
			for(int i = 0; i < 9; i++)
			{
				int num = row[i].getSolution();
				if(num != -1) tile.removePossible(num);
				num = col[i].getSolution();
				if(num != -1) tile.removePossible(num);
				num = box[i].getSolution();
				if(num != -1) tile.removePossible(num);
			}
			
			boolean[] possibles = tile.getPossibles();
			
			for(int i = 1; i < 10; i++)
			{
				if(possibles[i] == true) 
				{
					tile.assignSolution(i);
					break;
				}
			}
			
		}
	}
	
	public void setActive(int id, int box, int row, int col)
	{
		if(tileActive == true)
		{
			this.tileActive = false;
		}
		
		this.tileActive = true;
		
		activeID  = id;
		activeBox = box;
		activeRow = row;
		activeCol = col;
		
		tiles[activeID].highlighted = true;
		
		secondaryHighlighting();
	}

	public void secondaryHighlighting()
	{
		Color bgOne, bgTwo;
		if(this.tileActive == true)
		{
			bgOne = influenceHighlightOne;
			bgTwo = influenceHighlightTwo;
		}
		else
		{
			bgOne = bgTwo = gameBGColor;
		}
		
		for(Tile rTile : rows.get(this.activeRow))
		{
			if(rTile.highlighted == false)
			{
				rTile.setBackground(bgTwo);
				rTile.highlighted = true;
			}
		}
		for(Tile cTile : cols.get(this.activeCol))
		{
			if(cTile.highlighted == false)
			{
				cTile.setBackground(bgTwo);
				cTile.highlighted = true;
			}
		}
		for(Tile bTile : boxes.get(this.activeBox))
		{
			if(bTile.highlighted == false)
			{
				bTile.setBackground(bgOne);
				bTile.highlighted = true;
			}
		}
	}
	
	private JPanel gameBoardSection(int sectionNumber)
	{
		JPanel temp = new JPanel(null);
		temp.setBackground(bGColor);
		temp.setSize(92, 92);
		
		int id = (sectionNumber * 9);
		int row = 0;
		int col = 0;
		for(int y = 0; y < 3; y++)
		{
			
			int pY = 31 * y;
			for(int x = 0; x < 3; x++)
			{
				int pX = 31 * x;
				
				Tile tile = new Tile(id, sectionNumber, col, row);
				
				col++;
				
				tile.setLocation(pX, pY);
				
				tile.addMouseListener(new MouseListener() 
				{
					@Override
					public void mouseClicked(MouseEvent e) 
					{
					}

					@Override
					public void mousePressed(MouseEvent e) 
					{
						colorRefresh(tile.id);
						tile.setBackground(highlightBG);
						if(tile.highlighted == true && activeID == tile.getID())
						{
							
							tile.highlighted = false;
							tile.setBackground(gameBGColor);

							setInactive();
						}
						else
						{
							setInactive();
							tile.setBackground(highlightBG);
							tile.highlighted = true;

							
							setActive(tile.getID(), tile.getBox(), tile.getRow(), tile.getCol());
						}
						
					}

					@Override
					public void mouseReleased(MouseEvent e) 
					{
					}

					@Override
					public void mouseEntered(MouseEvent e) 
					{
						if(tile.highlighted == false)
						{
							tile.setBackground(highlightBG);
						}
					}

					@Override
					public void mouseExited(MouseEvent e) 
					{
						if(tile.highlighted == false)
						{
							tile.setBackground(gameBGColor);
						}
					}
					
				});
				
				tiles[id++] = tile;
				
				temp.add(tile);
			}
			row++;
			col = 0;
		}
		return temp;
	}

	
	public void setInactive()
	{
		for(Tile tile : tiles)
		{
			tile.highlighted = false;
			tile.setBackground(gameBGColor);
		}
		this.tileActive = false;
	}
	
	public void colorRefresh(int id)
	{
		if(highlighted != -1)
		{
			tiles[highlighted].setBackground(gameBGColor);
		}
		highlighted = id;
	}
}
