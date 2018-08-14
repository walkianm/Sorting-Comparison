//Extra Credit Assigment
//Ian Walk (imw6jy)

import java.awt.Color;

public class Bar {
	public int size;
	public Color color;
	
	public Bar(int size) {
		this.size = size;
		this.color = Color.BLACK;
	}
	
	public Bar(Bar b) {
		this.size = b.size;
		this.color = b.color;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String toString() {
		return "Size:" + this.size;
	}
}
