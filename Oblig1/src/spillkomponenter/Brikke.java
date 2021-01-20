package spillkomponenter;

public class Brikke {
	
	private int posisjon;
	private String farge;
	private int sisteKast;
	private int foersteKast;
	private boolean fanget;
	
	public Brikke(String farge) {
		this.posisjon = 0;
		this.farge = farge;
		this.sisteKast = 0;
		this.foersteKast = 0;
		this.fanget = false;
	}
	
	public boolean getFanget() {
		return this.fanget;
	}
	
	public void setFanget(boolean fanget) {
		this.fanget = fanget;
	}
	
	public void setPosisjon(int posisjon) {
		this.posisjon = posisjon;
	}
	
	public int getPosisjon() {
		return this.posisjon;
	}
	
	public int getSisteKast() {
		return this.sisteKast;
	}
	
	public int getFoersteKast() {
		return this.foersteKast;
	}
	
	public String getFarge() {
		return this.farge;
	}
	
	public void oppdaterKast(int kast) {
		foersteKast = sisteKast;
		sisteKast = kast;
	}
	
}
