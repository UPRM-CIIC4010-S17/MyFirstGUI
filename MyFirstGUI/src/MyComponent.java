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

	static int counter = 0;

	static MutableCar theCar = new MutableCar(0,0,Color.BLACK, 10, 1);
	static Random genRand = new Random();

	public boolean carBumped(MutableCar c) {
		return ((c.getCarDirection() > 0) && (c.getxPos()+60 >= this.getWidth()) 
				|| 
				(c.getxPos() <= 0));
	}

	public boolean carReachedTopOrBottom(MutableCar c) {
		if (c.getCarDirectionY() > 0) {
			return (c.getyPos() + 30 > this.getHeight());
		}
		else {
			return (c.getyPos() - 30 < 0);
		}
	}


	public void paintComponent(Graphics g) {		

		theCar.draw(g);
		theCar.move(theCar.getCarSpeed()*theCar.getCarDirection(), 0);

		int deltay = 0;

		if (this.carBumped(theCar)) {
			theCar.setCarDirection(theCar.getCarDirection()*-1);
			deltay = theCar.getCarDirectionY()*theCar.getCarSpeedY();
		}
		if (this.carReachedTopOrBottom(theCar)) {
			theCar.setCarDirectionY(theCar.getCarDirectionY()*-1);
		}
		int deltax = theCar.getCarDirection()*theCar.getCarSpeed();
		theCar.move(deltax, deltay);

		//		//MutableCar car2 = new MutableCar(0,40, Color.BLUE);
		//		theCar.setPosition(0, 40);
		//		theCar.draw(g);

		System.out.println("Painted " + counter++ + " times");
	}

}
