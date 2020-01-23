package sudoku;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

class Tile extends JPanel implements UI
{
	private static final long serialVersionUID = 7547811879365061004L;
	
	public boolean highlighted = false;
	
	private int userInputNum;
	private int solution = -1;
	
	public int id;
	public int box;
	public int x;
	public int y;
	private int blockSubCol;
	private int blockSubRow;
	private int col;
	private int row;
	private boolean[] possibles = new boolean[9];
	
	public Tile(int id, int blockNumber, int blockSubCol, int blockSubRow)
	{
		super(null);
		this.id = id;
		this.box = blockNumber;
		
		this.blockSubCol = blockSubCol;
		this.blockSubRow = blockSubRow;
		
		this.setCol();
		this.setRow();
		
		
		
		this.setBackground(gameBGColor);
		this.setSize(30, 30);
		
		this.setName(Integer.toString(id));
		this.addLabel();
		
		for(int i = 0; i < 9; i++)
		{
			possibles[i] = true;
		}
	}
	
	public void assignSolution(int solution)
	{
		this.solution = solution;
	}
	
	public void removePossible(int num)
	{
		this.possibles[num] = false;
		
	}
	
	public boolean checkSolution()
	{
		return (userInputNum == solution) ? true : false;
	}
	
	private void setCol()
	{
		boolean done = false;
		int blockCol = this.box;
		while(!done)
		{
			if(blockCol - 3 >= 0) blockCol -= 3;
			else done = true;
		}
		this.col = (blockCol * 3) + blockSubCol;
	}
	
	private void setRow()
	{
		boolean done = false;
		int blockRow = 0; 
		int blockNumber = this.box;
		while(!done)
		{
			if(blockNumber - 3 >= 0) 
			{
				blockNumber -= 3;
				blockRow++;
			}
			else done = true;
		}
		this.row = (blockRow * 3) + blockSubRow;
	}
	
	private void addLabel()
	{
		String sId = Integer.toString(id);
		String labelString = (id < 10) ? ("  " + sId) : (sId);
		
		JLabel label = new JLabel("           " + labelString);
		
		label.setSize(30, 8);
		label.setLocation(0, 0);
		label.setFont(new Font("id", Font.BOLD, 6));
		this.add(label);
	}
	
	@Override
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		super.setLocation(x, y);
	}
	
	public int getID()
	{
		return this.id;
	}
	
	public int getBox()
	{
		return this.box;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public int getBlockSubRow()
	{
		return this.blockSubRow;
	}
	
	public int getBlockSubCol()
	{
		return this.blockSubCol;
	}
}
