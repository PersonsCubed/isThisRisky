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
		contentPane.add(background, BorderLayout.WEST);
	    
		CommandPanel command = new CommandPanel();
	    command.setBackground(SystemColor.black);
		contentPane.add(command);

		frame.pack();
	}		
}