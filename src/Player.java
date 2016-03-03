
import java.util.ArrayList;


public class Player {//Player class to set name and territories of player objects
	
	private String name;
	private String sPlayerColour;
	private int numTerritories = 0;
	private int numArmy = 45;
	private int i = 0;
	private ArrayList<Territory> TerritoriesOwned = new ArrayList<Territory>();
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
	
	public void setTerritories(String sCountryName,int x,int y){
		TerritoriesOwned.add(new Territory(this.name,sCountryName, 1,x,y));
		numTerritories++;
	}
	
	public int getNumArmy(){
		return numArmy;
	}
	
	public String printTerritories(){
		
		String allTerritories = "";
		
		for(i=0; i<numTerritories; i++){
			allTerritories += TerritoriesOwned.get(i).getCountryName() + "\n";
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
	public int iNumberTerritories(){
		return TerritoriesOwned.size();
	}
	public Territory getTerritory(int i){
		return TerritoriesOwned.get(i);
	}
}

