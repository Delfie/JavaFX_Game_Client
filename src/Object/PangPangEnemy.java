package Object;

import GraphicUtility.GraphicsContextSprite;

public class PangPangEnemy extends GraphicsContextSprite {
	private String sPlayerName;
	private boolean isDeath;
	private int imageTypeNumber;

	public PangPangEnemy(String filename, double width, double height) {
		super(filename, width, height);
	}

	public PangPangEnemy(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		// TODO Auto-generated constructor stub
	}

	public String getsPlayerName() {
		return sPlayerName;
	}

	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

	public boolean isDeath() {
		return isDeath;
	}

	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}

	public int getImageTypeNumber() {
		return imageTypeNumber;
	}

	public void setImageTypeNumber(int imageTypeNumber) {
		this.imageTypeNumber = imageTypeNumber;
	}

}
