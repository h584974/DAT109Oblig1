package spillkomponenter;

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
	
	public boolean spillTur(Brikke b, int kast, Logg logg) {
		boolean harVunnet = false;
		logg.setFarge(b.getFarge());
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
			b.oppdaterKast(-1);
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
		
		return harVunnet;
	}

}
