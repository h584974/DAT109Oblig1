package spillkomponenter;

public class Logg {
	
	private boolean fanget;
	private int startPos;
	private int sluttPos;
	private String brikke;
	private int link;
	private int kast;
	
	public Logg() {
		fanget = false;
		startPos = -1;
		sluttPos = -1;
		brikke = null;
		link = -1;
		kast = 0;
	}

	public boolean isFanget() {
		return fanget;
	}

	public void setFanget(boolean fanget) {
		this.fanget = fanget;
	}

	public int getKast() {
		return kast;
	}

	public void setKast(int kast) {
		this.kast = kast;
	}

	public int getLink() {
		return link;
	}

	public void setLink(int link) {
		this.link = link;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getSluttPos() {
		return sluttPos;
	}

	public void setSluttPos(int sluttPos) {
		this.sluttPos = sluttPos;
	}

	public String getBrikke() {
		return brikke;
	}

	public void setBrikke(String brikke) {
		this.brikke = brikke;
	}

}
