
public class Territory { //Current Territory Object class which will be potentially incremented in the future
	
	private String sCountryName;
	private String sControllingName;
	private int iNumArmys;
	private int[] iArmyTypes = new int [3]; // 0-infantry 1-cavalry 2-artillery
	private int [] iCoordinates = new int [2]; //0-xCo-Ordinate 1-yCo-Ordinate
	
	public Territory(String sController,String sCountry, int iNumSoilders,int xCord,int yCord){
		sControllingName=sController;
		setCountryName(sCountry);
		iNumArmys=iNumSoilders;
		iCoordinates[0]=xCord;
		iCoordinates[1]=yCord;
		iArmyTypes [0] = 1;
	}
	
	public Territory(){
		
	}
	
	public String getControllingName(){
		return sControllingName;
	}
	
	public int getNumArmys(){
		return iNumArmys;
	}

	public String sGetCountryName() {
		return sCountryName;
	}

	public void setCountryName(String sCountryName) {
		this.sCountryName = sCountryName;
	}
	public int iGetXT(){
		return iCoordinates[0];
	}
	public int iGetYT(){
		return iCoordinates[1];
	}
}