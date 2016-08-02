
package Object;

import GraphicUtility.AnimationManager;
import GraphicUtility.GraphicsContextSprite;

public class PangPangPlayer extends GraphicsContextSprite {

	private String sPlayerName;
	private static AnimationManager PlayerLeftImage;
	private static AnimationManager PlayerRihgtImage;

	private int nLife;
	private boolean isDeath;

	public PangPangPlayer(String filename, double width, double height) {
		super(filename, width, height);
		this.nLife = 2;
		this.isDeath = false;
	}

	public PangPangPlayer(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		this.nLife = 2;
		this.isDeath = false;
	}

	public void update(double time) {

		if (nDirection == LEFT)
			velocityX = -10;
		else if (nDirection == RIGHT)
			velocityX = 10;
		else if (nDirection == UP)
			velocityX = 0;

		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	public static AnimationManager getPlayerLeftImage() {
		return PlayerLeftImage;
	}

	public static void setPlayerLeftImage(AnimationManager playerLeftImage) {
		PlayerLeftImage = playerLeftImage;
	}

	public static AnimationManager getPlayerRightImage() {
		return PlayerRihgtImage;
	}

	public static void setPlayerRightImage(AnimationManager playerRihgtImage) {
		PlayerRihgtImage = playerRihgtImage;
	}

	public String getsPlayerName() {
		return sPlayerName;
	}

	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

	public int getnLife() {
		return nLife;
	}

	public void decreaseLife() {
		this.nLife--;

		if (nLife < 0)
			this.isDeath = true;
	}

	public boolean isDeath() {
		return isDeath;
	}

}
