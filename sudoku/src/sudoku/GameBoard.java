package sudoku;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import java.util.Vector;

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
	
	Color backgroundHolder;
	
	
	public GameBoard()
	{
		super(null);

		this.setSize(280, 280);
		this.setBackground(menuBGColor);
		
		this.setLocation(2, 2);
		
		this.generatePlayArea();
		
		generateSolutions();
	}
	
	private void generatePlayArea()
	{
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
		
		initTiles();
	}
	
	private void initTiles()
	{
	    for(int i = 0; i < 9; i++)
        {
            Tile[] row = new Tile[9];
            Tile[] col = new Tile[9];
            Tile[] box = new Tile[9];
            
            this.rows.put(i, row);
            this.cols.put(i, col);
            this.boxes.put(i, box);
        }
        
        for(Tile tile : tiles)
        {
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
            this.boxes.put(boxNum, box);
        }
	}

	private void resetTiles()
	{
	    for (int i = 0; i < 81; i++)
	    {
	        Tile t = tiles[i];
	        t.reset();
	        tiles[i] = t;
	    }
	
	}
	
	private JPanel gameBoardSection(int sectionNumber)
	{
		JPanel temp = new JPanel(null);
		temp.setBackground(bGColor);
		temp.setSize(92, 92);
		
		int id = (sectionNumber * 9);
		for(int row = 0; row < 3; row++)
		{
			int pY = 31 * row;
			for(int col = 0; col < 3; col++)
			{
				Tile tile = new Tile(id, sectionNumber, col, row);
				
                int pX = 31 * col;
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
					    //backgroundHolder = tile.getBackground();
                        //tile.setBackground(highlightBG);
					}

					@Override
					public void mouseExited(MouseEvent e) 
					{
						if(tile.highlighted == false)
						{
							tile.setBackground(gameBGColor);
						}
					    
					    //tile.setBackground(backgroundHolder);
					}
					
				});
				
				tiles[id++] = tile;
				temp.add(tile);
			}
		}
		return temp;
	}
	
	private void generateSolutions()
	{
		for(int i = 0; i < 81; i++)
		{
		    Tile current = tiles[idOfLeastPossibleSolutions()]; 
		    Vector<Integer> possibles = current.getPossibles();
            if (possibles.size() != 0)
            {
                int rand = (int)(Math.random() * possibles.size());
                int randSolution = (int)possibles.get(rand);
                current.assignSolution(randSolution);
                updatePossibles(randSolution, current.getRow(), 
                        current.getCol(), current.getBox());
            }
            else
            {
                //System.out.println(current.getID());
                
                //GB: This method of creating the solution occasionally 
                //    fails, this is handled by resetting the puzzle and
                //    starting again.
                resetTiles();
                i = -1;
            }
		}
	}
	
	private int idOfLeastPossibleSolutions()
	{
	    int leastSolutions = 9999;
	    int target = 0;
	    for(int i = 0; i < 81; i++)
	    {
	        Tile current = tiles[i];
	        if (current.solution == -1)
	        {
	            int numSolutions = current.getPossibles().size();
	            if (numSolutions < leastSolutions)
	            {
	                target = i;
	                leastSolutions = numSolutions;
	            }
	        }
	    }
	    return target;
	}
	
	//GB: updates the possible fields for every tile in the specified row, col, and box.
	private void updatePossibles(int num, int rowNum, int colNum, int boxNum)
	{
		Tile[] row = this.rows.get(rowNum);
		Tile[] col = this.cols.get(colNum);
		Tile[] box = this.boxes.get(boxNum);
		for(int i = 0; i < 9; i++)
		{
			row[i].removePossible(num);
			col[i].removePossible(num);
			box[i].removePossible(num);
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
		
		System.out.println("ID: " + id + "Possibles: " + tiles[id].stringPossibles());
		
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
