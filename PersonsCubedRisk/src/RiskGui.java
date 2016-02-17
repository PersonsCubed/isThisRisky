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
		
		GraphDraw nodesPanel = new GraphDraw(frame.getWidth()-320, frame.getHeight());
		nodesPanel.setOpaque(true);
		
		addNodes(nodesPanel);
		addEdges(nodesPanel);
		
		contentPane.add(nodesPanel, BorderLayout.WEST);
		
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
	private static void addEdges(GraphDraw nodesPanel) {
		nodesPanel.addEdge(0, 1);
		nodesPanel.addEdge(0, 2);
		nodesPanel.addEdge(0, 3);
		nodesPanel.addEdge(0, 6);
		nodesPanel.addEdge(0, 4);
		nodesPanel.addEdge(0, 5);
		nodesPanel.addEdge(1, 4);
		nodesPanel.addEdge(1, 5);
		nodesPanel.addEdge(2, 8);
		nodesPanel.addEdge(2, 4);
		nodesPanel.addEdge(3, 8);
		nodesPanel.addEdge(3, 6);
		nodesPanel.addEdge(4, 14);
		nodesPanel.addEdge(5, 7);
		nodesPanel.addEdge(6, 7);
		nodesPanel.addEdge(7, 32);
		nodesPanel.addEdge(14, 15);
		nodesPanel.addEdge(32, 33);
		nodesPanel.addEdge(32, 34);
		nodesPanel.addEdge(33, 34);
		nodesPanel.addEdge(33, 35);
		nodesPanel.addEdge(34, 35);
		
	}

	private static void addNodes(GraphDraw nodesPanel) {
		nodesPanel.addNode("0Ontario", 191, 150);
		nodesPanel.addNode("1Quebec", 255, 161);
		nodesPanel.addNode("2NW Territory", 146, 86);
		nodesPanel.addNode("3Alberta", 123, 144);
		nodesPanel.addNode("4Greenland", 314, 61);
		nodesPanel.addNode("5E United States", 205, 235);
		nodesPanel.addNode("6W United States", 135, 219);
		nodesPanel.addNode("7Central America", 140, 299);
		nodesPanel.addNode("8Alaska", 45, 89);
		nodesPanel.addNode("9Great Britain", 370, 199);
		nodesPanel.addNode("10W Europe", 398, 280);      // 10
		nodesPanel.addNode("11S Europe", 465, 270);
		nodesPanel.addNode("12Ukraine", 547, 180);
		nodesPanel.addNode("13N Europe", 460, 200);
		nodesPanel.addNode("14Iceland", 393, 127);
		nodesPanel.addNode("15Scandinavia", 463, 122);
		nodesPanel.addNode("16Afghanistan", 628, 227);
		nodesPanel.addNode("17India", 679, 332);
		nodesPanel.addNode("18Middle East", 572, 338);
		nodesPanel.addNode("19Japan", 861, 213);
		nodesPanel.addNode("20Ural", 645, 152);      // 20
		nodesPanel.addNode("21Yakutsk", 763, 70);
		nodesPanel.addNode("22Kamchatka", 827, 94);
		nodesPanel.addNode("23Siam", 751, 360);
		nodesPanel.addNode("24Irkutsk", 750, 140);
		nodesPanel.addNode("25Siberia", 695, 108);
		nodesPanel.addNode("26Mongolia", 760, 216);
		nodesPanel.addNode("27China", 735, 277);
		nodesPanel.addNode("28E Australia", 889, 537);
		nodesPanel.addNode("29New Guinea", 850, 429);
		nodesPanel.addNode("30W Australia", 813, 526);       // 30
		nodesPanel.addNode("31Indonesia", 771, 454);
		nodesPanel.addNode("32Venezuela", 274, 437);
		nodesPanel.addNode("33Peru", 292, 511);
		nodesPanel.addNode("34Brazil", 360, 500); //{289,415},
		nodesPanel.addNode("35Argentina", 304, 608);//
		nodesPanel.addNode("36Congo", 496, 462);
		nodesPanel.addNode("37N Africa", 440, 393);
		nodesPanel.addNode("38S Africa", 510, 532);
		nodesPanel.addNode("39Egypt", 499, 354);
		nodesPanel.addNode("40E Africa", 547, 432);        // 40
		nodesPanel.addNode("41Madagascar", 586, 545);
		
	}
}