import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class RiskGui {
	
	public RiskGui() throws IOException {
		initialize(null);
	}
	
	private void initialize(Graphics g) throws IOException {
		
		JFrame frame = new JFrame("Risk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		Container contentPane = frame.getContentPane();
		
		File file = new File("Map.jpg");
		BufferedImage img = ImageIO.read(file);
		Image ReImage = img.getScaledInstance(frame.getWidth()-320, frame.getHeight() , Image.SCALE_SMOOTH);
		JLabel background=new JLabel(new ImageIcon(ReImage));
		background.setOpaque(true);
		contentPane.add(background, BorderLayout.WEST);
	    
		background.setOpaque(true);
		frame.add(new MyComponent());
		frame.add(background, BorderLayout.WEST);
		CommandPanel command = new CommandPanel();
		command.setBackground(SystemColor.black);
		frame.add(command);
		
		frame.pack();
		
	}	
		
	@SuppressWarnings("serial")
	class MyComponent extends JPanel {
		Info2 info = new Info2();
		Info1 info1 = new Info1();

	   
		public MyComponent() {
	    	setBounds(0,0,1800,1010);
	    	setOpaque(false);
	    }
		
	    @SuppressWarnings("static-access")
		public void paintComponent(Graphics g) {
	    	
	    	int temp ;
	       
	    	super.paintComponent(g);       
	        
	    	for(int i=0;i<42;i++){
	        
	    		g.setColor(Color.black);
	            g.setFont( new Font("Helvetica", Font.BOLD, 12) );
	            g.drawString(info1.getName(i), (info.getX(i)+10), info.getY(i));

	            g.setColor(Color.white);

	            if(i<=8) g.setColor(Color.red);
	        	else if(i>8 &&i<16) g.setColor(Color.green);
	        	else if(i>15 &&i<28) g.setColor(Color.blue);
	        	else if(i>27 && i<32) g.setColor(Color.yellow);
	        	else if(i>31 && i<36) g.setColor(Color.black);
	        	else if(i>35 && i<42) g.setColor(Color.white);
	        	
	            g.fillOval(info.getX(i),info.getY(i) , 20, 20);
	        	
	            if(i<=8) g.setColor(Color.black);
	        	else if(i>8 &&i<16) g.setColor(Color.yellow);
	        	else if(i>15 &&i<28) g.setColor(Color.white);
	        	else if(i>27 && i<32) g.setColor(Color.blue);
	        	else if(i>31 && i<36) g.setColor(Color.red);
	        	else if(i>35 && i<42) g.setColor(Color.green);	
	            
	            g.drawString("1", info.getX(i)+7, info.getY(i)+14); //draws the number 1(1 army for start) in the nodes
	            
	          //draws line between two points	
	            for(int j=0; j<(info1.ADJACENT[i]).length; j++){ 	            	
	    			temp = info1.ADJACENT[i][j];	
	    			g.drawLine(info.getX(i)+10, info.getY(i)+7, info.getX(temp)+10, info.getY(temp)+7);
	    		} 
	           
	        }
	    }
	}
}