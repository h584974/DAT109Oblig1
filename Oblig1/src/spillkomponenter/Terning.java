package spillkomponenter;

import java.util.Random;

public class Terning {
	
	private static Random random = new Random();
	
	public Terning() {}
	
	/**
	 * Simulerer terningkast
	 * @return Verdi for kastet, fra 1-6
	 */
	public int kastTerning() {
		return random.nextInt(6) + 1;
	}

}