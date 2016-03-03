import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.IOException;
import java.util.Random;

public class CommandPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int iNumClicks = 1;
	private int iNumCardsDrawn = 1;
	private int iIsThereAny = 0;
	private String sTextDisplayed = "";
	private String sPlayer_1_name = null;
	private String sPlayer_2_name = null;
	private String sTempTerritoryName = null;
	private Info1 info1 = new Info1();
	private Info2 info2 = new Info2();

	private JTextArea textArea = new JTextArea();
	private JTextPane userInputTextPane = new JTextPane();
	private JButton userInputButton = new JButton();
	private JLabel ouputLabel = new JLabel();

	// declares 6 object "Player" being 2 user players and 4 neutrals
	public Player player1 = new Player("#000000");
	public Player player2 = new Player("#ff0000");
	public Player playerN1 = new Player("Mary", 33,"#ffff00");
	public Player playerN2 = new Player("John", 33,"#ffc0cb");
	public Player playerN3 = new Player("Ann", 33, "#000000");
	public Player playerN4 = new Player("Simon", 33,"#000000");

	// declare array of object Card
	private Card[] arrayCards = new Card[42];
		
	public CommandPanel() throws IOException {
		
		buildLayout();

		userPrompt("\nWelcome to Risk -\nthe World Conquest Game\n\n");
		userPrompt("Player 1, Enter your name:");

		userInputButton.addActionListener(new addAction());

		buildCards();

		drawTerritoryCard();
	}
		
	class addAction implements ActionListener { // implements actions to the
												// "send" button
		public void actionPerformed(ActionEvent e) {

			// if it is the first click and something is typed to the JTextPane,
			// it assigns the string entered to player 1,
			// display greeting message and asks for the next player's name

			if (iNumClicks == 1 && ((sPlayer_1_name = userInputTextPane.getText()).length() > 0)) {
				sPlayer_1_name = userInputTextPane.getText();
				userInputTextPane.setText("");
				player1.setName(sPlayer_1_name);
				userPrompt("Hi " + sPlayer_1_name + "!\n");
				userPrompt("Player 2, Enter your name:");
				iNumClicks++;
			}

			// If it is the second click and something is typed to the
			// JTextPane, it assigns the string entered to player 2,
			// display greeting messages and the territories allocated for each
			// of the user players

			else if (iNumClicks == 2 && ((sPlayer_2_name = userInputTextPane.getText()).length() > 0) && sPlayer_1_name!=sPlayer_2_name ) {

				userInputTextPane.setText("");
				player2.setName(sPlayer_2_name);
				userPrompt("Hi " + sPlayer_2_name + "!\n\nNow let's play!\n\n");
				
				assignTerritories();

				userPrompt(player1.getName()
						+ ", one army was alocated in\neach of the below territories:\n\n"
						+ player1.printTerritories() + "\n"
						+ "Total number of armys: " + player1.getNumArmy()
						+ "\n\n" + "              *****************");

				userPrompt(player2.getName()
						+ ", one army was alocated in\neach of the below territories:\n\n"
						+ player2.printTerritories() + "\n"
						+ "Total number of armys: " + player2.getNumArmy()
						+ "\n\n" + "              *****************");
				
				repaint();
				
				userPrompt(player1.getName()
						+ ", draw a card\nfrom the deck:\n");

				// activates deckOfCards button
				RiskGui.deckOfCards.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("deck active");
						if (iNumCardsDrawn == 1) {
							player1.setCard(drawTerritoryCard());
							userPrompt(player1.getName() + ", you got\n"
									+ player1.getCard() + "\n\n");
							userPrompt(player2.getName()
									+ ", draw a card\nfrom the deck:\n\n");
							iNumCardsDrawn++;
						} else if (iNumCardsDrawn == 2) {
							player2.setCard(drawTerritoryCard());
							userPrompt(player2.getName() + ", you got\n"
									+ player2.getCard() + "\n\n" + "              *****************\n");
							iNumCardsDrawn++;
							
							userPrompt(player1.getName() + " type the territory namewhere 3\nof your armys will be placed:\n ");
						}
					}
				});

				iNumClicks++;
			}
			
			//users take turn to place 3 armies in a territory
			
			else if (iNumClicks>=3 && iNumClicks<28){
					
				sTempTerritoryName = userInputTextPane.getText();
				
				//player 1 turn
				if (iNumClicks%2 == 1 && sTempTerritoryName.length() > 0) {
						
					compareTerritoryName(player1);						
						
					if(iIsThereAny==1){
						userPrompt(player2.getName() + " type the territory name where 3\nof your armys will be placed:\n ");					
					}
				}
				
				//player 2 turn
				else if (iNumClicks%2 == 0	&& sTempTerritoryName.length() > 0) {
					
					compareTerritoryName(player2);								
												
					if(iNumClicks<27 && iIsThereAny==1){
						userPrompt(player1.getName() + " type the territory namewhere 3\nof your armys will be placed:\n ");
					}
					else if(iNumClicks==27){
						setNeutralsTerritories(playerN1);
						setNeutralsTerritories(playerN2);
						setNeutralsTerritories(playerN3);
						setNeutralsTerritories(playerN4);
						
						userPrompt("All armies have now been allocated!\n ");
						
						/*
						//test to see if territories were alocated properly
						printInfo(player1);
						printInfo(player2);
						printInfo(playerN1);
						printInfo(playerN2);
						printInfo(playerN3);
						printInfo(playerN4);*/
					}
				}				
			}
		}
	}

	private void assignTerritories() { // assign territories to user and neutral players

		for (int i = 0, j = 9; i < 9; i++) { // loop to assign 9 territories to each of the two user players

			player1.setTerritories(allocateTerritories(i), info2.getX(i),info2.getY(i));
			player2.setTerritories(allocateTerritories(j), info2.getX(j),info2.getY(j));
			j++;
		}

		for (int i = 0, k = 18, l = 24, m = 30, n = 36; i < 6; i++) {// loop to assign 6 territories to
																// each of the four neutral players
			playerN1.setTerritories(allocateTerritories(k), info2.getX(k),info2.getY(k));
			playerN2.setTerritories(allocateTerritories(l), info2.getX(l),info2.getY(l));
			playerN3.setTerritories(allocateTerritories(m), info2.getX(m),info2.getY(m));
			playerN4.setTerritories(allocateTerritories(n), info2.getX(n),info2.getY(n));

			k++;
			l++;
			m++;
			n++;
		}

	}

	public String allocateTerritories(int i) {

		return info1.getTerritories(i); // uses the getTerritories method defined in Info1 to get territories names from the array
	}
	
	public void linkToGraphics(Graphics g){
		repaint();
	}

	public Card drawTerritoryCard() { // return random Territory card

		Random rand = new Random();

		return arrayCards[rand.nextInt(41) + 0];
	}

	public void buildCards() { // build 42 object cards containing name of territory and type of army

		arrayCards[0] = new Card(Info1.COUNTRY_NAMES[0], "Cavalry");
		arrayCards[1] = new Card(Info1.COUNTRY_NAMES[1], "Artillery");
		arrayCards[2] = new Card(Info1.COUNTRY_NAMES[2], "Artillery");
		arrayCards[3] = new Card(Info1.COUNTRY_NAMES[3], "Infantry");
		arrayCards[4] = new Card(Info1.COUNTRY_NAMES[4], "Cavalry");
		arrayCards[5] = new Card(Info1.COUNTRY_NAMES[5], "Artillery");
		arrayCards[6] = new Card(Info1.COUNTRY_NAMES[6], "Infantry");
		arrayCards[7] = new Card(Info1.COUNTRY_NAMES[7], "Cavalry");
		arrayCards[8] = new Card(Info1.COUNTRY_NAMES[8], "Infantry");
		arrayCards[9] = new Card(Info1.COUNTRY_NAMES[9], "Cavalry");
		arrayCards[10] = new Card(Info1.COUNTRY_NAMES[10], "Infantry");
		arrayCards[11] = new Card(Info1.COUNTRY_NAMES[11], "Cavalry");
		arrayCards[12] = new Card(Info1.COUNTRY_NAMES[12], "Artillery");
		arrayCards[13] = new Card(Info1.COUNTRY_NAMES[13], "Cavalry");
		arrayCards[14] = new Card(Info1.COUNTRY_NAMES[14], "Infantry");
		arrayCards[15] = new Card(Info1.COUNTRY_NAMES[15], "Artillery");
		arrayCards[16] = new Card(Info1.COUNTRY_NAMES[16], "Infantry");
		arrayCards[17] = new Card(Info1.COUNTRY_NAMES[17], "Infantry");
		arrayCards[18] = new Card(Info1.COUNTRY_NAMES[18], "Artillery");
		arrayCards[19] = new Card(Info1.COUNTRY_NAMES[19], "Infantry");
		arrayCards[20] = new Card(Info1.COUNTRY_NAMES[20], "Cavalry");
		arrayCards[21] = new Card(Info1.COUNTRY_NAMES[21], "Cavalry");
		arrayCards[22] = new Card(Info1.COUNTRY_NAMES[22], "Cavalry");
		arrayCards[23] = new Card(Info1.COUNTRY_NAMES[23], "Artillery");
		arrayCards[24] = new Card(Info1.COUNTRY_NAMES[24], "Infantry");
		arrayCards[25] = new Card(Info1.COUNTRY_NAMES[25], "Artillery");
		arrayCards[26] = new Card(Info1.COUNTRY_NAMES[26], "Artillery");
		arrayCards[27] = new Card(Info1.COUNTRY_NAMES[27], "Cavalry");
		arrayCards[28] = new Card(Info1.COUNTRY_NAMES[28], "Infantry");
		arrayCards[29] = new Card(Info1.COUNTRY_NAMES[29], "Cavalry");
		arrayCards[30] = new Card(Info1.COUNTRY_NAMES[30], "Artillery");
		arrayCards[31] = new Card(Info1.COUNTRY_NAMES[31], "Cavalry");
		arrayCards[32] = new Card(Info1.COUNTRY_NAMES[32], "Artillery");
		arrayCards[33] = new Card(Info1.COUNTRY_NAMES[33], "Cavalry");
		arrayCards[34] = new Card(Info1.COUNTRY_NAMES[34], "Artillery");
		arrayCards[35] = new Card(Info1.COUNTRY_NAMES[37], "Infantry");
		arrayCards[36] = new Card(Info1.COUNTRY_NAMES[36], "Cavalry");
		arrayCards[37] = new Card(Info1.COUNTRY_NAMES[37], "Infantry");
		arrayCards[38] = new Card(Info1.COUNTRY_NAMES[38], "Artillery");
		arrayCards[39] = new Card(Info1.COUNTRY_NAMES[39], "Infantry");
		arrayCards[40] = new Card(Info1.COUNTRY_NAMES[40], "Artillery");
		arrayCards[41] = new Card(Info1.COUNTRY_NAMES[41], "Infantry");

	}

	public void buildLayout() { // sets the layout of main JPanel and its components

		Dimension size = getPreferredSize();
		size.width = 320;
		setPreferredSize(size);
		setBorder(BorderFactory.createLineBorder(Color.black, 20));

		JLabel enterCommandLabel = new JLabel("Enter command: ");
		enterCommandLabel.setForeground(Color.WHITE);
		enterCommandLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		enterCommandLabel.setBackground(SystemColor.controlHighlight);

		userInputTextPane.setPreferredSize(new Dimension(200, 23));

		userInputButton.setText("Send");
		userInputButton.setFont(new Font("Trajan Pro", Font.PLAIN, 11));

		ouputLabel.setText("Ouput: ");
		ouputLabel.setForeground(Color.WHITE);
		ouputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		ouputLabel.setBackground(SystemColor.controlHighlight);

		textArea.setEditable(false); // display only
		textArea.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		textArea.setForeground(Color.red);
		textArea.setBackground(Color.black);

		JScrollPane scrollingTextArea = new JScrollPane(textArea);
		scrollingTextArea.setBorder(null);
		scrollingTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		/////uses GidBagLayout to set the layout of the main JPanel//////

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.weightx = 0.5;
		gc.weighty = 0.5;

		gc.gridx = 0;
		gc.gridy = 0;
		add(enterCommandLabel, gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.HORIZONTAL;
		add(userInputTextPane, gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(userInputButton, gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(ouputLabel, gc);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.weighty = 50;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;
		add(scrollingTextArea, gc);

	}

	public void userPrompt(String prompt) { // builds an string with all game's outputs so far and displays it at the JTextArea
		sTextDisplayed += prompt + "\n";
		textArea.setText(sTextDisplayed);
	}

	public void setNeutralsTerritories(Player neutralPlayer) { //allocate armies to neutral's territories
		for(int i=0; i<27; i++){
			Random rand = new Random(); 	
			neutralPlayer.getTerritory(rand.nextInt(6)).addArmys(1);
		}		
	}

	public void compareTerritoryName(Player player) { //checks if territory name typed by user is valid
		
		int temp=0;
		iIsThereAny=0;

		for(int i=0; i<player.iNumberTerritories(); i++){
			if(sTempTerritoryName.equalsIgnoreCase(player.getTerritory(i).getCountryName().substring(0, sTempTerritoryName.length()))){						
				iIsThereAny++;
				temp=i;
			}
		}
		
		if(iIsThereAny==1){ //if only one result is found (expected)
			player.getTerritory(temp).addArmys(3);
			userPrompt(player.getTerritory(temp).getCountryName() + " has now " + player.getTerritory(temp).getNumArmys() + " armys.\n");
			iNumClicks++;		
			userInputTextPane.setText("");
		}
		
		else if(iIsThereAny>1){ //if more than one result is found therefore name typed is ambiguous 
			userPrompt("Territory is abiguos,\nplease enter a valid territory name:\n");
			userInputTextPane.setText("");
			sTempTerritoryName = userInputTextPane.getText();
		}
		
		else{ //if no result was found
			userPrompt("Territory could not be found,\nplease enter a valid territory name:\n");
			userInputTextPane.setText("");
			sTempTerritoryName = userInputTextPane.getText();
		}											
	}
	
	public void printInfo(Player player){ //test the allocation of territories
		for(int i=0; i<player.iNumberTerritories(); i++){
			System.out.println(player.getTerritory(i).getCountryName() + " no army: " + player.getTerritory(i).getNumArmys() + " " + player.getTerritory(i).getControllingName());
		}
		System.out.println("\n\n");
	}
}
		
