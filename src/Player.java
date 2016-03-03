
import java.util.ArrayList;
import java.util.Random;


public class Player {//Player class to set name and territories of player objects
	
	private String name;
	private String sPlayerColour;
	private static String [] ColourList={ "red", "blue", "green", "yellow", "pink", "white"};
	private static String [] ColourListhex={ "#DC143C", "#4682B4", "#3CB371", "#FFFF00", "#FF1493", "#FFFFF0"};
	private static int hexIndex=0;
	private int numTerritories = 0;
	private int numArmy = 45;
	private int i = 0;
	private ArrayList<Territory> TerritoriesOwned = new ArrayList<Territory>();
	private Card[] arrayCards = new Card[20];

	public Player(){ 
		
	}

	public Player(String nameInput, int numArmyInput){ 
		name = nameInput;
		numArmy = numArmyInput;
	}
	
	public String getHexColour(){
		return ColourListhex[hexIndex];
	}
	
	public String setColour(){
		 //return random color from the array of possible colors, makes the array smaller to avoid repeating colours.
			Random randNum = new Random();
			int index=randNum.nextInt(ColourList.length);
			hexIndex=index;
			String delete=ColourList[index];
			ColourList=removeListElements(ColourList,delete);
			return delete;		
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

