<<<<<<< HEAD

import java.util.ArrayList;
import java.util.Random;

=======
import java.util.Random;
>>>>>>> a1234df69c79a67863dd39057042f2032fe6f025

public class Player {//Player class to set name and territories of player objects
	
	private String name;
<<<<<<< HEAD
	private String sPlayerColour;
	private static String [] ColourList={ "red", "blue", "green", "yellow", "pink", "white"};
	private static String [] ColourListhex={ "#DC143C", "#4682B4", "#3CB371", "#FFFF00", "#FF1493", "#FFFFF0"};
	private static int hexIndex=0;
	private int numTerritories = 0;
	private int numArmy = 45;
=======
	private int size = 0;
	private int numArmy = 36;
>>>>>>> a1234df69c79a67863dd39057042f2032fe6f025
	private int i = 0;
	Territory territory;
	private String [] territories = new String[50];
	private Card[] arrayCards = new Card[20];
	//private String PlayerColour;
	private static String [] ColourList={ "red", "blue", "green", "yellow", "pink", "white"};
	//private static String [] ColourListhex={ "#DC143C", "#4682B4", "#3CB371", "#FFFF00", "#FF1493", "#FFFFF0"};

	public Player(){ 
		
	}
<<<<<<< HEAD

=======
	
>>>>>>> a1234df69c79a67863dd39057042f2032fe6f025
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
	
	public String setColour(){
		 //return random colour from the array of possible colours, makes the array smaller to avoid repeating colours.
			Random randNum = new Random();
			int index=randNum.nextInt(ColourList.length);
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


	
	public void setCard(Card x){
		arrayCards[i]=x;
		i++;
	}
	
	public String getCard(){
		String s = arrayCards[i-1].getTerritoryNameCard() + " - " + arrayCards[i-1].getTypeArmy();
		return s;
	}
}

