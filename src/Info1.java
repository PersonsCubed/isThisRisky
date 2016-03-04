
public class Info1 {
	public static final int NUM_PLAYERS = 2;
	public static final int NUM_NEUTRALS = 4;
	public static final int NUM_PLAYERS_PLUS_NEUTRALS = NUM_PLAYERS + NUM_NEUTRALS;
	public static final int NUM_COUNTRIES = 42;
	public static final int INIT_COUNTRIES_PLAYER = 9;
	public static final int INIT_COUNTRIES_NEUTRAL = 6;
	public static final int INIT_UNITS_PLAYER = 36;
	public static final String[] COUNTRY_NAMES = {
		"Ontario","Quebec","NW Territory","Alberta","Greenland","E United States","W United States","Central America","Alaska",
		"Great Britain","W Europe","S Europe","Ukraine","N Europe","Iceland","Scandinavia",
		"Afghanistan","India","Middle East","Japan","Ural","Yakutsk","Kamchatka","Siam","Irkutsk","Siberia","Mongolia","China",
		"E Australia","New Guinea","W Australia","Indonesia",
		"Venezuela","Peru","Brazil","Argentina",
		"Congo","N Africa","S Africa","Egypt","E Africa","Madagascar"};  // for reference
	
	public static final int[][] ADJACENT = { 
		{4,1,5,6,3,2},    // 0
		{4,5,0,},
		{4,0,3,8},
		{2,0,6,8},
		{14,1,0,2}, //greenland-4
		{0,1,7,6}, 
		{3,0,5,7},
		{6,5,32},//central america-7
		{2,3}, //alaska -8
		{14,15,13,10},    
		{9,13,11,37},     // 10	w europe
		{13,12,18,39,10},//s europe-11
		{20,16,18,11,13,15},//ukraine-12
		{15,12,11,10,9},
		{15,9,4},//iceland-14
		{12,13,14},
		{20,27,17,18,12}, //afgan -16
		{16,27,23,18},
		{16,17,40,39,11,12},// midleeast-18
		{26,22},
		{25,27,16,12},    // 20 ural
		{22,24,25},        
		{19,26,24,21}, // kamatcha--22
		{27,31,17}, //siam-23
		{21,22,26,25},
		{21,24,26,27,20},
		{24,22,19,27,25},
		{26,23,17,16,20,25},
		{29,30},          
		{28,30,31},
		{29,28,31},      // 30
		{29,30,23},	//indonisia-31
		{34,33,7},   //venezulla-32    
		{32,34,35},
		{32,37,35,33},//brazill-34
		{33,34},
		{37,40,38},      
		{39,40,36,34,10,11},//nafrica-37
		{36,40,41},
		{40,37,11,18},// egypt-39
		{39,41,38,36,37,18},  //40 east africa
		{38,40}
	};
	
	public String getTerritories(int x){//method to return a string from the array of strings of territories names
		return COUNTRY_NAMES[x];	
	}
	
	public String getName(int i){
		return COUNTRY_NAMES[i];
	}
	
	public int getAdjacents(int i, int j){
		return ADJACENT[i][j]; 
	}
}
	


