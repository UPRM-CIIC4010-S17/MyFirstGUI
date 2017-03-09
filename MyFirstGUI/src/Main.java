import javax.swing.JFrame;

public class Main {
	public static void main (String[] args) throws InterruptedException {
		JFrame mainFrame = new JFrame();
		mainFrame.setSize(400, 300);
		mainFrame.setTitle("My First Java GUI With Mutable Cars Rocks!!");
		
		MyComponent theComponent = new MyComponent();
		mainFrame.add(theComponent);
		
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			mainFrame.repaint();
			Thread.sleep(100);
		}
	}
}
