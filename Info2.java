
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
			{ 191, 150 }, // 0
			{ 255, 161 }, 
			{ 146, 86 }, 
			{ 123, 144 },
			{ 314, 61 },
			{ 205, 235 },
			{ 135, 219 },
			{ 140, 299 },
			{ 45, 89 },
			{ 370, 199 },
			{ 398, 280 }, // 10
			{ 465, 270 }, 
			{ 547, 180 }, 
			{ 460, 200 }, 
			{ 393, 127 },
			{ 463, 122 },
			{ 628, 227 },
			{ 679, 332 },
			{ 572, 338 },
			{ 861, 213 },
			{ 645, 152 }, // 20
			{ 763, 70 }, 
			{ 827, 94 }, 
			{ 751, 360 }, 
			{ 750, 140 }, 
			{ 695, 108 },
			{ 760, 216 }, 
			{ 735, 277 },
			{ 889, 537 },
			{ 850, 429 },
			{ 813, 526 }, // 30
			{ 771, 454 }, 
			{ 213, 352 }, 
			{ 221, 426 }, 
			{ 289, 415 },
			{ 233, 523 }, 
			{ 496, 462 }, 
			{ 440, 393 }, 
			{ 510, 532 },
			{ 499, 354 }, 
			{ 547, 432 }, // 40
			{ 586, 545 } };

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
