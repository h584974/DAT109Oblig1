package spillkomponenter;

public class Logg {
	
	private int startPos;
	private int link;
	
	public Logg() {
		startPos = -1;
		link = -1;
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

}