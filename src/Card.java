
public class Card {
	
	private String territoryNameCard;
	private String typeArmy;
	
	public Card (String territoryName, String army){
		territoryNameCard = territoryName;
		typeArmy = army;
	}
	
	public Card(){
		
	}
	
	public String getTerritoryNameCard(){
		return territoryNameCard;
	}
	
	public String getTypeArmy(){
		return typeArmy;
	}
}
