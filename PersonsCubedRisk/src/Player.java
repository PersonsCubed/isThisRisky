
public class Player {//Player class to set name and territories of player objects
	
	private String name;
	private int size=0;
	private String [] territories = new String[50];

	public Player(){ 
	}
	
	public void setName(String x){ 
		name=x;
	}
	
	public void setTerritories(int y,String w ){
		territories [y] =w;
		size++;
	}
	
	public String printTerritories(){
		
		String allTerritories="";
		
		for(int i=0; i<size; i++){
			allTerritories+= territories[i]+"\n";
		}
		
		return allTerritories;
	}
	
	public String getName(){
		return name;
	}
}
