import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RiskGui {

	public RiskGui() throws IOException {
		initialize(null);
	}

	private void initialize(Graphics g) throws IOException {
		//MyComponent paintCircles = new MyComponent();

		JFrame frame = new JFrame("Risk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		File file = new File("Map.jpg");
		BufferedImage img = ImageIO.read(file);
		Image ReImage = img.getScaledInstance(frame.getWidth() - 320,
				frame.getHeight(), Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(ReImage));
		// JLabel nodes=new JLabel();

		background.setOpaque(true);
		frame.add(new MyComponent());
		frame.add(background, BorderLayout.WEST);
		CommandPanel command = new CommandPanel();
		command.setBackground(SystemColor.black);
		frame.add(command);

		frame.pack();
		System.out.println("done!");
	}

	@SuppressWarnings("serial")
	class MyComponent extends JPanel {
		Info2 info = new Info2();
		Info1 info1 = new Info1();
	   
		public MyComponent() {
	    	setBounds(0,0,1800,1010);
	    	setOpaque(false);
	    }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);       
	        for(int i=0;i<42;i++){
	        	g.setColor(Color.black);
	            g.setFont( new Font("Helvetica", Font.BOLD, 12) );
	            g.drawString(info1.getName(i), (info.getX(i)+10), info.getY(i));
	        	if(i<=8) g.setColor(Color.red);
	        	else if(i>8 &&i<16) g.setColor(Color.green);
	        	else if(i>15 &&i<28) g.setColor(Color.blue);
	        	else if(i>27 && i<32) g.setColor(Color.yellow);
	        	else if(i>31 && i<36) g.setColor(Color.black);
	        	else if(i>35 && i<42) g.setColor(Color.white);
	        	g.fillOval(info.getX(i),info.getY(i) , 15, 15);
	        	if(i<=8) g.setColor(Color.black);
	        	else if(i>8 &&i<16) g.setColor(Color.yellow);
	        	else if(i>15 &&i<28) g.setColor(Color.white);
	        	else if(i>27 && i<32) g.setColor(Color.blue);
	        	else if(i>31 && i<36) g.setColor(Color.red);
	        	else if(i>35 && i<42) g.setColor(Color.green);
	        	g.drawOval(info.getX(i), info.getY(i), 16, 16);
	        }
		}
	}
}