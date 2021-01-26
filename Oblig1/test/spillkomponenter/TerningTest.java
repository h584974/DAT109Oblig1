package spillkomponenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TerningTest {
	
	private Terning terning= new Terning();
	
	@Test
	public void testKastTerning() {
		for(int i = 0; i < 1000000; i++) {
			int kast = terning.kastTerning();
			Assertions.assertTrue(kast > 0 && kast < 7);
		}
	}

}
