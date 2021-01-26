package spill;

import spillkomponenter.Brett;
import spillkomponenter.Brikke;
import spillkomponenter.Logg;
import spillkomponenter.Terning;

public class Stigespill {
	
	private Brett brett;
	private Brikke[] brikker;
	private int runde;
	private int nesteTur;
	private Terning terning;
	private boolean harVunnet;
	
	public Stigespill(Brikke[] brikker) {
		this.brikker = brikker;
		this.brett = new Brett();
		this.runde = 1;
		this.nesteTur = 0;
		this.terning = new Terning();
		this.harVunnet = false;
	}
	
	/**
	 * Spiller neste brikke sin tur, skriver ut logg for turen og om brikke har vunnet spillet.
	 */
	public void spillNesteTur() {
		if(harVunnet) {
			System.out.println("Dette spillet er ferdigspilt");
		}
		else {
			Brikke brikke = brikker[nesteTur];
			Logg logg = new Logg();
			
			if((nesteTur + 1) % brikker.length == 0) {
				nesteTur = 0;
				System.out.println();
			}
			else {
				if(nesteTur == 0) {
					System.out.println("-- RUNDE " + runde + " --");
				}
				nesteTur++;
			}
			
			int kast = terning.kastTerning();
			harVunnet = brett.spillTur(brikke, kast, logg);
			skrivTur(logg,brikke);
			
			if(harVunnet) {
				skrivVinner(brikke);
			}
			
			if((nesteTur + 1) % brikker.length == 0) {
				runde++;
			}
			
			try {
				Thread.sleep(200);
			}
			catch(Throwable e) {}
		}
	}
	
	/**
	 * Sjekker om spillet er vunnet
	 * @return harVunnet
	 */
	public boolean harVunnet() {
		return harVunnet;
	}
	
	/**
	 * Sjekker hvilken brikke sin tur det er
	 * @return Fargen på neste brikke
	 */
	public String getNesteBrikke() {
		return brikker[nesteTur].getFarge();
	}
	
	/**
	 * Skriver ut vinneren av spillet
	 * @param brikke Brikken som har vunnet
	 */
	private void skrivVinner(Brikke brikke) {
		System.out.println(brikke.getFarge() + " brikke vant spillet på runde " + runde);
	}
	
	/**
	 * Skriver passende logg for hver tur som spilles
	 * @param logg Logg-objekt som inneholder detaljer for tur som skal skrives som brikken ikke inneholder selv
	 * @param brikke Brikken som sin tur har blitt spilt
	 */
	private void skrivTur(Logg logg, Brikke brikke) {
		String melding = "flyttet seg fra rute " + (logg.getStartPos() + 1) + " til rute " + (brikke.getPosisjon() + 1);
		if(logg.getLink() > -1) {
			if(logg.getStartPos() + brikke.getSisteKast() > brikke.getPosisjon()) {
				melding = "traff en slange, falt fra rute " + (logg.getLink() + 1) + " til rute " + (brikke.getPosisjon() + 1);
			}
			else {
				melding = "traff en stige, klatret fra rute " + (logg.getLink() + 1) + " til rute " + (brikke.getPosisjon() + 1);
			}
		}
		else if(brikke.getFanget()) {
			if(brikke.getSisteKast() == 6) {
				melding = "ble fanget, trenger terningkast 6 for å gå videre";
			}
			else {
				melding = "er enda fanget, trenger terningkast 6 for å gå videre";
			}
		}
 		else if(logg.getStartPos() == brikke.getPosisjon()) {
			melding = "flyttet ikke på seg, " + (99 - brikke.getPosisjon()) + " ruter fra mål!";
		}
		
		System.out.println(brikke.getFarge() + " brikke trillet " + brikke.getSisteKast() + " på terningen og " + melding);
	}

}