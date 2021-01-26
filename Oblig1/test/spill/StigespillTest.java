package spill;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spillkomponenter.Brikke;

public class StigespillTest {
	
	private static Stigespill spill;
	private static Brikke[] brikker;
 	
	@BeforeEach
	public void reset() {
		Brikke b1 = new Brikke("Bl�");
		Brikke b2 = new Brikke("R�d");
		brikker = new Brikke[2];
		brikker[0] = b1;
		brikker[1] = b2;
		spill = new Stigespill(brikker);
	}
	
	@Test
	public void testNesteBrikke() {
		Assertions.assertTrue(spill.getNesteBrikke().equals("Bl�"));
		spill.spillNesteTur();
		Assertions.assertTrue(spill.getNesteBrikke().equals("R�d"));
		spill.spillNesteTur();
		Assertions.assertTrue(spill.getNesteBrikke().equals("Bl�"));
	}

}
