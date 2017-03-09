import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class MutableCar {
	static long count = 0;
	
	private double xPos;
	private double yPos;
	private Color carColor;
	
	private int carSpeed;
	private int carDirection;
	
	private int carSpeedY;
	private int carDirectionY;
	
	public MutableCar(double xPos, double yPos, Color carColor, int speed, int direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.carColor = carColor;
		this.carDirection = direction;
		this.carSpeed = speed;
		this.carDirectionY = 1;
		this.carSpeedY = 40;
	}

	public int getCarSpeedY() {
		return carSpeedY;
	}

	public void setCarSpeedY(int carSpeedY) {
		this.carSpeedY = carSpeedY;
	}

	public int getCarDirectionY() {
		return carDirectionY;
	}

	public void setCarDirectionY(int carDirectionY) {
		this.carDirectionY = carDirectionY;
	}

	public MutableCar(double xPos, double yPos, Color carColor) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.carColor = carColor;
		this.carDirection = 1; // Direction left to right by default
		this.carSpeed = 10; // Speed == 10 pixels per tick by default
	}
	
	public void setPosition(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public void move(int deltax, int deltay) {
		this.xPos = this.xPos + deltax;
		this.yPos = this.yPos + deltay;
	}
	
	public int getCarSpeed() {
		return carSpeed;
	}

	public void setCarSpeed(int carSpeed) {
		this.carSpeed = carSpeed;
	}

	public double getxPos() {
		return xPos;
	}

	public void setxPos(double xPos) {
		this.xPos = xPos;
	}

	public double getyPos() {
		return yPos;
	}

	public void setyPos(double yPos) {
		this.yPos = yPos;
	}

	public int getCarDirection() {
		return carDirection;
	}

	public void setCarDirection(int carDirection) {
		this.carDirection = carDirection;
	}

	public void draw(Graphics g) {
		
		Point2D.Double rearWindowStart = new Point2D.Double(xPos+10,yPos+10);
		Point2D.Double rearWindowEnd = new Point2D.Double(xPos+20,yPos+0);
		Line2D.Double rearWindow = new Line2D.Double(rearWindowStart, rearWindowEnd);

		Point2D.Double frontWindowStart = new Point2D.Double(xPos+40,yPos+0);
		Point2D.Double frontWindowEnd = new Point2D.Double(xPos+50,yPos+10);
		Line2D.Double frontWindow = new Line2D.Double(frontWindowStart, frontWindowEnd);

		Point2D.Double roofStart = new Point2D.Double(xPos+20,yPos+0);
		Point2D.Double roofEnd = new Point2D.Double(xPos+40,yPos+0);
		Line2D.Double roof = new Line2D.Double(roofStart, roofEnd);
		
		
		Rectangle2D.Double body = new Rectangle2D.Double(xPos+0,yPos+10,60,10);
		
		Ellipse2D.Double rearTire = new Ellipse2D.Double(xPos+10,yPos+20,10,10);
		Ellipse2D.Double frontTire = new Ellipse2D.Double(xPos+40,yPos+20,10,10);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(carColor);
		g2.draw(rearWindow);
		g2.draw(roof);
		g2.draw(frontWindow);
		g2.draw(body);
		g2.setColor(Color.BLACK);
		g2.draw(rearTire);
		g2.draw(frontTire);
		
		count++;
		System.out.println("Car count " + count);
		
	}
}
