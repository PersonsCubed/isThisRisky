
public class Player {//Player class to set name and territories of player objects
	
	private String name;
	private int size = 0;
	private int numArmy = 9;
	private String [] territories = new String[50];

	public Player(){ 
	}
	
	public Player(String nameInput, int numArmyInput){ 
		name = nameInput;
		numArmy = numArmyInput;
	}
	public void setName(String x){ 
		name = x;
	}
	
	public void setTerritories(int y, String w){
		territories[y] = w;
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
}
