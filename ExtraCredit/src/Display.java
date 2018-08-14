//Extra Credit Assigment
//Ian Walk (imw6jy)

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Display{
	
	public static boolean interrupt = false;
	
	public static JFrame display = new JFrame();
	
	public static Window bubble;
	public static Window insert;
	public static Window select;
	
	public static JButton run = new JButton();

	public static Bar[] bars = new Bar[50];
	
	
	public static void main(String[] args) {
		display.setSize(1050, 300);
		display.setTitle("Sorting Algorithm Comparison");
		display.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setUpScene(display.getContentPane());
		
		display.setVisible(true);
	}
	
	public static void setUpScene(Container pane) {
		
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Shuffle and run")) {	
					shuffleBars();
					interrupt = true;
					
					Thread t1 = new Thread(bubble);
					t1.start();
					
					Thread t2 = new Thread(insert);
					t2.start();
					
					Thread t3 = new Thread(select);
					t3.start();
				}
			}
		}		
		
		fillBars();
		
		JPanel panel = new JPanel(new GridLayout(1, 4));
		
		JPanel panel2 = new JPanel(new GridLayout(1, 4));
		
		JPanel panel3 = new JPanel(new FlowLayout());
		
		JLabel empty = new JLabel("");
		JLabel bubbleTitle = new JLabel("Bubble Sort");
		JLabel insertionTitle = new JLabel("Insertion Sort");
		JLabel selectionTitle = new JLabel("Selection Sort");
		
		panel.add(empty);
		panel.add(bubbleTitle);
		panel.add(insertionTitle);
		panel.add(selectionTitle);
		
		run.addActionListener(new ButtonListener());
		run.setText("Shuffle and Sort");
		run.setActionCommand("Shuffle and run");
		
		panel3.add(run);
		
		panel2.add(panel3);
		
		panel2.add(bubble);
		panel2.add(insert);
		panel2.add(select);
		
		pane.add(panel, BorderLayout.NORTH);
		pane.add(panel2, BorderLayout.CENTER);
	}
	
	public static void fillBars() {
		for(int i = 0; i < bars.length; i++) {
			bars[i] = new Bar(4 * i + 10);
		}
		
		bubble = new Window(bars, "bubble");
		select = new Window(bars, "select");
		insert = new Window(bars, "insert");
	}
	
	public static void createBox(Container pane, Bar b) {
		pane.add(new DrawBox(0, 0, b, 3));
	}
	
	public static void shuffleBars() {
		int x = 0;
		Bar[] c = new Bar[50];
		Bar[] d = new Bar[50];
		Bar[] e = new Bar[50];
		
		for(int i = 0; i < bars.length; i++) {
			bars[i].setColor(Color.GRAY);
		}
		for(int i = 0; i < bars.length; i++) {
			x = (int) (Math.random() * bars.length);
			Bar b = bars[i];
			bars[i] = bars[x];
			bars[x] = b;
		}
		for(int i = 0; i < bars.length; i++) {
			c[i] = new Bar(bars[i]);
			d[i] = new Bar(bars[i]);
			e[i] = new Bar(bars[i]);
		}
		
		bubble.newBars(c);
		insert.newBars(d);
		select.newBars(e);
	}
}
