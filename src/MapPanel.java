import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class MapPanel extends JPanel {
	
   	BufferedImage img = null;
   	Image ReImage;
	private static final long serialVersionUID = 1L;
	private static final int FRAME_WIDTH = 1000;    // must be even
	private static final int FRAME_HEIGHT = 680;
	private static final int COUNTRY_RADIUS = 12;   // must be even
	private static final int NAME_OFFSET_X = 3;
	private static final int NAME_OFFSET_Y = 13;
	private static final Color TEXT_COLOR = Color.BLACK;
	private static final int ADJACENT_LINE = 1;
	private static final Color ADJACENT_COLOR = Color.LIGHT_GRAY;
	private static final Color[] PLAYER_COLORS = {Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.MAGENTA,Color.WHITE};
	private static final String[] COLOR_NAMES = {"RED","BLUE","YELLOW","GREEN","MAGENTA","WHITE"};
	private static final Color[] CONTINENT_COLORS = {Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.PINK, Color.ORANGE, Color.GRAY};
	private static final int PLAYER_RADIUS = 8;
	private static final int[][] COUNTRY_COORD = {
		{ 240, 250 },	// ontario
		{ 235, 185 },   // quebec
		{ 145, 180},    // nw teritory
		{ 180, 235 },   // alberta
		{ 360, 110 },   // grenland
		{ 250, 305 },   // e usa
		{ 170, 285 },   // w usa
		{ 200, 340 },   // central america
		{ 70, 190 },    // namerica alaska
		{ 455, 250 },   // europe britain
		{ 460, 310 },   // west europe			                10
		{ 530, 300 },   // europe south europe
		{ 570, 245 },   // europe ukraine
		{ 495, 270 },   // europe north europe
		{ 445, 180 },   // europe iceland
		{ 520, 200 },   // europe scandanavia
		{ 630, 300 },   // asia afganistan
		{ 680, 360 },   // asia india
		{ 610, 350 },   // asia middle east
		{ 845, 340 },   // asia japan
		{ 630, 230 },   // asia ural			                20
		{ 787, 180},    // asia yakutusk
		{ 890, 216 },   // asia kamchatka
		{ 735, 400 },   // asia siam
		{ 760, 230 },   // asia irkutsk
		{690, 216 },   // asia siberia
		{ 770, 290 },   // asia mogolia
		{ 765, 350 },   // asia-china
		{ 839, 585 },   // Australia-eastern Australia
		{ 850, 480 },   // Australia-new guinea
		{ 790, 565 },   // Australia-western Australia          30
		{ 760, 455 },   // Australia-Indonesia
		{ 285, 405 },   // southAmerica-Venezuela
		{ 290, 470 },   // southAmerica-peru
		{ 345, 455 },   // southAmerica-brazil
		{ 285, 570 },   // south america-argentina
		{ 515, 475 },   // Africa-congo
		{ 460, 390 },   // Africa-northAfrica
		{ 530, 530 },   // Africa-south africa
		{ 530, 370 },   // africa-egypt
		{ 560, 430 },   // africa-east africa                   40
		{ 595, 520 }	// Africa Madagascar
	};
	
	private Board board;	
	
		MapPanel (Board inBoard) {
	    	File file = new File("Map.jpg");
			try {
				img = ImageIO.read(file);
				ReImage = img.getScaledInstance(FRAME_WIDTH,
						FRAME_HEIGHT, Image.SCALE_SMOOTH);
				setBackground(new Color(0,0,0));
			} 
			catch (IOException e) {
				e.printStackTrace();
				Graphics2D graphics = img.createGraphics();
				graphics.setBackground(new Color(0,0,0));
				graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
			}
		board = inBoard;
		return;
	}

    public void paintComponent(Graphics g) {
    	int xPos, yPos, xPosBegin, yPosBegin, xPosEnd, yPosEnd;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(ReImage,0,0,null);
        // Display adjacent lines
        g2.setStroke(new BasicStroke(ADJACENT_LINE));
        g2.setColor(ADJACENT_COLOR);
        for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
        	xPosBegin = COUNTRY_COORD[i][0];
        	yPosBegin = COUNTRY_COORD[i][1];
        	for (int j=0; j<GameData.ADJACENT[i].length; j++) {
            	xPosEnd = COUNTRY_COORD[GameData.ADJACENT[i][j]][0];
            	yPosEnd = COUNTRY_COORD[GameData.ADJACENT[i][j]][1];
            	if (xPosBegin < xPosEnd) {
	       			if (Math.abs(xPosEnd-xPosBegin)<FRAME_WIDTH/2) {
	        			g2.drawLine(xPosBegin, yPosBegin, xPosEnd, yPosEnd);
	        		} else {
	        			g2.drawLine(0,yPosBegin,xPosBegin,yPosBegin);
	        			g2.drawLine(FRAME_WIDTH-1,yPosEnd,xPosEnd,yPosEnd);
	        		}
            	}
        	}
        }
        // Display countries
        g.setFont(new Font("Times New Roman", Font.BOLD, 12));
        for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
            g2.setColor(CONTINENT_COLORS[GameData.CONTINENT_IDS[i]]);
            xPos = COUNTRY_COORD[i][0] - COUNTRY_RADIUS;
            yPos = COUNTRY_COORD[i][1] - COUNTRY_RADIUS;
			Ellipse2D.Double ellipse = new Ellipse2D.Double(xPos,yPos,2*COUNTRY_RADIUS,2*COUNTRY_RADIUS);
			g2.fill(ellipse);
            g2.setColor(TEXT_COLOR);
            xPos = COUNTRY_COORD[i][0] - GameData.COUNTRY_NAMES[i].length()*NAME_OFFSET_X;
            yPos = COUNTRY_COORD[i][1] - NAME_OFFSET_Y;
			g2.drawString(GameData.COUNTRY_NAMES[i],xPos,yPos);
        }
        // Display players units
        for (int i=0; i<GameData.NUM_COUNTRIES; i++) {
        	if (board.isOccupied(i)) {
                g2.setColor(PLAYER_COLORS[board.getOccupier(i)]);
                xPos = COUNTRY_COORD[i][0] - PLAYER_RADIUS;
                yPos = COUNTRY_COORD[i][1] - PLAYER_RADIUS;
    			Ellipse2D.Double ellipse = new Ellipse2D.Double(xPos,yPos,2*PLAYER_RADIUS,2*PLAYER_RADIUS);
    			g2.fill(ellipse);  
                g2.setColor(TEXT_COLOR);   			
                xPos = COUNTRY_COORD[i][0] - NAME_OFFSET_X;
                yPos = COUNTRY_COORD[i][1] + 2*PLAYER_RADIUS + NAME_OFFSET_Y;  
        		g2.drawString(String.valueOf(board.getNumUnits(i)),xPos,yPos);
        	}
        }
		return;
    }

    public void refresh () {
		revalidate();
		repaint();
		return;
    }
    
    public String getColorName (int playerId) {
    	return COLOR_NAMES[playerId];
    }
}
