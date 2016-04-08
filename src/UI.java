
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UI {

	private static final int FRAME_WIDTH = 1360;
	private static final int FRAME_HEIGHT = 710;
	private static String PROMPT = "- ";

	private JFrame frame = new JFrame();
	private MapPanel mapPanel;	
	private InfoPanel infoPanel = new InfoPanel();
	private CommandPanel commandPanel = new CommandPanel();
	private Parse parse = new Parse();
	private Board board;
	
	UI (Board inBoard) {
		board = inBoard;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Risk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints layout = new GridBagConstraints();
		frame.getContentPane().setBackground(Color.BLACK);
		mapPanel = new MapPanel(board);
		
		layout.fill = GridBagConstraints.BOTH;
		layout.gridx=0;
		layout.gridy=0;
		layout.gridheight=GridBagConstraints.REMAINDER;
		layout.gridwidth=GridBagConstraints.RELATIVE;
		layout.weightx=1;
		layout.weighty=2;
		frame.add(mapPanel,layout);
		
		JLabel input = new JLabel();
		input.setText("Input :");
		input.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		input.setForeground(Color.RED);
		input.setBackground(Color.BLACK);
		input.setOpaque(true);
		layout.fill = GridBagConstraints.BOTH;
		layout.gridx=2;
		layout.gridy=0;
		layout.gridheight=1;
		layout.gridwidth=GridBagConstraints.REMAINDER;
		layout.weightx=0;
		layout.weighty=0;
		frame.add(input,layout);
		
		layout.fill = GridBagConstraints.BOTH;
		layout.gridx=2;
		layout.gridy=1;
		layout.gridheight=1;
		layout.gridwidth=GridBagConstraints.REMAINDER;
		layout.weightx=0;
		layout.weighty=0;
		layout.insets=new Insets(10,0,0,0);
		frame.add(commandPanel,layout);
		
		JLabel ouputLabel = new JLabel();
		ouputLabel.setText("Ouput: ");
		ouputLabel.setForeground(Color.RED);
		ouputLabel.setBackground(Color.BLACK);
		ouputLabel.setFont(new Font("Trajan Pro", Font.PLAIN, 11));
		ouputLabel.setOpaque(true);
		layout.fill = GridBagConstraints.BOTH;
		layout.gridx=2;
		layout.gridy=2;
		layout.gridheight=1;
		layout.gridwidth=GridBagConstraints.REMAINDER;
		layout.weightx=0;
		layout.weighty=0;
		frame.add(ouputLabel,layout);
		
		layout.fill = GridBagConstraints.BOTH;
		layout.gridx=2;
		layout.gridy=3;
		layout.gridheight=GridBagConstraints.REMAINDER;
		layout.gridwidth=GridBagConstraints.REMAINDER;
		layout.weightx=0;
		layout.weighty=0;
		frame.add(infoPanel,layout);
		
		frame.setResizable(false);
		frame.setVisible(true);
		return;
	}
	
	public int getCountryId () {
		return parse.getCountryId();
	}
	
	public int getNumUnits () {
		return parse.getNumUnits();
	}	
	
	public boolean isTurnEnded() {
		return parse.isTurnEnded();
	}
	
	public int getFromCountryId () {
		return parse.getFromCountryId();
	}
	
	public int getToCountryId () {
		return parse.getToCountryId();
	}
	
	private String makeLongName (Player player) {
		return player.getName() + " (" + mapPanel.getColorName(player.getId()) + ")";
	}
	
	public void displayMap () {
		mapPanel.refresh();
		return;
	}

	public void displayString (String string) {
		infoPanel.addText(string);
		return;
	}
	
	public void displayName (int playerId, String name) {
		displayString("Neutral player " + (playerId+1) + " is " + mapPanel.getColorName(playerId));
		return;		
	}

	public void displayCardDraw (Player player, Card card) {
		displayString(makeLongName(player) + " draws the " + card.getCountryName() + " card");
		return;
	}
	
	public void displayDice (Player player) {
		displayString(makeLongName(player) + " rolls " + player.getDice() );
		return;
	}
	
	public void displayRollWinner (Player player) {
		displayString(makeLongName(player)  + " wins roll and goes first");
		return;
	}
	
	public void displayReinforcements (Player player, int numUnits) {
		displayString(makeLongName(player) + " gets " + numUnits + " reinforcements.");
		return;
	}
	
	public void displayNumUnits (Player player) {
		String message = makeLongName(player) + " has " + player.getNumUnits() + " units";
		displayString(message);
		return;
	}
	
	public void displayBattle (Player attackPlayer, Player defencePlayer) {
		String message;
		message = makeLongName(attackPlayer) + "'s roll was " + attackPlayer.getDice() + " and ";
		message += makeLongName(defencePlayer) + "'s roll was " + defencePlayer.getDice();
		displayString(message);
		if (attackPlayer.getBattleLoss()==1) {
			message = makeLongName(attackPlayer) + " loses 1 unit and ";
		} else {
			message = makeLongName(attackPlayer) + " loses " + attackPlayer.getBattleLoss() + " units and ";			
		}
		if (defencePlayer.getBattleLoss()==1) {
			message += makeLongName(defencePlayer) + " loses 1 unit";	
		} else {
			message += makeLongName(defencePlayer) + " loses " + defencePlayer.getBattleLoss() + " units";		
		}
		displayString(message);
		return;
	}
	
	public void displayWinner (Player player) {
		displayString(makeLongName(player) + " wins the game!");
		return;
	}
		
	public String inputName (int playerId) {
		String response;
		displayString("Enter the name for player " + (playerId+1) + " (" + mapPanel.getColorName(playerId) + "):");
		response = commandPanel.getCommand();
		response.trim();
		displayString(PROMPT + response+"\n");
		return response;		
	}
		
	public void inputReinforcement (Player player) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(player) + ": Enter a country to reinforce\nand the number of units";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response+"\n");
			parse.countryNumber(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Incorrect command\n");
				infoPanel.setForeground(Color.RED);
			} else if (board.getOccupier(getCountryId()) != player.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot place the units in that country\n");
				infoPanel.setForeground(Color.RED);
			} else if (parse.getNumUnits() > player.getNumUnits()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Not enough units\n");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;
	}

	public void inputPlacement (Player byPlayer, Player forPlayer) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(byPlayer) + "Enter a country occupied by\n" + makeLongName(forPlayer) + " to reinforce by 1 unit";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response+"\n");
			parse.country(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Not a country\n");
				infoPanel.setForeground(Color.RED);
			} else if (board.getOccupier(getCountryId()) != forPlayer.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot place the units in that country\n");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;
	}

	public void inputBattle (Player player) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(player) + "Enter country to attack from, country \nto attack and number of units to use,\n or enter skip";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response+"\n");
			parse.countryCountryNumber(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Incorrect command\n");
				infoPanel.setForeground(Color.RED);
			} else if (parse.isTurnEnded()) {
				responseOK = true;
			} else if (board.getOccupier(getFromCountryId()) != player.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot invade from that country\n");
				infoPanel.setForeground(Color.RED);
			} else if (board.getOccupier(getToCountryId()) == player.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot invade your own country\n");
				infoPanel.setForeground(Color.RED);
			} else if (!board.isAdjacent(getFromCountryId(),parse.getToCountryId())) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Countries not neighbours\n");
				infoPanel.setForeground(Color.RED);
			} else if (getNumUnits() >= board.getNumUnits(getFromCountryId())) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Not enough units in the attacking country, must leave 1 behind\n");
				infoPanel.setForeground(Color.RED);
			} else if (board.getNumUnits(getFromCountryId()) < GameData.ATTACK_MIN_IN_COUNTRY) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Must have 2 or more units in the attacking country\n");
				infoPanel.setForeground(Color.RED);
			} else if (parse.getNumUnits() > GameData.ATTACK_MAX) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: The maximum number of units that can used to attack is 3\n");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;
	}
	
	public void inputDefence (Player player, int countryId) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(player) + "Enter number of units to defend with\n";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response);
			parse.number(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Incorrect command\n");
				infoPanel.setForeground(Color.RED);
			} else if (getNumUnits() > GameData.DEFEND_MAX) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Maximum of 2 defenders\n");
				infoPanel.setForeground(Color.WHITE);
			} else if (getNumUnits() > board.getNumUnits(countryId)) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Not enough units in the country\n");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;		
	}

	public void inputMoveIn (Player player, int attackCountryId) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(player) + "How many units do you wish to move in";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response+"\n");
			parse.number(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Incorrect command");
				infoPanel.setForeground(Color.RED);
			} else if (getNumUnits() >= board.getNumUnits(attackCountryId)) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Insufficient units in attacking country,\nnote you must leave at least 1 behind");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;		
	}
	
	public void inputFortify (Player player) {
		String response, message;
		boolean responseOK = false;
		do {
			message = makeLongName(player) + "Enter country to move units from,\ncountry to fortify and number of \nunits to move, or enter skip\n";
			displayString(message);
			response = commandPanel.getCommand();
			displayString(PROMPT + response);
			parse.countryCountryNumber(response);
			if (parse.isError()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Incorrect command");
				infoPanel.setForeground(Color.RED);
			} else if (parse.isTurnEnded()) {
				responseOK = true;
			} else if (board.getOccupier(getFromCountryId()) != player.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot move from the origin country, you do not occupy it");
				infoPanel.setForeground(Color.RED);
			} else if (board.getOccupier(getToCountryId()) != player.getId()) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Cannot move to the destination country, you do not occupy it");
				infoPanel.setForeground(Color.RED);
			} else if (!board.isConnected(getFromCountryId(),parse.getToCountryId())) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Countries are not connected by your occupied territories");
				infoPanel.setForeground(Color.RED);
			} else if (getNumUnits() >= board.getNumUnits(getFromCountryId())) {
				infoPanel.setForeground(Color.WHITE);
				displayString("Error: Not enough units in the origin\ncountry, note you must leave at least 1 behind\n");
				infoPanel.setForeground(Color.RED);
			} else {
				responseOK = true;
			}
		} while (!responseOK);
		return;		
	}
}