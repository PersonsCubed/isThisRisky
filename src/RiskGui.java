import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RiskGui {
	public JFrame frame = new JFrame("Risk");
	public static JButton deckOfCards = new JButton("Cards");
	private CommandPanel command = new CommandPanel();
	private boolean bSwitcher = false;

	public RiskGui() throws IOException {
		initialize(null);
	}

	public void initialize(Graphics g) throws IOException {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		File file = new File("Map.jpg");
		BufferedImage img = ImageIO.read(file);
		Image ReImage = img.getScaledInstance(frame.getWidth() - 320,
				frame.getHeight(), Image.SCALE_SMOOTH);
		JLabel background = new JLabel(new ImageIcon(ReImage));

		command.setBackground(SystemColor.black);
		frame.add(command, BorderLayout.EAST);
		background.add(new initialSetUp());
		frame.add(background, BorderLayout.WEST);
		background.setOpaque(true);
		deckOfCards.setBounds(43, 588, 73, 93);
		deckOfCards.setBackground(Color.yellow.darker());
		deckOfCards.setForeground(Color.yellow.darker().darker().darker());
		frame.add(deckOfCards);
		System.out.println(command.player1.printTerritories());

		frame.pack();
	}

	@SuppressWarnings("serial")
	public class initialSetUp extends JPanel {
		Info2 info2 = new Info2();
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
			command.linkToGraphics(g);
			g2d.setStroke(new BasicStroke(2));

			for (int i = 0; i < 42; i++) { // code for node edges

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
				if ((i != 8) && (i != 22)) {
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						if ((i == 14) && (j == 2))
							g.setColor(Color.black);
						else if ((i == 16) && (j == 4))
							g.setColor(Color.black);
						else if ((i == 18) && (j == 4))
							g.setColor(Color.black);
						else if ((i == 20) && (j == 3))
							g.setColor(Color.black);
						else if (((i == 31) || (i == 32)) && (j == 2))
							g.setColor(Color.black);
						else if ((i == 37) && (j == 3))
							g.setColor(Color.black);
						else if ((i == 39) && (j == 2))
							g.setColor(Color.black);
						else if ((i == 40) && (j == 5))
							g.setColor(Color.black);

						g2d.drawLine(info2.getX(i) + 10, info2.getY(i) + 7,
								info2.getX(temp) + 10, info2.getY(temp) + 7);
					}
				} else if (i == 8) { // wrap-around line from alaska to
										// kamtcha
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						g.drawLine(info2.getX(i) + 10, info2.getY(i) + 7,
								info2.getX(temp) + 10, info2.getY(temp) + 7);
					}
					g.setColor(Color.black);
					g2d.drawLine(info2.getX(i) + 10, info2.getY(i) + 7, 20, 200);
				} else { // wrap-around line from kamatcha to alaska
					for (int j = 0; j < (info1.ADJACENT[i]).length; j++) {
						temp = info1.ADJACENT[i][j];
						g2d.drawLine(info2.getX(i) + 10, info2.getY(i) + 7,
								info2.getX(temp) + 10, info2.getY(temp) + 7);
					}
					g.setColor(Color.black);
					g2d.drawLine(info2.getX(i) + 10, info2.getY(i) + 7, 1040,
							220);
				}
			}
			for (int i = 0; i < 42; i++) { // draws a node for each
											// territory
				if (i <= 8)
					g.setColor(Color.yellow);
				else if (i > 8 && i < 16)
					g.setColor(Color.blue);
				else if (i > 15 && i < 28)
					g.setColor(Color.green);
				else if (i > 27 && i < 32)
					g.setColor(Color.decode("#a9a9a9"));
				else if (i > 31 && i < 36)
					g.setColor(Color.decode("#ff7f00"));
				else if (i > 35 && i < 42)
					g.setColor(Color.decode("#663300"));

				g.fillOval(info2.getX(i), info2.getY(i), 25, 25);
				g.setColor(Color.white);
				g.drawString("1", info2.getX(i), info2.getY(i)); // draws
																	// the
																	// number
																	// 1(1
																	// army
																	// for
																	// start)
																	// in
																	// the
																	// nodes

				g.setColor(Color.black);
				g.setFont(new Font("Helvetica", Font.BOLD, 12));
				g.drawString(info1.getName(i), (info2.getX(i) + 15),
						info2.getY(i));
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

			if (bSwitcher == true) {
				for (int i = ((command.player1.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.player1.getColour()));
					g.fillOval(command.player1.getTerritory(i).iGetXT(),
							command.player1.getTerritory(i).iGetYT(), 15, 15);
				}
				for (int i = ((command.player2.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.player2.getColour()));
					g.fillOval(command.player2.getTerritory(i).iGetXT(),
							command.player2.getTerritory(i).iGetYT(), 15, 15);
				}
				for (int i = ((command.playerN1.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.playerN1.getColour()));
					g.fillOval(command.playerN1.getTerritory(i).iGetXT(),
							command.playerN1.getTerritory(i).iGetYT(), 15, 15);
				}
				for (int i = ((command.playerN2.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.playerN2.getColour()));
					g.fillOval(command.playerN2.getTerritory(i).iGetXT(),
							command.playerN2.getTerritory(i).iGetYT(), 15, 15);
				}
				for (int i = ((command.playerN3.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.playerN3.getColour()));
					g.fillOval(command.playerN3.getTerritory(i).iGetXT(),
							command.playerN3.getTerritory(i).iGetYT(), 15, 15);
				}
				for (int i = ((command.playerN4.iNumberTerritories())-1); i > 0; i--) {
					g.setColor(Color.decode(command.playerN4.getColour()));
					g.fillOval(command.playerN4.getTerritory(i).iGetXT(),
							command.playerN4.getTerritory(i).iGetYT(), 15, 15);
				}
			}
			bSwitcher = true;
		}
	}
}
