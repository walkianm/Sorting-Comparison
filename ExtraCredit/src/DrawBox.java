//Extra Credit Assigment
//Ian Walk (imw6jy)

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;

public class DrawBox extends JComponent{
	private static final long serialVersionUID = 1L;
	
	int x;
	int y;
	int width;
	int height;
	Color color;
	
	public DrawBox(int x, int y, Bar b, int height) {
		this.x = x;
		this.y = y;
		this.width = b.getSize();
		this.height = height;
		this.color = b.getColor();
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(this.color);
		Rectangle box1 = new Rectangle(x, y, width, height);
		g2.fill(box1);
	}
}
