/** InfoBox.java
 *  @author Gage Brown
 * 
 * 
 *  Info box displayed in corner
 * 		Ex:
 * 			ID: XX
 * 		   Box: 0X
 *         Row: 0X
 *         Col: 0X
 */
package sudoku;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InfoBox extends JPanel implements UI
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6547354006585198907L;
	
	private JLabel labelID, labelBox, labelRow, labelCol;
	private boolean visible;
	
	public InfoBox()
	{
		super(null);
		setUp();
		createLabels();
	}
	
	public void display(int id, int box, int row, int col)
	{
		labelID.setText("ID: " + ((id < 10) ? "0" + id : id));
		labelBox.setText("Box: 0" + box);
		labelRow.setText("Row: 0" + row);
		labelCol.setText("Col: 0" + col);
		
		this.add(labelID);
		this.add(labelBox);
		this.add(labelRow);
		this.add(labelCol);
		
		this.validate();
		this.repaint();
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public boolean getVisible()
	{
		return this.visible;
	}
	
	private void setUp()
	{
		this.setSize(60, 80);
		this.setLocation(440, 0);
		this.setBackground(gameBGColor);
	}
	
	private void createLabels()
	{
		labelID  = labelMaker();
		labelBox = labelMaker();
		labelRow = labelMaker();
		labelCol = labelMaker();
			
		labelID .setLocation(0, 0);
		labelBox.setLocation(0, 20);
		labelRow.setLocation(0, 40);
		labelCol.setLocation(0, 60);
	}
		
	/**
	 * @author bilbobeswaggin
	 * @return JLabel with predefined field values i.e. a JLabel 
	 * 		   template
	 */
	private JLabel labelMaker()
	{			JLabel label = new JLabel("", SwingConstants.RIGHT);
		//GB: SwingConstants.RIGHT aligns the label text to the right
		label.setSize(60, 15);
		label.setFont(new Font("info", Font.BOLD, 15));
			
		return label;
	}
	
	
}
