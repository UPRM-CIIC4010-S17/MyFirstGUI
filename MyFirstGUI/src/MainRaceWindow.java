import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;

public class MainRaceWindow {

	private JFrame frame;
	private MyComponent theComponent;

	private boolean racePaused = true;
	private long repaintPeriod = 100;

	JLabel lblTimeElapsed;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws InterruptedException {
		//		EventQueue.invokeLater(new Runnable() {
		//			public void run() {
		//
		MainRaceWindow window = new MainRaceWindow();

		window.frame.setVisible(true);
		//		try {
		//		} catch (Exception e) {
		//			e.printStackTrace();
		//		}
		while (!window.theComponent.getSomeCarWon()) {
			if (!window.racePaused) {
				window.theComponent.repaint();
				window.updateTimeElapsed();
			}
			try {
				Thread.sleep(window.repaintPeriod);
			}
			catch (InterruptedException e) {
				System.exit(1);
			}
		}
		//			}
		//		});
	}

	/**
	 * Create the application.
	 */
	public MainRaceWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel headerPanel = new JPanel();
		headerPanel.setBorder(new LineBorder(Color.GRAY));
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));

		File UPRMSealFile = new File("image/UPRMSeal-100x100.png");
		Image UPRMSealImage = null;
		try {
			UPRMSealImage = ImageIO.read(UPRMSealFile);
		}
		catch (IOException e) {
			System.out.println("Image File not found");
			System.exit(1); // Kill APP
		}
		ImageIcon UPRMSealIcon = new ImageIcon(UPRMSealImage);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		headerPanel.add(horizontalStrut);
		JLabel lblUPRMSeal = new JLabel(UPRMSealIcon);
		headerPanel.add(lblUPRMSeal);

		Component horizontalGlue_1 = Box.createHorizontalGlue();
		headerPanel.add(horizontalGlue_1);

		JLabel lblUprmAdvancedProgramming = new JLabel("UPRM Advanced Programming Race");
		headerPanel.add(lblUprmAdvancedProgramming);
		lblUprmAdvancedProgramming.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblUprmAdvancedProgramming.setHorizontalAlignment(SwingConstants.CENTER);

		Component horizontalGlue_2 = Box.createHorizontalGlue();
		headerPanel.add(horizontalGlue_2);

		File CSELogoFile = new File("image/CSE-logo-100x36.png");
		Image CSELogoImage = null;
		try {
			CSELogoImage = ImageIO.read(CSELogoFile);
		}
		catch (IOException e) {
			System.out.println("Image File not found");
			System.exit(1); // Kill APP
		}
		ImageIcon CSELogoIcon = new ImageIcon(CSELogoImage);
		JLabel lblCSELogo = new JLabel(CSELogoIcon);
		headerPanel.add(lblCSELogo);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		headerPanel.add(horizontalStrut_1);

		this.theComponent = new MyComponent();
		theComponent.setBorder(new LineBorder(Color.GRAY));
		this.theComponent.setRepaintPeriod(repaintPeriod);
		frame.getContentPane().add(theComponent, BorderLayout.CENTER);

		JPanel controlPanel = new JPanel();
		frame.getContentPane().add(controlPanel, BorderLayout.SOUTH);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setupRace();
				updateTimeElapsed();
				frame.repaint();
			}
		});
		controlPanel.add(btnReset);

		Component horizontalGlue = Box.createHorizontalGlue();
		controlPanel.add(horizontalGlue);

		JLabel lblTimeElapsed = new JLabel("TimeElapsed");
		lblTimeElapsed.setToolTipText("Time Elapsed");
		lblTimeElapsed.setForeground(Color.BLACK);
		lblTimeElapsed.setBackground(Color.WHITE);
		controlPanel.add(lblTimeElapsed);
		this.lblTimeElapsed = lblTimeElapsed;  // Keep hook to thi label in window to allow updates

		Component glue = Box.createGlue();
		controlPanel.add(glue);

		JButton btnPause = new JButton("Start");
		controlPanel.add(btnPause);
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (racePaused) {
					racePaused = false;
					btnPause.setText("Pause");
				}
				else {
					racePaused = true;
					btnPause.setText("Resume");
				}
			}
		});

		setupRace();
		frame.repaint();

	}

	public void setupRace() {

		//int numCars = this.theComponent.getHeight() / MyComponent.laneWidth;
		int numCars = 12;

		Raceable[] theCars = new Raceable[numCars];
		for(int i=0; i<numCars; i++) {
			int laneY = i * MyComponent.laneWidth + 10;
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
		this.theComponent.addRacers(theCars);
		this.theComponent.setTimeElapsed(0);
		//this.racePaused = true;

	}

	public void updateTimeElapsed() {
		this.lblTimeElapsed.setText(this.theComponent.getTimeElapsed() / 1000.0 +" seconds");
	}

}
