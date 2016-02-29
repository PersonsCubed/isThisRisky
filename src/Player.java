
public class Player {//Player class to set name and territories of player objects
	
	private String name;
	private String sPlayerColour;
	private int size = 0;
	private int numArmy = 36;
	private int i = 0;
	Territory territory;
	private String [] territories = new String[50];
	private Card[] arrayCards = new Card[20];

	public Player(String colour){ 
		sPlayerColour = colour;
	}
	
	public Player(String nameInput, int numArmyInput,String colour){ 
		name = nameInput;
		numArmy = numArmyInput;
		sPlayerColour = colour;
	}
	
	public void setName(String x){ 
		name = x;
	}
	
	public void setTerritories(int y, String w){
		territories[y] = w;
		territory = new Territory(this.name, 1);
		size++;
	}
	
	public int getNumArmy(){
		return numArmy;
	}
	
	public String printTerritories(){
		
		String allTerritories = "";
		
		for(int i=0; i<size; i++){
			allTerritories += territories[i] + "\n";
		}
		
		return allTerritories;
	}
	
	public String getName(){
		return name;
	}
	
	public void setCard(Card x){
		
		arrayCards[i]=x;
		i++;
	}
	
	public String getCard(){
		String s = arrayCards[i-1].getTerritoryNameCard() + " - " + arrayCards[i-1].getTypeArmy();
		return s;
	}
	public void setColour (String x){
		sPlayerColour = x;
	}
	public String getColour(){
		return sPlayerColour;
	}
}

