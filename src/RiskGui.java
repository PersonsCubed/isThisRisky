import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RiskGui {

	public JFrame frame = new JFrame("Risk");
	public static JButton deckOfCards = new JButton("Cards");

	public RiskGui() throws IOException {
		initialize(null);
	}

	private void initialize(Graphics g) throws IOException {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		Container contentPane = frame.getContentPane();

		File file = new File("Map.jpg");
		BufferedImage img = ImageIO.read(file);
		Image ReImage = img.getScaledInstance(frame.getWidth() - 320,
				frame.getHeight(), Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(ReImage));
		background.setOpaque(true);
		contentPane.add(background, BorderLayout.WEST);

		deckOfCards.setBounds(43, 588, 73, 93);
		deckOfCards.setBackground(Color.yellow.darker());
		deckOfCards.setForeground(Color.yellow.darker().darker().darker());

		frame.add(deckOfCards);

		background.setOpaque(true);
		frame.add(new initialSetUp());
		frame.add(background, BorderLayout.WEST);
		CommandPanel command = new CommandPanel();
		command.setBackground(SystemColor.black);
		frame.add(command);

		frame.pack();

	}

	@SuppressWarnings("serial")
	class initialSetUp extends JPanel {
		Info2 info = new Info2();
		Info1 info1 = new Info1();

		public initialSetUp() {
			setBounds(0, 0, 1800, 1010);
			setOpaque(false);
		}

		@SuppressWarnings("static-access")
		public void paintComponent(Graphics g) {

			int temp;
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setStroke(new BasicStroke(2));
			for (int i = 0; i < 42; i++) {				//code for node edges

				if (i < 9)
					g.setColor(Color.yellow.darker());
				else if (i > 8 && i < 16)
					g.setColor(Color.blue.darker());
				else if (i > 15 && i < 28)
					g.setColor(Color.green.darker());
				else if (i > 27 && i < 32)
					g.setColor(Color.decode("#747d7d"));
				else if (i > 31 && i < 36)
					g.setColor(Color.decode("#cf5300"));
				else if (i > 35 && i < 42)
					g.setColor(Color.decode("#2c2416"));

				// draws line between two points
				if ((i != 8) && (i!= 22)) {
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						if((i==14)&&(j==2))
							g.setColor(Color.black);
						else if ((i==16)&&(j==4))
							g.setColor(Color.black);
						else if ((i==18)&&(j==4))
							g.setColor(Color.black);
						else if ((i==20)&&(j==3))
							g.setColor(Color.black);
						else if(((i==31)||(i==32))&&(j==2))
							g.setColor(Color.black);
						else if ((i==37)&&(j==3))
							g.setColor(Color.black);
						else if ((i==39)&&(j==2))
							g.setColor(Color.black);
						else if ((i==40)&&(j==5))
							g.setColor(Color.black);
						
						g2d.drawLine(info.getX(i) + 10, info.getY(i) + 7,
								info.getX(temp) + 10, info.getY(temp) + 7);
					}
				}
				else if(i==8){												//wrap-around line from alaska to kamtcha
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						g.drawLine(info.getX(i) + 10, info.getY(i) + 7,
								info.getX(temp) + 10, info.getY(temp) + 7);
					}
					g.setColor(Color.black);
					g2d.drawLine(info.getX(i)+10, info.getY(i)+7,20,200);
				}
				else{													//wrap-around line from kamatcha to alaska
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						g2d.drawLine(info.getX(i) + 10, info.getY(i) + 7,
								info.getX(temp) + 10, info.getY(temp) + 7);
					}
					g.setColor(Color.black);
					g2d.drawLine(info.getX(i)+10, info.getY(i)+7,1040,220);
				}
			}
			for (int i = 0; i < 42; i++) {									//draws a node for each territory
				if (i <= 8)
					g.setColor(Color.yellow);
				else if (i > 8 && i < 16)
					g.setColor(Color.blue);
				else if (i > 15 && i < 28)
					g.setColor(Color.green);
				else if (i > 27 && i < 32)
					g.setColor(Color.decode("#2f4f4f"));
				else if (i > 31 && i < 36)
					g.setColor(Color.decode("#ff7f00"));
				else if (i > 35 && i < 42)
					g.setColor(Color.decode("#663300"));

				g.fillOval(info.getX(i), info.getY(i), 25, 25);

				g.setColor(Color.white);
				g.drawString("1", info.getX(i) , info.getY(i)); // draws the number 1(1 army for start) in the nodes
				
				
				g.setColor(Color.black);
				g.setFont(new Font("Helvetica", Font.BOLD, 12));
				g.drawString(info1.getName(i), (info.getX(i) + 15),
						info.getY(i));
			}

			// draw deck of cards
			g.setColor(Color.yellow.darker());
			g.fillRect(25, 600, 80, 100);
			g.setColor(Color.white);
			g.drawRect(28, 603, 73, 93);
			g.drawRect(29, 604, 71, 91);

			g.setColor(Color.yellow.darker());
			g.fillRect(30, 595, 80, 100);
			g.setColor(Color.yellow.darker().darker().darker());
			g.drawRect(33, 598, 73, 93);
			g.drawRect(34, 599, 71, 91);

			g.setColor(Color.yellow.darker());
			g.fillRect(35, 590, 80, 100);
			g.setColor(Color.white);
			g.drawRect(38, 593, 73, 93);
			g.drawRect(39, 594, 71, 91);

			g.setColor(Color.yellow.darker());
			g.fillRect(40, 585, 80, 100);
			g.setColor(Color.yellow.darker().darker().darker());
			g.drawRect(43, 588, 73, 93);
			g.drawRect(44, 589, 71, 91);
			g.drawString("CARDS", 60, 640);

		}
	}
}