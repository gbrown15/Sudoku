package sudoku;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JPanel implements UI
{
	private static final long serialVersionUID = -7583982177987691801L;
	
	private JLabel mainTag = new JLabel("SUDOKU");

	public MainMenu(Dimension dim)
	{
		super(null);
		this.setSize(dim);
		this.setBackground(menuBGColor);
		this.setLocation(0, 0);
		
		
		
		Font label = new Font("mainTag", 0, 50);
		
		Dimension mid = new Dimension(300, (dim.height / 2));
		Point point = new Point(145, (mid.height / 2));
		mainTag.setSize(mid);
		mainTag.setLocation(point);
		mainTag.setFont(label);
		
		this.add(mainTag);
	}
	
	public void windowResized(Dimension dim)
	{
//		Dimension mid = new Dimension((dim.width / 2) + 100, (dim.height / 2));
//		Point point = new Point((mid.width / 2), (mid.height / 2));
//		mainTag.setSize(mid);
//		mainTag.setLocation(point);
	}
}
