package spill;

import spillkomponenter.Brett;
import spillkomponenter.Brikke;
import spillkomponenter.Logg;
import spillkomponenter.Terning;

public class Stigespill {
	
	private Brett brett;
	private Brikke[] brikker;
	private int runde;
	private Terning terning;
	
	public Stigespill(Brikke[] brikker) {
		this.brikker = brikker;
		this.brett = new Brett();
		this.runde = 1;
		this.terning = new Terning();
	}
	
	public void start() {
		boolean harVunnet = false;
		Logg logg = null;
		
		while(!harVunnet) {
			System.out.println("Runde " + runde + ":");
			
			for(Brikke b : brikker) {
				logg = new Logg();
				harVunnet = brett.spillTur(b, terning.kastTerning(), logg);
				skrivLogg(logg);
				if(harVunnet) {
					skrivVinner(b);
					break;
				}
				
				try {
					Thread.sleep(200);
				}
				catch(Throwable e) {}
			}
			
			System.out.println();
			
			runde++;
		}
	}
	
	private void skrivVinner(Brikke brikke) {
		System.out.println(brikke.getFarge() + " brikke vant spillet på runde " + runde);
	}
	
	private void skrivLogg(Logg logg) {
		String temp = "flyttet seg fra rute " + (logg.getStartPos() + 1) + " til rute " + (logg.getSluttPos() + 1);
		if(logg.getLink() > -1) {
			if(logg.getStartPos() > logg.getSluttPos()) {
				temp = "traff en slange, falt fra rute " + (logg.getLink() + 1) + " til rute " + (logg.getSluttPos() + 1);
			}
			else {
				temp = "traff en stige, klatret fra rute " + (logg.getLink() + 1) + " til rute " + (logg.getSluttPos() + 1);
			}
		}
		else if(logg.isFanget()) {
			temp = "flyttet ikke på seg, trenger terningkast 6 for å gå videre";
		}
 		else if(logg.getStartPos() == logg.getSluttPos()) {
			temp = "flyttet ikke på seg, " + (99 - logg.getSluttPos()) + " ruter fra mål!";
		}
		System.out.println(logg.getFarge() + " brikke trillet " + logg.getKast() + " på terningen og " + temp);
	}


}