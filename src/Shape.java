import java.awt.Graphics;

/**
 * Abstract class that is the superclass of NewLine and NewOval. 
 * @author andi
 *
 */
public abstract class Shape {
	
	/**
	 * Abstract draw method that is overriden by the subclasses
	 * @param g
	 */
	public abstract void draw(Graphics g);
}