import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;


/**
 * class that extends shape. Used to draw a new line. 
 * @author andi
 *
 */
public class NewLine extends Shape {
	private int x1, y1, x2, y2;

	/**
	 * New line constructor. Takes in the coordinates to draw a new line method.  
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	NewLine(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void draw(Graphics g){
		g.drawLine(x1, y1, x2, y2);
		}
}
