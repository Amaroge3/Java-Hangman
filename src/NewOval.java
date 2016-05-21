import java.awt.Graphics;


/**
 * Oval class that extends Shape. Used to create a new Oval shape with the draw method.  
 * @author andi
 *
 */
public class NewOval extends Shape {
	
	
	//instance variables of Oval class
	private int x1, y1, width, height;
	
	
	/**
	 * Constructor assigns the x1, y1, width, and height when object is created.
	 * @param x1
	 * @param y1
	 * @param width
	 * @param height
	 */
	NewOval(int x1, int y1,int width,int height){	
		this.x1 = x1;
		this.y1 = y1;
		this.width = width;
		this.height = height;
	}

	
	/**
	 * Draw method to draw oval shape
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.drawOval(x1, y1, width, height);
	}
	
}
