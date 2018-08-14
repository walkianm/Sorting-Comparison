//Extra Credit Assigment
//Ian Walk (imw6jy)

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class Window extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	final int DELAY = 30;
	
	Bar[] bars = new Bar[50];
	String sortType;
	
	boolean updated = false;
	
	static GridLayout grid = new GridLayout(50, 1);
	
	public void run() {
		try {
			Thread.sleep(5*DELAY);
		}catch(Exception e) {
			
		}
		this.clearColor();
		try {
			Thread.sleep(20*DELAY);
		}catch(Exception e) {
			
		}
		Display.interrupt = false;
		if(this.sortType.equals("bubble")) {
			this.bubbleSort();
		}
		if(this.sortType.equals("insert")) {
			this.insertSort();
		}
		if(this.sortType.equals("select")) {
			this.selectSort();
		}
	}
	
	public void clearColor() {
		for(int i = 0; i < this.bars.length; i++) {
			this.bars[i].setColor(Color.GRAY);
			refresh(i);
		}
	}
	
	public Window(Bar[] b, String s) {
		grid.setVgap(1);
		this.setLayout(grid);
		this.bars = b;
		this.sortType = s;
		
		this.fillWindow();
	}
	
	public Bar[] getBars() {
		return this.bars;
	}
	
	public Bar getBar(int index) {
		return this.bars[index];
	}
	
	public void swapBars(int one, int two) {
		Bar b = bars[one];
		bars[one] = bars[two];
		bars[two] = b;
		
		refresh(one);
		refresh(two);
	}
	
	public void refresh(int one) {
		this.remove(one);
		this.add(new DrawBox(0, 0, this.getBar(one), 3), one);

		this.revalidate();
	}
	
	public void newBars(Bar[] b) {
		this.bars = b;
		
		for(int i = 0; i < this.bars.length; i++) {
			refresh(i);
		}
	}
	
	public void fillWindow() {
		for(int i = 0; i < 50; i++) {
			Display.createBox(this, this.bars[i]);
		}
	}
	
	public String toString() {
		String s = "";
		for(Bar b : bars) {
			s += b.getSize() + " ";
		}
		
		return s;
	}
	
	public void selectSort() {
		int minIndex = 0;
		for(int i = 0; i < this.bars.length && !Display.interrupt; i++) {
			minIndex = i;
			this.getBar(this.bars.length - 1).setColor(Color.GRAY);
			refresh(this.bars.length - 1);
			for(int j = i; j < this.bars.length && !Display.interrupt; j++) {
				if(j > i) {
					this.getBar(j - 1).setColor(Color.GRAY);
					refresh(j - 1);
				}
				this.getBar(j).setColor(Color.RED);
				refresh(j);
				
				if(this.getBar(j).size < this.getBar(minIndex).size) {
					minIndex = j;
				}
				try {
					Thread.sleep(DELAY);
				}catch(Exception e) {
					
				}
			}
			this.getBar(minIndex).setColor(Color.BLACK);
			this.swapBars(i, minIndex);
		}
	}
	
	public void insertSort() {
		int j = 0;
		for(int i = 0; i < this.bars.length && !Display.interrupt; i++) {
			j = i;
			this.getBar(i).setColor(Color.RED);
			while(j-1 >= 0 && this.getBar(j).size < this.getBar(j-1).size && !Display.interrupt) {
				this.swapBars(j, j-1);
				j--;
				try {
					Thread.sleep(DELAY);
				}catch(Exception e) {
					
				}
			}
			this.getBar(j).setColor(Color.BLACK);
			refresh(j);
		}
	}	
	
	public void bubbleSort() {
		int lastSwap = 0;
		int currentSwap = 1;
		int previousSwap = 0;
	
		while (currentSwap != 0 && !Display.interrupt) {		
			currentSwap = 0;
			for(int j = this.getBars().length - 1; j > lastSwap && !Display.interrupt; j--) {
				this.getBar(j).setColor(Color.RED);
				
				if(this.getBar(j).getSize() < this.getBar(j - 1).getSize()) {
					this.swapBars(j, j-1);

					currentSwap = j;
				}else {
					this.getBar(j).setColor(Color.RED);
					refresh(j);
				}
				if(j < 48) {
					this.getBar(j+1).setColor(Color.GRAY);
					this.getBar(49).setColor(Color.GRAY);
					
					refresh(j+1);
					refresh(49);
				}
				try {
					Thread.sleep(DELAY);
				} catch (InterruptedException e) {

				}
			}
			lastSwap = currentSwap;
			for(int i = previousSwap; i < lastSwap && !Display.interrupt; i++) {
				this.getBar(i).setColor(Color.BLACK);
				refresh(i);
			}
			previousSwap = lastSwap;
		}
		for(int i = this.getBars().length -1; i > previousSwap && !Display.interrupt; i--) {
			this.getBar(i).setColor(Color.BLACK);
			refresh(i);
		}
	}
}
