
//This class is going to be implemented in future sprints when it is necessary to deal with multiple dice

public class Dice {

   private int faceValue;

   // mutator method for the dice class

   public void roll() {
      faceValue = 1 + (int)(Math.random()*6.0);
   }

   // the accessor method for the dice

   public int value(){
      return faceValue;
   }

  // the constructor for dice objects.  Starts them
  // with a roll.
  
  public Dice(){
     roll();
  }

}






/*
public class Dice {
	
	
	
//public Dice(){ 
		
	//}
public int [] RollDice(int numDice){
	int result []=null;
	int i=0;
	Random randNum = new Random();
	for(i=0;i<numDice;i++){
	result[i]=randNum.nextInt(5)+1;//number between 1 and 6
	}
	return result;
	
}
/*public String[] InitialRoll(){
	int result1;
	int result2;
	String outcome;
	Random randNum = new Random();
	result1=randNum.nextInt(5)+1;//number between 1 and 6
	result2=randNum.nextInt(5)+1;//number between 1 and 6
	if(result1==result2){
		outcome=("")
		
	}

	return null;
	
}
*/

