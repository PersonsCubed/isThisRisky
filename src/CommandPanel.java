import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.io.IOException;

public class CommandPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numClicks=1; 
	private String textDisplayed="";
	private String player_1_name=null;
	private String player_2_name=null;
	
	private JTextArea textArea = new JTextArea();
	private JTextPane userInputTextPane = new JTextPane();
	private JButton userInputButton = new JButton();
	private JLabel ouputLabel = new JLabel();
	
	//declares 6 object "Player" being 2 user players and 4 neutrals
	private Player player1 = new Player();
	private Player player2 = new Player();
	private Player playerN1 = new Player("Mary", 6);
	private Player playerN2 = new Player("John", 6);
	private Player playerN3 = new Player("Ann", 6);
	private Player playerN4 = new Player("Simon", 6);

	public CommandPanel() throws IOException{
		
		//sets the size and border of the main JPanel which will contains all the components below
		
		Dimension size = getPreferredSize();
        size.width = 320;
        setPreferredSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black, 20));
         
        //sets the appearance of the components
	    
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
		
		////////********/////////
		
		userPrompt("\nWelcome to Risk -\nthe World Conquest Game\n\n");
		userPrompt("Player 1, Enter your name:");

		userInputButton.addActionListener(new addAction()); 
		
		for (int i = 0, j = 9; i < 9; i++) { //loop to assign 9 territories to each of the two user players
			player1.setTerritories(i, allocateTerritories(i));
			player2.setTerritories(i, allocateTerritories(j));
			
		//	System.out.println(player1.territories[i]); //print statements to check territories are all different for each player
		//	System.out.println(player2.territories[i]);
			
			j++;
		}
		
		for (int i = 0, k = 18, l = 24, m = 30, n = 36; i < 6; i++) {//loop to assign 6 territories to each of the four neutral players

			playerN1.setTerritories(i, allocateTerritories(k));
			playerN2.setTerritories(i, allocateTerritories(l));
			playerN3.setTerritories(i, allocateTerritories(m));
			playerN4.setTerritories(i, allocateTerritories(n));
		
		//	System.out.println(playerN1.territories[i]);
		//	System.out.println(playerN2.territories[i]);
		//	System.out.println(playerN3.territories[i]);
		//	System.out.println(playerN4.territories[i]);
			
			k++;
			l++;
			m++;
			n++;
		}		
	}
	
	public void userPrompt(String prompt) { //builds an string with all game's outputs so far and displays it at the JTextArea
		textDisplayed+=prompt+"\n";
    	textArea.setText(textDisplayed);	
	}
	
	class addAction implements ActionListener {  //implements actions to the "send" button
		public void actionPerformed(ActionEvent e) {
			
			//if it is the first click and something is typed to the JTextPane, it assigns the string entered to player 1,
			//display greeting message and asks for the next player's name 
			
			if(numClicks==1 && ((player_1_name = userInputTextPane.getText()).length()>0)){
				player_1_name = userInputTextPane.getText();
				userInputTextPane.setText("");
				player1.setName(player_1_name);
				userPrompt("Hi " + player_1_name + "!\n");
				userPrompt("Player 2, Enter your name:");
				numClicks++;
			}
			
			//If it is the second click and something is typed to the JTextPane, it assigns the string entered to player 2, 
			//display greeting messages and the territories allocated for each of the user players
			
			else if(numClicks==2 && ((player_2_name = userInputTextPane.getText()).length()>0)){
	            	
				userInputTextPane.setText("");
	            player2.setName(player_2_name);
	            userPrompt("Hi " + player_2_name + "!\n\nNow let's play!\n\n" );
	            
	        	userPrompt(player1.getName() + ", one army was alocated in\neach of the below territories:\n\n" 
	            + player1.printTerritories() + "\n" + "Total number of armys: " + player1.getNumArmy() + "\n\n" + "              *****************");
	        	
	        	userPrompt(player2.getName() + ", one army was alocated in\neach of the below territories:\n\n" 
	    	    + player1.printTerritories() + "\n" + "Total number of armys: " + player2.getNumArmy() + "\n\n");
	            numClicks++;
	        }
			
			//if an invalid command is entered to the TextPane, it displays "Command invalid" message 
			else if(numClicks>2 && ((userInputTextPane.getText().length())>0)){
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

}
