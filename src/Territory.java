
public class Territory { //Current Territory Object class which will be potentially incremented in the future
	
	private String sCountryName;
	private String sControllingName;
	private int iNumArmys;
	private int[] iArmyTypes = new int [3]; // 0-infantry 1-cavalry 2-artillery
	private int [] iCoordinates = new int [2]; //0-xCo-Ordinate 1-yCo-Ordinate
	
	public Territory(String x, int y){
		x = sControllingName;
		y = iNumArmys;
	}
	
	public Territory(){
		
	}
	
	public String getControllingName(){
		return sControllingName;
	}
	
	public int getNumArmys(){
		return iNumArmys;
	}
	
}
