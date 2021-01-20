package spill;

import spillkomponenter.Brett;
import spillkomponenter.Brikke;

public class Stigespill {
	
	private Brett brett;
	private Brikke[] brikker;
	private int runde;
	
	public Stigespill(Brikke[] brikker) {
		this.brikker = brikker;
		this.brett = new Brett();
		this.runde = 1;
	}
	
	public void start() {
		boolean harVunnet = false;
		while(!harVunnet) {
			System.out.println("Runde " + runde + ":");
			for(Brikke b : brikker) {
				harVunnet = brett.spillTur(b);
				if(harVunnet) {
					skrivVinner(b);
					break;
				}
			}
			
			System.out.println();
			
			runde++;
		}
	}
	
	private void skrivVinner(Brikke brikke) {
		System.out.println(brikke.getFarge() + " brikke vant spillet på runde " + runde);
	}

}
