import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Turtle implements Raceable {
	static long count = 0;
	
	private double xPos;
	private double yPos;
	private Color carColor;
	
	private int carSpeed;
	private int carDirection;
	
	private Image image;
	
	public Turtle(double xPos, double yPos, Color carColor, int speed, int direction) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.carColor = carColor;
		this.carDirection = direction;
		this.carSpeed = speed;
		
		File turtleFile = new File("image/turtle-40x60.png");
		
		try {
			image = ImageIO.read(turtleFile);
		}
		catch (IOException e) {
			System.out.println("Image File not found");
			System.exit(1); // Kill APP
		}
	}

	public Turtle(double xPos, double yPos, Color carColor) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.carColor = carColor;
		this.carDirection = 1; // Direction left to right by default
		this.carSpeed = 10; // Speed == 10 pixels per tick by default
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	public Color getColor() {
		return carColor;
	}
	public int getCarSpeed() {
		return carSpeed;
	}
	public int getCarDirection() {
		return carDirection;
	}
	public void setXPos(double xPos) {
		this.xPos = xPos;
	}
	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	public void setColor(Color carColor) {
		this.carColor = carColor;
	}
	public void setCarSpeed(int carSpeed) {
		this.carSpeed = carSpeed;
	}
	public void setCarDirection(int carDirection) {
		this.carDirection = carDirection;
	}
	
	public void move(double deltax, double deltay) {
		this.setXPos(this.getXPos()+deltax);
		this.setYPos(this.getYPos()+deltay);
	}

	public void draw(Graphics g, Color newColor) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(this.image,(int) this.getXPos(), (int)this.getYPos(), null);
	}
	
}
