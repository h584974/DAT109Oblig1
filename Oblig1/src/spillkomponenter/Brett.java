package spillkomponenter;

import java.util.Random;

public class Brett {
	
	private Rute[] ruter;
	
	public Brett() {
		ruter = lagRuter(100);
	}
	
	private Rute[] lagRuter(int antallRuter) {
		Rute[] ruter = new Rute[antallRuter];
		
		for(int i = 0; i < ruter.length; i++) {
			ruter[i] = new Rute();
		}
		
		ruter[0].setLink(39);
		ruter[32].setLink(2);
		ruter[23].setLink(4);
		ruter[7].setLink(9);
		ruter[73].setLink(11);
		ruter[63].setLink(26);
		ruter[41].setLink(29);
		ruter[35].setLink(51);
		ruter[55].setLink(36);
		ruter[42].setLink(61);
		ruter[48].setLink(78);
		ruter[64].setLink(81);
		ruter[67].setLink(84);
		ruter[86].setLink(69);
		ruter[76].setLink(97);
		ruter[98].setLink(0);
		
		return ruter;
	}
	
	public boolean spillTur(Brikke b) {
		boolean harVunnet = false;
		int kast = kastTerning();
		Logg logg = new Logg();
		logg.setBrikke(b.getFarge());
		logg.setStartPos(b.getPosisjon());
		logg.setKast(kast);
		
		if(b.getFanget()) {
			if(kast == 6) {
				b.setPosisjon(b.getPosisjon() + kast);
			}
		}
		else if(kast == 6 && b.getFoersteKast() == 6 && b.getSisteKast() == 6) {
			b.setPosisjon(0);
			b.setFanget(true);
			logg.setFanget(true);
		}
		else {
			int tempPos = b.getPosisjon() + kast;
			
			if(tempPos < 99) {
				int linkPos = ruter[tempPos].getLink();
				if(linkPos == -1) {
					b.setPosisjon(tempPos);
				}
				else {
					logg.setLink(b.getPosisjon() + kast);
					b.setPosisjon(linkPos);
				}
			}
			else if(tempPos == 99) {
				b.setPosisjon(tempPos);
				harVunnet = true;
			}
		}
		logg.setSluttPos(b.getPosisjon());
		skrivLogg(logg);
		
		try {
			Thread.sleep(200);
		}
		catch(Throwable e) {}
		
		return harVunnet;
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
		System.out.println(logg.getBrikke() + " brikke trillet " + logg.getKast() + " på terningen og " + temp);
	}
	
	private int kastTerning() {
		Random r = new Random();
		return r.nextInt(6) + 1;
	}

}
