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
	private int forrigeTerningkast;
	
	public Stigespill(Brikke[] brikker) {
		this.brikker = brikker;
		this.brett = new Brett();
		this.runde = 1;
		this.nesteTur = 0;
		this.terning = new Terning();
		this.harVunnet = false;
		this.forrigeTerningkast = -1;
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
			forrigeTerningkast = kast;
			harVunnet = brett.spillTur(brikke, kast, logg);
			skrivLogg(logg,brikke);
			
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
	 * Sjekker hva forrige terningkast ble.
	 * @return forrigeTerningkast
	 */
	public int getForrigeTerningkast() {
		return forrigeTerningkast;
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
	 * @return getFarge() Fargen på neste brikke
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
	 * @param logg Logg-objekt som inneholder detaljer for tur som skal skrives
	 */
	private void skrivLogg(Logg logg, Brikke brikke) {
		String temp = "flyttet seg fra rute " + (logg.getStartPos() + 1) + " til rute " + (brikke.getPosisjon() + 1);
		if(logg.getLink() > -1) {
			if(logg.getStartPos() + brikke.getSisteKast() > brikke.getPosisjon()) {
				temp = "traff en slange, falt fra rute " + (logg.getLink() + 1) + " til rute " + (brikke.getPosisjon() + 1);
			}
			else {
				temp = "traff en stige, klatret fra rute " + (logg.getLink() + 1) + " til rute " + (brikke.getPosisjon() + 1);
			}
		}
		else if(brikke.getFanget()) {
			if(brikke.getSisteKast() == 6) {
				temp = "ble fanget, trenger terningkast 6 for å gå videre";
			}
			else {
				temp = "er enda fanget, trenger terningkast 6 for å gå videre";
			}
		}
 		else if(logg.getStartPos() == brikke.getPosisjon()) {
			temp = "flyttet ikke på seg, " + (99 - brikke.getPosisjon()) + " ruter fra mål!";
		}
		System.out.println(brikke.getFarge() + " brikke trillet " + brikke.getSisteKast() + " på terningen og " + temp);
	}

}