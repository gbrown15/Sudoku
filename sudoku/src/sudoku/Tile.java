package sudoku;

import java.awt.Font;

import java.util.Vector;

import java.util.Random;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Tile extends JPanel implements UI
{
	private static final long serialVersionUID = 7547811879365061004L;
	
	public boolean highlighted = false;
	
	private int userInputNum;
	public int solution = -1;
	
	public int id;
	public int box;
	public int x;
	public int y;
	private int blockSubCol;
	private int blockSubRow;
	private int col;
	private int row;
	private boolean solvedTile; //GB: tile displays its original value
	
	Vector<Integer> possibles = new Vector<>();
	
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
		
		this.solvedTile = false;
		
		for(int i = 1; i < 10; i++)
		{
			possibles.add(i);
		}
	}
	
	public void reset()
	{
		possibles.clear();
		for(int i = 1; i < 10; i++)
		{
			possibles.add(i);
		}
	
		this.removeAll();
		this.addLabel();
		this.repaint();
		this.solution = -1;
		this.solvedTile = false;
	}
	
	public void assignSolution(int solution)
	{
		this.solution = solution;
		Random rand = new Random();
		if(rand.nextBoolean() || rand.nextBoolean())
		{
			this.setUserInput(solution);
			this.solvedTile = true;
		}
		
	}
	
	public void setUserInput(int num)
	{
		userInputNum = num;
		addUserLabel();
	}
	
	private void addUserLabel()
	{
		this.removeAll();
		this.addLabel();
		this.repaint();
		
		String s = Integer.toString(this.userInputNum);
		
		JLabel label = new JLabel(s);
		
		label.setSize(8, 8);
		label.setLocation(12, 12);
		label.setFont(new Font("id", Font.BOLD, 9));
		this.add(label);
	}
	
	private void addSolutionLabel()
	{
		String s = Integer.toString(this.solution);
		
		JLabel label = new JLabel(s);
		
		label.setSize(8, 8);
		label.setLocation(12, 12);
		label.setFont(new Font("id", Font.BOLD, 9));
		this.add(label);
	}
	
	public void removePossible(int num)
	{
		if(this.possibles.contains(num))
		{
			this.possibles.removeElement(num);
		}
	}
	
	public Vector<Integer> getPossibles()
	{
		return this.possibles;
	}
	
	public String stringPossibles()
	{
		String possible = "";
		for (int i = 1; i < 10; i++)
		{
			if (possibles.contains(i)) 
			{
				possible += String.valueOf(i);
			}
			else 
			{
				possible += "_";
			}
			possible += " ";
		}
	
		return possible;
	}
	
	public boolean isSolvedTile()
	{
		return this.solvedTile;
	}
	
	public int getSolution()
	{
		return this.solution;
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
			if (blockCol - 3 >= 0) blockCol -= 3;
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
	
	/*private void setKeyBindings()
	{
		ActionMap actionMap = getActionMap();
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = getInputMap(condition);
		
		
	}*/
	
	@Override
	public boolean isFocusTraversable () 
	{
		return true ;
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
