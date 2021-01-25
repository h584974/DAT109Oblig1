package spillkomponenter;

public class Brett {
	
	private Rute[] ruter;
	
	public Brett() {
		ruter = lagRuter(100);
	}
	
	/**
	 * Oppretter ruter til brettet og setter hvilke som er slanger og hvilke som er stiger.
	 * @param antallRuter Antall ruter som skal opprettes
	 * @return Tabell av ruter
	 */
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
	
	/**
	 * Spiller turen til en brikke, sjekker for eventuelle slanger/stiger og om brikken vinner
	 * @param brikke Brikke som sin tur skal spilles
	 * @param kast Brikken sitt terningkast for turen
	 * @param logg Logg-objekt hvor detaljer for turen lagres
	 * @return Bolsk verdi, om brikken har vunnet eller ikke
	 */
	public boolean spillTur(Brikke brikke, int kast, Logg logg) {
		boolean harVunnet = false;
		logg.setFarge(brikke.getFarge());
		logg.setStartPos(brikke.getPosisjon());
		logg.setKast(kast);
		
		if(brikke.getFanget()) {
			if(kast == 6) {
				brikke.setPosisjon(brikke.getPosisjon() + kast);
			}
		}
		else if(kast == 6 && brikke.getFoersteKast() == 6 && brikke.getSisteKast() == 6) {
			brikke.setPosisjon(0);
			brikke.setFanget(true);
			brikke.oppdaterKast(-1);
			logg.setFanget(true);
		}
		else {
			int tempPos = brikke.getPosisjon() + kast;
			
			if(tempPos < 99) {
				int linkPos = ruter[tempPos].getLink();
				if(linkPos == -1) {
					brikke.setPosisjon(tempPos);
				}
				else {
					logg.setLink(brikke.getPosisjon() + kast);
					brikke.setPosisjon(linkPos);
				}
			}
			else if(tempPos == 99) {
				brikke.setPosisjon(tempPos);
				harVunnet = true;
			}
		}
		
		logg.setSluttPos(brikke.getPosisjon());
		
		return harVunnet;
	}

}
