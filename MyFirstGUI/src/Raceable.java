import java.awt.Color;
import java.awt.Graphics;

public interface Raceable {

	public double getXPos();
	public double getYPos();
	public Color getColor();
	public int getCarSpeed();
	public int getCarDirection();
	public void setXPos(double xPos);
	public void setYPos(double yPos);
	public void setColor(Color carColor);
	public void setCarSpeed(int carSpeed);
	public void setCarDirection(int carDirection);
	public void move(double deltax, double deltay);
	public void draw(Graphics g, Color newColor);
	
}
