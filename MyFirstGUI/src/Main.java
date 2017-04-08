import javax.swing.JFrame;

public class Main {
	public static void main (String[] args) throws InterruptedException {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(1000, 1000);
		mainFrame.setTitle("My First Java GUI With Mutable Cars Rocks!!");
		
		int numCars = mainFrame.getHeight() / MyComponent.laneWidth;
		MyComponent theComponent = new MyComponent();
		mainFrame.add(theComponent);
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (!theComponent.getSomeCarWon()) {
			mainFrame.repaint();
			Thread.sleep(100);
		}
	}
}
