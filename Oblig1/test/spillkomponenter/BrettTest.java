package spillkomponenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrettTest {
	
	private Brett brett;
	private Brikke b1;
	private Brikke b2;
	private Logg logg;
	
	@BeforeEach
	public void reset() {
		b1 = new Brikke("Blå");
		b2 = new Brikke("Rød");
		brett = new Brett();
		logg = new Logg();
	}
	
	@Test
	public void testSisteOgFoersteKast() {
		brett.spillTur(b1, 6, logg);
		brett.spillTur(b1, 3, logg);
		Assertions.assertEquals(b1.getForrigeKast(),6);
		Assertions.assertEquals(b1.getSisteKast(),3);
	}
	
	@Test
	public void testBrikkeFanget() {
		brett.spillTur(b1, 6, logg);
		brett.spillTur(b1, 6, logg);
		brett.spillTur(b1, 6, logg);
		Assertions.assertTrue(b1.getFanget());
	}
	
	@Test
	public void testStigeSlange() {
		brett.spillTur(b1, 1, logg);
		brett.spillTur(b2, 6, logg);
		brett.spillTur(b2, 6, logg);
		brett.spillTur(b2, 5, logg);
		brett.spillTur(b2, 6, logg);
		Assertions.assertEquals(b1.getPosisjon(),39);
		Assertions.assertEquals(b2.getPosisjon(),4);
	}
	
	@Test
	public void testHarVunnet() {
		b1.setPosisjon(97);
		Assertions.assertTrue(brett.spillTur(b1, 2, logg));
	}
}
