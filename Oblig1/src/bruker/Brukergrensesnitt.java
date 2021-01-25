package bruker;

import java.util.Scanner;
import spill.Stigespill;
import spillkomponenter.Brikke;

public class Brukergrensesnitt {
	
	private Scanner scanner;
	
	public Brukergrensesnitt() {
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Starter brukergresesnittet
	 */
	public void start() {
		System.out.println("Velg fra 2-4 spillere: ");
		int antallSpillere = 0;
		while(true) {
			try {
				antallSpillere = Integer.parseInt(scanner.next());
				if(antallSpillere < 2 || antallSpillere > 4) {
					System.out.println("Velg fra 2-4 spillere: ");
				}
				else {
					break;
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Valg må være tall fra 2-4: ");
			}
		}
		
		Brikke[] brikker = new Brikke[antallSpillere];
		
		for(int i = 0; i < brikker.length; i++) {
			System.out.println("Velg farge på brikke nr " + (i + 1));
			String farge = null;
			while(true) {
				farge = scanner.next();
				if(farge == null || farge.isBlank() || farge.length() > 30) {
					System.out.println("Ugyldig valg. Velg farge på brikke nr " + (i + 1));
				}
				else {
					break;
				}
			}
			brikker[i] = new Brikke(farge);
		}
		
		Stigespill spill = new Stigespill(brikker);
		
		System.out.println("Trykk ENTER for å starte spill");
		scanner.next();
		spill.start();
		
	}

}
