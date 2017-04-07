import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

import javax.swing.JComponent;

public class MyComponent extends JComponent {

	private static int counter = 0;

	private static Raceable theCars[];

	private static Random genRand = new Random();

	private static boolean someCarWon = false;

	public static final int laneWidth = 50;

	public boolean getSomeCarWon() { return someCarWon; }

	public MyComponent(int numCars) {
		theCars = new Raceable[numCars];
		for(int i=0; i<numCars; i++) {
			int laneY = i * laneWidth + 10;
			if (i==0) {
				theCars[i] = new PoliceCar(0, laneY, Color.RED, 0, 1);
			}
			else if (i==1) {
				theCars[i] = new Truck(0, laneY, Color.BLACK, 0, 1);
			}
			else if (i==2) {
				theCars[i] = new Turtle(0, laneY, Color.BLACK, 0, 1);
			}
			else {
				theCars[i] = new MutableCar(0, laneY, Color.RED, 0, 1);
			}
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
			theCars[i].move(genRand.nextInt(10), 0);
			if (this.carCrashed(theCars[i])) {
				this.someCarWon = true;
			}
		}
		theCars[iMax].draw(g,Color.GREEN);

		System.out.println("Painted " + counter++ + " times");
	}

}
