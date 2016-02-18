
public class Info2 {
	public static final int NUM_CONTINENTS = 6;
	public static final String[] CONTINENT_NAMES = { "N America", "Europe",
			"Asia", "Australia", "S America", "Africa" }; // for reference
	public static final int[] CONTINENT_IDS = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
			1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3,
			4, 4, 4, 4, 5, 5, 5, 5, 5, 5 };
	public static final int[] CONTINENT_VALUES = { 5, 5, 7, 2, 2, 3 };
	private static final int FRAME_WIDTH = 1000; // must be even
	private static final int FRAME_HEIGHT = 600;
	private static final int[][] COUNTRY_COORD = {
			{ 240, 250 }, // 0 ontario
			{ 235, 190 }, //quebec
			{ 145, 180}, // nw teritory
			{ 180, 250 },// alberta
			{ 360, 110 },//grenland
			{ 250, 320 },//e usa
			{ 180, 310 },//w usa
			{ 200, 350 },//central america
			{ 70, 190 },//namerica alaska
			{ 470, 260 },//europe britain
			{ 495, 320 }, // 10 west europe
			{ 560, 310 },// europe south europe
			{ 600, 260 }, // europe ukraine
			{ 540, 280 }, //europe north europe
			{ 470, 195 },//europe iceland
			{ 540, 216 },//europe scandanavia
			{ 670, 320 },//asia afganistan
			{ 700, 380 },//asia india
			{ 610, 380 },//asia middle east
			{ 885, 360 },//asia japan
			{ 670, 250 }, // 20 asia ural
			{ 827, 200}, //asia yakutusk
			{ 930, 216 }, // asia kamchatka
			{ 765, 410 }, //asia siam
			{ 800, 250 }, // asia irkutsk
			{ 730, 216 }, //asia siberia
			{ 810, 310 }, // asia mogolia
			{ 765, 350 },// asia-china
			{ 879, 635 },//done		Australia-eastern Australia
			{ 890, 515 },//done		Australia-new guinea
			{ 850, 575 }, // 30 done Australia-western Australia
			{ 800, 495 }, //done	Australia-Indonesia
			{ 295, 425 }, //done southAmerica-Venezuela
			{ 315, 520 }, //done southAmerica-peru
			{ 345, 455 }, //done southAmerica-brazil
			{ 315, 590 }, //done south america-argentina
			{ 540, 500 }, //done Africa-congo
			{ 480, 430 }, //done Africa-northAfrica
			{ 550, 570 },//done Africa-south africa
			{ 550, 400 }, //done africa-egypt
			{ 580, 470 }, // 40 //africa-east africa
			{ 625, 540 } };//Africa Madigascar

	public int getContinents() {
		return NUM_CONTINENTS;
	}

	public String getContinentName(int x) {
		return CONTINENT_NAMES[x];
	}

	public int getContinentID(int x) {
		return CONTINENT_IDS[x];
	}

	public int getContinentValues(int x) {
		return CONTINENT_VALUES[x];
	}

	public int getFrameWidth() {
		return FRAME_WIDTH;
	}

	public int getFrameHeight() {
		return FRAME_HEIGHT;
	}
	public int getX(int x){
		return COUNTRY_COORD [x][0];
	}
	public int getY(int x){
		return COUNTRY_COORD [x][1];
	}
}
