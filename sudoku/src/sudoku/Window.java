package sudoku;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Window extends JFrame implements UI
{
	//private Color bGColor = new Color(120, 120, 120);
	
	private MainMenu mainMenu;
	private GameWindow gameWindow;
	
	//GB: the frames insets[top=31,left=8,bottom=8,right=8]
	private Dimension size = new Dimension(500 + 8 + 8, 500 + 31 + 8);
	
	private JButton closeMainMenuButton;
	private JButton openMainMenuButton;
	
	public static final long serialVersionUID=1;
	
	
	public Window(String name)
	{
		super(name);
	}
	
	//GB: Sets up the game, called from main.
	public void setUp()
	{
		setUpWindow();
		setUpMainMenu();
		setUpGameWindow();
		
		//GB: This component listener triggers the windowResized function on resizing
		this.addComponentListener(new ComponentAdapter()
		{
		    public void componentResized(ComponentEvent componentEvent) 
		    {
		    	System.out.println("Window Resized");
		    	
		    	windowResized();
		    }
		});
		
		closeMainMenu(); //GB: just for testing purposes
		return;
	}
	
	private void setUpWindow()
	{
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setSize(size);
		this.getContentPane().setLayout(null);
		this.getContentPane().setBackground(bGColor);
		
		System.out.println(this.getInsets());
	}
	
	private void setUpMainMenu()
	{
		mainMenu = new MainMenu(this.getAdjustedSize());
		
		closeMainMenuButton = makeButton("Open Game Window");
		mainMenu.add(closeMainMenuButton);
		
		closeMainMenuButton.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent e)
			{
				add(gameWindow);
				closeMainMenu();
			}
	    });
		
		openMainMenu();
		return;
	}
	
	private void setUpGameWindow()
	{
		gameWindow = new GameWindow(this.getAdjustedSize());
		
		
		openMainMenuButton = makeButton("Open Main Menu");
		gameWindow.add(openMainMenuButton);
		gameWindow.addGameBoard();

		openMainMenuButton.addActionListener(new ActionListener()
	    {
			public void actionPerformed(ActionEvent e)
			{
				remove(gameWindow);
				validate();
				repaint();
				openMainMenu();
			}
	    });
		
		return;
	}
	
	public void openMainMenu()
	{
		this.add(mainMenu);
		this.validate();
		this.repaint();
	}
	
	public void closeMainMenu()
	{
	    this.add(gameWindow);
		this.remove(mainMenu);
		this.validate();
		this.repaint();
	}
	
	//GB: Handles the placing of UI elements upon the resizing of the window.
	/**
	 * @author bilbobeswaggin
	 * 
	 * Needs to first take the size of the window into account and then reposition
	 * every visual element of the game accordingly
	 */
	public void windowResized()
	{
		
		Dimension temp = this.getAdjustedSize();
		System.out.println(temp);
		mainMenu.setSize(temp);
		mainMenu.windowResized(temp);
		
		gameWindow.setSize(temp);
		
		this.validate();
		this.repaint();
	}
	
	//GB: adjusts the size of the JFrame to account for its insets
	private Dimension getAdjustedSize()
	{
		Dimension temp = this.getSize();
		Dimension adjusted = new Dimension(temp.width - 16, temp.height - 39);
		
		return adjusted;
	}
	
	//GB: Creates a JButton with colors and style that matches the game.
	private JButton makeButton(String name)
	{
		JButton temp = new JButton(name);
		
		temp.setBackground(new Color(40, 40, 40));
		temp.setForeground(new Color(215, 215, 215));
		temp.setFocusPainted(false);
		temp.setBorderPainted(false);
		
		temp.setSize(150, 25);
		temp.setLocation(0, 0);
		
		return temp;
	}
}