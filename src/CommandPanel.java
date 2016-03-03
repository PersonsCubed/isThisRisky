
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.util.Random;

public class CommandPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numClicks=1; 
	private int numCardsDrawn=1;
	private int iNumDiceRolled=1;
	private String textDisplayed="";
	private String player_1_name=null;
	private String player_2_name=null;
	private int P1roll;
	private int P2roll;
	
	
	private JTextArea textArea = new JTextArea();
	private JTextPane userInputTextPane = new JTextPane();
	private JButton userInputButton = new JButton();
	/*frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"clickButton");

			f.getRootPane().getActionMap().put("clickButton",new AbstractAction(){
			        public void actionPerformed(ActionEvent ae)
			        {
			    userInputButton.doClick();
			    System.out.println("button clicked");
			        }
			    });
			    } */

	private JLabel ouputLabel = new JLabel();
	
	//declares 6 object "Player" being 2 user players and 4 neutrals
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player playerN1 = new Player("Mary", 24);
    String sPN1Colour=playerN1.setColour();
    private Player playerN2 = new Player("John", 24);
    String sPN2Colour=playerN2.setColour();
	private Player playerN3 = new Player("Ann", 24);
	String sPN3Colour=playerN3.setColour();
	private Player playerN4 = new Player("Simon", 24);
	String sPN4Colour=playerN4.setColour();
	
	
	//declare array of object Card
	private static Card[] arrayCards = new Card[42];

	public CommandPanel() throws IOException{
	       
        buildLayout();

		userPrompt("\nWelcome to Risk -\nthe World Conquest Game\n\n");
		userPrompt("Player 1, Enter your name:");

		userInputButton.addActionListener(new addAction()); 
	//	userInputTextPane.getRootPane().setDefaultButton(userInputButton);
		
		assignTerritories();
		buildCards();
		
		drawTerritoryCard();
	}
	
	/*private Card drawTerritoryCard() {  //return random Territory card
		
		Random rand = new Random();

		return arrayCards[rand.nextInt(41) + 0];
		
	}*/
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

	private void assignTerritories() { //assign territories to user and neutral players
		
		for (int i = 0, j = 9; i < 9; i++) { //loop to assign 9 territories to each of the two user players
			
			player1.setTerritories(i, allocateTerritories(i));
			player2.setTerritories(i, allocateTerritories(j));
			
			j++;
		}
		
		for (int i = 0, k = 18, l = 24, m = 30, n = 36; i < 6; i++) {//loop to assign 6 territories to each of the four neutral players

			playerN1.setTerritories(i, allocateTerritories(k));
			playerN2.setTerritories(i, allocateTerritories(l));
			playerN3.setTerritories(i, allocateTerritories(m));
			playerN4.setTerritories(i, allocateTerritories(n));
			
			k++;
			l++;
			m++;
			n++;
		}	
		
	}

	private void buildCards() { //build 42 object cards containing name of territory and type of army
		
		arrayCards[0]= new Card(Info1.COUNTRY_NAMES[0], "Cavalry"); 
		arrayCards[1]= new Card(Info1.COUNTRY_NAMES[1], "Artillery");
		arrayCards[2]= new Card(Info1.COUNTRY_NAMES[2], "Artillery");
		arrayCards[3]= new Card(Info1.COUNTRY_NAMES[3], "Infantry");
		arrayCards[4]= new Card(Info1.COUNTRY_NAMES[4], "Cavalry");
		arrayCards[5]= new Card(Info1.COUNTRY_NAMES[5], "Artillery");
		arrayCards[6]= new Card(Info1.COUNTRY_NAMES[6], "Infantry");
		arrayCards[7]= new Card(Info1.COUNTRY_NAMES[7], "Cavalry");
		arrayCards[8]= new Card(Info1.COUNTRY_NAMES[8], "Infantry");		
		arrayCards[9]= new Card(Info1.COUNTRY_NAMES[9], "Cavalry");
		arrayCards[10]= new Card(Info1.COUNTRY_NAMES[10], "Infantry");
		arrayCards[11]= new Card(Info1.COUNTRY_NAMES[11], "Cavalry");
		arrayCards[12]= new Card(Info1.COUNTRY_NAMES[12], "Artillery");
		arrayCards[13]= new Card(Info1.COUNTRY_NAMES[13], "Cavalry");
		arrayCards[14]= new Card(Info1.COUNTRY_NAMES[14], "Infantry");
		arrayCards[15]= new Card(Info1.COUNTRY_NAMES[15], "Artillery");
		arrayCards[16]= new Card(Info1.COUNTRY_NAMES[16], "Infantry");
		arrayCards[17]= new Card(Info1.COUNTRY_NAMES[17], "Infantry");		
		arrayCards[18]= new Card(Info1.COUNTRY_NAMES[18], "Artillery");
		arrayCards[19]= new Card(Info1.COUNTRY_NAMES[19], "Infantry");
		arrayCards[20]= new Card(Info1.COUNTRY_NAMES[20], "Cavalry");
		arrayCards[21]= new Card(Info1.COUNTRY_NAMES[21], "Cavalry");
		arrayCards[22]= new Card(Info1.COUNTRY_NAMES[22], "Cavalry");
		arrayCards[23]= new Card(Info1.COUNTRY_NAMES[23], "Artillery");
		arrayCards[24]= new Card(Info1.COUNTRY_NAMES[24], "Infantry");
		arrayCards[25]= new Card(Info1.COUNTRY_NAMES[25], "Artillery");
		arrayCards[26]= new Card(Info1.COUNTRY_NAMES[26], "Artillery");		
		arrayCards[27]= new Card(Info1.COUNTRY_NAMES[27], "Cavalry");
		arrayCards[28]= new Card(Info1.COUNTRY_NAMES[28], "Infantry");
		arrayCards[29]= new Card(Info1.COUNTRY_NAMES[29], "Cavalry");
		arrayCards[30]= new Card(Info1.COUNTRY_NAMES[30], "Artillery");
		arrayCards[31]= new Card(Info1.COUNTRY_NAMES[31], "Cavalry");
		arrayCards[32]= new Card(Info1.COUNTRY_NAMES[32], "Artillery");
		arrayCards[33]= new Card(Info1.COUNTRY_NAMES[33], "Cavalry");
		arrayCards[34]= new Card(Info1.COUNTRY_NAMES[34], "Artillery");
		arrayCards[35]= new Card(Info1.COUNTRY_NAMES[37], "Infantry");		
		arrayCards[36]= new Card(Info1.COUNTRY_NAMES[36], "Cavalry");
		arrayCards[37]= new Card(Info1.COUNTRY_NAMES[37], "Infantry");
		arrayCards[38]= new Card(Info1.COUNTRY_NAMES[38], "Artillery");
		arrayCards[39]= new Card(Info1.COUNTRY_NAMES[39], "Infantry");
		arrayCards[40]= new Card(Info1.COUNTRY_NAMES[40], "Artillery");
		arrayCards[41]= new Card(Info1.COUNTRY_NAMES[41], "Infantry");
		
	}

	private void buildLayout() { //sets the layout of main JPanel and its components
		
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
		System.out.println("making panels");
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
		gc.weighty =50;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;
		add(scrollingTextArea, gc);
		//userInputTextPane.getRootPane().setDefaultButton(userInputButton);
	
	}

	public void userPrompt(String prompt) { //builds an string with all game's outputs so far and displays it at the JTextArea
		textDisplayed+=prompt+"\n";
    	textArea.setText(textDisplayed);	
	}
	
	class addAction implements ActionListener {  //implements actions to the "send" button
		
		public void actionPerformed(ActionEvent e) {
			//userInputTextPane.getRootPane().setDefaultButton(userInputButton);
			//if it is the first click and something is typed to the JTextPane, it assigns the string entered to player 1,
			//display greeting message and asks for the next player's name 
			
			if(numClicks==1 && ((player_1_name = userInputTextPane.getText()).length()>0)){
				player_1_name = userInputTextPane.getText();
				userInputTextPane.setText("");
				player1.setName(player_1_name);
				String sP1Colour=player1.setColour();
				userPrompt("Hi " + player_1_name + "!\n");
				userPrompt("You have been allocated the colour " +sP1Colour+ "!\n");
				userPrompt("Player 2, Enter your name:");
				numClicks++;
			}
			//If it is the second click and something is typed to the JTextPane, it assigns the string entered to player 2, 
			//display greeting messages and the territories allocated for each of the user players
			
			else if(numClicks==2 && ((player_2_name = userInputTextPane.getText()).length()>0||((player_1_name= userInputTextPane.getText())=="/n"))){
	            	
				userInputTextPane.setText("");
	            player2.setName(player_2_name);
	            String sP2Colour=player2.setColour();
	            userPrompt("Hi " + player_2_name + "\n\nYou have been allocated the colour " +sP2Colour+ "!\n" );
	            userPrompt("Now lets lay!\n\n");
				//userPrompt("Neutral Player 1 has been allocated the colour " +sPN1Colour+ "!\n");
				//userPrompt("Neutral Player 2 has been allocated the colour " +sPN2Colour+ "!\n");
				//userPrompt("Neutral Player 3 has been allocated the colour " +sPN3Colour+ "!\n");
				//userPrompt("Neutral Player 4 has been allocated the colour " +sPN4Colour+ "!\n");

	            
	        	userPrompt(player1.getName() + ", one army was alocated in\neach of the below territories:\n\n" 
	            + player1.printTerritories() + "\n" + "Total number of armys: " + player1.getNumArmy() + "\n\n" + "              *****************");
	        	
	        	userPrompt(player2.getName() + ", one army was alocated in\neach of the below territories:\n\n" 
	    	    + player2.printTerritories() + "\n" + "Total number of armys: " + player2.getNumArmy() + "\n\n");
	        	
	        	userPrompt("Now " + player1.getName() + ", draw a card\nfrom the deck:\n\n");
	        	
	        	//activates deckOfCards button	
	        	RiskGui.deckOfCards.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	if(numCardsDrawn==1){
	                		player1.setCard(drawTerritoryCard());
	                		userPrompt(player1.getName() + ", you got\n" + player1.getCard() + "\n\n");
	                		userPrompt("Now " + player2.getName() + ", draw a card\nfrom the deck:\n\n");
	                		numCardsDrawn++;
	                	}
	                	else if(numCardsDrawn==2){
	                		player2.setCard(drawTerritoryCard());	           
	                		userPrompt(player2.getName() + ", you got\n" + player2.getCard() + "\n\n");
	                		userPrompt("Roll dice to see who places their infantry first!\n\n" +player1.getName()+": Roll your dice!\n");
	                		numCardsDrawn++;
	                		
	                	}
	       
	                }
	             });
	            RiskGui.dice.addActionListener(new ActionListener() {
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                	//System.out.print(iNumDiceRolled+"\n")
	                	/*if(iNumDiceRolled==1){
	                	//userPrompt("Now " + player1.getName() + ", draw a card\nfrom the deck:\n\n");
	                		//userPrompt("You got "+RollDice()+"\n\n"); 
	                		iNumDiceRolled++;
		                	System.out.print("2\n");
	                	}*/
	                	if(iNumDiceRolled==1){
	                		P1roll=RollDice();
	                		userPrompt("You got "+P1roll+"\n\n"); 
	                		RiskGui.dice.setIcon(new ImageIcon("diceFace"+P1roll+".jpg"));
	                		userPrompt(player2.getName()+": Roll your dice!\n");
	                		//userPrompt("Player 2: Roll your dice!\n");
	                		//userPrompt("You got "+RollDice()+"\n\n"); 
	                		iNumDiceRolled++;
		                	//System.out.print("1\n");

	                	}
	                	else if(iNumDiceRolled==2){
	                		P2roll=RollDice();
	                	//	userPrompt("Player 2: Roll your dice!\n");
	                		userPrompt("You got "+P2roll+"\n\n"); 
	                		RiskGui.dice.setIcon(new ImageIcon("diceFace"+P2roll+".jpg"));
	                		iNumDiceRolled++;
		                	//System.out.print("2\n inum dice = "+iNumDiceRolled);
	              
	                	
	                	if((iNumDiceRolled==3)&&(P1roll==P2roll)){
	                		userPrompt("Looks like a draw!\nPlayers must roll again:\n"); 
	                		iNumDiceRolled=1;
	                    	userPrompt("Roll dice to see who places their infantry first!\n\n" +player1.getName()+": Roll your dice!\n");
		                	System.out.print("5\n");

	                	}
	                	else if((iNumDiceRolled==3)&&(P1roll>P2roll)){
	                		userPrompt(player1.getName()+" wins! Allocate your territories first\n\n"); 
	                		iNumDiceRolled++;
		                	System.out.print("6\n");

	                	}
	                	
	                	else if((iNumDiceRolled==3)&&(P1roll<P2roll)){
	                		userPrompt(player2.getName()+" wins! Allocate your territories first\n\n"); 
	                		iNumDiceRolled++;
		                	System.out.print("7\n");

	                	}
	                	}
	            }
	                
			});
	            numClicks++;
		}
	              

			else if((userInputTextPane.getText().length())>0){
				userInputTextPane.setText("");
				userPrompt("Command invalid");
			}	
		}
	}
	public String allocateTerritories(int i) { 
		Info1 Info = new Info1(); //uses the Info1.java file to access the array of strings storing the list of territories
		String P = Info.getTerritories(i); //uses the getTerritories method defined in Info1 to get territories names from the array
		return P;
	}
	public int RollDice(){
		int result;
		Random randNum = new Random();
		result=randNum.nextInt(5)+1;//number between 1 and 6
		return result;
		
	}

}

