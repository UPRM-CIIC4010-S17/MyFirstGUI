import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MyComponent extends JComponent {

	private int counter = 1;
	
	private long timeElapsed = 0;
	
	private long repaintPeriod = 100;  // repaint period in milliseconds

	private Raceable theCars[];

	private static Random genRand = new Random();

	private boolean someCarWon = false;

	public static final int laneWidth = 50;

	public boolean getSomeCarWon() { return someCarWon; }
	public void setSomeCarWon(boolean won) { someCarWon = won; }

	public MyComponent() {
		// Dummy racers so that GUI designer doesn't crash
		theCars = new Raceable[12];
		int y = 0;
		for (int i=0; i<12; i++) {
			theCars[i] = new MutableCar(0, y, Color.RED, 0, 1);
			y += laneWidth;
		}
	}
	
	public void setRepaintPeriod(long period) {
		repaintPeriod = period;
	}
	
	public long getTimeElapsed() {
		return timeElapsed;
	}
	
	public void setTimeElapsed(long time) {
		timeElapsed = time;
	}

	public void addRacers(Raceable[] racers) {
		theCars = new Raceable[12];
		for (int i=0; i<12; i++) {
			this.theCars[i] = racers[i];
		}
	}
	
	public boolean carCrashed(Raceable c) {
		if (c.getCarDirection() > 0) {
			if (c.getXPos()+60 >= this.getWidth()) {
				return true;
			}
		}
		else if (c.getCarDirection() < 0) {
			if (c.getXPos() <= 0) {
				return true;
			}			
		}

		return false;

	}

	public void paintComponent(Graphics g) {		

		int iMax = 0;
		for (int i=0; i < theCars.length; i++) {
			//theCars[i].setColor(Color.RED);
			if (theCars[iMax].getXPos() < theCars[i].getXPos()) {
				iMax = i;
			}
		}
		for (int i=0; i < theCars.length; i++) {
			if (i!=iMax) {
				theCars[i].draw(g,theCars[i].getColor());
			}
			else {
				theCars[i].draw(g,Color.GREEN);
			}
			theCars[i].move(genRand.nextInt(10), 0);
			if (this.carCrashed(theCars[i])) {
				this.someCarWon = true;
			}
		}
		
		timeElapsed += repaintPeriod;

		System.out.println("Painted " + counter++ + " times");
	}

}
