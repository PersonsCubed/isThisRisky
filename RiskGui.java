import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RiskGui {
	private Info2 Info = new Info2();
	
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
		JLabel nodes = new JLabel();
		nodes.add(new MyComponent());
		nodes.setOpaque(false);
		contentPane.add(nodes, BorderLayout.WEST);
		//contentPane.add(background, BorderLayout.WEST);
		
	    
		CommandPanel command = new CommandPanel();
	    command.setBackground(SystemColor.black);
		contentPane.add(command);

		frame.pack();
	}
	@SuppressWarnings("serial")
	public class MyComponent extends JComponent{
		protected void paintComponent(Graphics g) {
			for(int i=0;i<41;i++){
				g.setColor(Color.WHITE);
				g.fillOval(Info.getX(i), Info.getY(i), 25, 25);
			}
		}
	}
}