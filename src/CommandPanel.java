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
	private int iNumDiceRolled=1;
	private int P1roll;
	private int P2roll;
	private int iIsThereAny = 0;
	private String sTextDisplayed = "";
	private Player GoesFirst;
	private Player GoesSecond;

	private String sPlayer_1_name = null;
	private String sPlayer_2_name = null;
	private String sTempTerritoryName;
	private Info1 info1 = new Info1();
	private Info2 info2 = new Info2();

	private JTextArea textArea = new JTextArea();
	private JTextPane userInputTextPane = new JTextPane();
	private JButton userInputButton = new JButton();
	private JLabel ouputLabel = new JLabel();

	// declares 6 object "Player" being 2 user players and 4 neutrals
	public Player player1 = new Player();
	public Player player2 = new Player();
	public Player playerN1 = new Player("Mary", 33);
	public Player playerN2 = new Player("John", 33);
	public Player playerN3 = new Player("Ann", 33);
	public Player playerN4 = new Player("Simon", 33);

	// declare array of object Card
	private Card[] arrayCards = new Card[42];
	
	private static String [] ColourList={ "red", "blue", "green", "yellow", "pink", "white"};
	private static String [] ColourListhex={ "#DC143C", "#4682B4", "#3CB371", "#FFFF00", "#FF1493", "#FFFFF0"};
	private static int hexIndex;
	
	public String setColour(){
		 //return random color from the array of possible colors, makes the array smaller to avoid repeating colours.
			Random randNum = new Random();
			int index=randNum.nextInt(ColourList.length);
			hexIndex=index;
			System.out.println(hexIndex);
			String delete=ColourList[index];
			ColourList=removeListElements(ColourList,delete);
			return delete;		
	}
	
	public String getHexColour(){
		String temp=ColourListhex[hexIndex];
		ColourListhex=removeListElements(ColourListhex,ColourListhex[hexIndex]);
		return temp;
	}
	
	public String[] removeListElements(String Colours [], String delete){
		String[] result =new String[Colours.length-1];
		int j=0;
		for(int i=0; i<Colours.length;i++){
			if(Colours[i]!=delete){
				result[j]=Colours[i];
				j++;
			}
		}
		return result;
	}
	
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
				String sP1Colour=setColour();
				player1.setColour(getHexColour());
				userPrompt("Hi " + sPlayer_1_name + "!\n");
				userPrompt("You have been allocated the colour " +sP1Colour+ "!\n");
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
				String sP2Colour=setColour();
				player2.setColour(getHexColour());
				userPrompt("Hi " + sPlayer_2_name + "\n\nYou have been allocated the colour " +sP2Colour+ "!\n" );
	            userPrompt("Now lets lay!\n\n");
	            String sPN1Colour=setColour();
				playerN1.setColour(getHexColour());
				String sPN2Colour=setColour();
				playerN2.setColour(getHexColour());
				String sPN3Colour=setColour();
				playerN3.setColour(getHexColour());
				String sPN4Colour=setColour();
				playerN4.setColour(getHexColour());
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
	                		userPrompt("Roll dice to see who places their infantry first!\n\n" +player1.getName()+": Roll your dice!\n");

							iNumCardsDrawn++;
							
							//userPrompt(player1.getName() + " type the territory namewhere 3\nof your armys will be placed:\n ");
						}
					}
				});
				  RiskGui.dice.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                	if(iNumDiceRolled==1){
		                		P1roll=RollDice();
		                		userPrompt("You got "+P1roll+"\n\n"); 
		                		RiskGui.dice.setIcon(new ImageIcon("diceFace"+P1roll+".jpg"));
		                		userPrompt(player2.getName()+": Roll your dice!\n");
		                		iNumDiceRolled++;
		                	}
		                	else if(iNumDiceRolled==2){
		                		P2roll=RollDice();
		                		userPrompt("You got "+P2roll+"\n\n"); 
		                		RiskGui.dice.setIcon(new ImageIcon("diceFace"+P2roll+".jpg"));
		                		iNumDiceRolled++;
		                	
		                	if((iNumDiceRolled==3)&&(P1roll==P2roll)){
		                		userPrompt("Looks like a draw!\nPlayers must roll again:\n"); 
		                		iNumDiceRolled=1;
		                    	userPrompt("Roll dice to see who places their infantry first!\n\n" +player1.getName()+": Roll your dice!\n");
		                	}
		                	else if((iNumDiceRolled==3)&&(P1roll>P2roll)){
		                		GoesFirst=player1;
		                		GoesSecond=player2;
		                		userPrompt(player1.getName()+" wins! Allocate your territories first\n\n"); 
		        				userPrompt(player1.getName() + " type the territory name where 3\nof your armys will be placed:\n ");
		                		iNumDiceRolled++;
		                	}
		                	
		                	else if((iNumDiceRolled==3)&&(P1roll<P2roll)){
		                		GoesFirst=player2;
		                		GoesSecond=player1;
		                		userPrompt(player2.getName()+" wins! Allocate your territories first\n\n"); 
		        				userPrompt(player2.getName() + " type the territory namewhere 3\nof your armys will be placed:\n ");
		        				iNumDiceRolled++;
		                	}
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
					compareTerritoryName(GoesFirst);						
					if(iIsThereAny==1){
						userPrompt(GoesSecond.getName() + " type the territory name where 3\nof your armys will be placed:\n ");					
					}
				}
				
				//player 2 turn
				else if (iNumClicks%2 == 0	&& sTempTerritoryName.length() > 0) {
					compareTerritoryName(GoesSecond);								
												
					if(iNumClicks<27 && iIsThereAny==1){
						userPrompt(GoesFirst.getName() + " type the territory namewhere 3\nof your armys will be placed:\n ");
					}
					else if(iNumClicks==27){
						setNeutralsTerritories(playerN1);
						setNeutralsTerritories(playerN2);
						setNeutralsTerritories(playerN3);
						setNeutralsTerritories(playerN4);
						
						userPrompt("All armies have now been allocated!\n ");
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
	
	public Card drawTerritoryCard(){
		 //return random colour from the array of possible colours, makes the array smaller to avoid repeating colours.
			Random randNum = new Random();
			int index=randNum.nextInt(arrayCards.length);
			Card delete=arrayCards[index];
			arrayCards=removeListElements(arrayCards,delete);
			return delete;		
	}
	public Card[] removeListElements(Card arrayCards2 [], Card delete){
		Card result[] = new Card[(arrayCards2.length)-1];
		int j=0;
		for(int i=0; i<arrayCards2.length;i++){
			if(arrayCards2[i]!=delete){
				result[j]=arrayCards2[i];
				j++;
			}
		}
		return result;
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

		for(int i=0; i<(player.iNumberTerritories()); i++){
			if(sTempTerritoryName.equalsIgnoreCase(player.getTerritory(i).getCountryName())){	
				iIsThereAny++;
				temp=i;
			}
		}
		
		if(iIsThereAny==1){ //if only one result is found (expected)
			player.getTerritory(temp).addArmys(3);
			userPrompt(player.getTerritory(temp).getCountryName() + " has now " + player.getTerritory(temp).getNumArmys() + " armys.\n");
			sTempTerritoryName=null;
			iNumClicks++;		
			userInputTextPane.setText("");
		}
		
		else if(iIsThereAny>1){ //if more than one result is found therefore name typed is ambiguous 
			userPrompt("Territory is abiguos,\nplease enter a valid territory name:\n");
			userInputTextPane.setText("");
			sTempTerritoryName = userInputTextPane.getText();
			sTempTerritoryName=null;
		}
		
		else{ //if no result was found
			userPrompt("Territory could not be found,\nplease enter a valid territory name:\n");
			userInputTextPane.setText("");
			sTempTerritoryName = userInputTextPane.getText();
			sTempTerritoryName=null;
		}											
	}
	
	public void printInfo(Player player){ //test the allocation of territories
		for(int i=0; i<player.iNumberTerritories(); i++){
			System.out.println(player.getTerritory(i).getCountryName() + " no army: " + player.getTerritory(i).getNumArmys() + " " + player.getTerritory(i).getControllingName());
		}
		System.out.println("\n\n");
	}
	public int RollDice(){
		int result;
		Random randNum = new Random();
		result=randNum.nextInt(5)+1;//number between 1 and 6
		return result;
		
	}

}
		
