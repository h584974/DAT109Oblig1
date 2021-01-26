package spillkomponenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BrikkeTest {
	
	private Brikke b1;
	
	@BeforeEach
	public void reset() {
		b1 = new Brikke("Blå");
	}
	
	@Test
	public void testOppdaterKast() {
		b1.oppdaterKast(1);
		b1.oppdaterKast(2);
		Assertions.assertEquals(b1.getForrigeKast(),1);
		Assertions.assertEquals(b1.getSisteKast(),2);
	}
	
}