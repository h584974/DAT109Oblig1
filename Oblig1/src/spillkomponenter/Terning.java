package spillkomponenter;

import java.util.Random;

public class Terning {
	
	private static Random random = new Random();
	
	public Terning() {}
	
	public int kastTerning() {
		return random.nextInt(6) + 1;
	}

}
