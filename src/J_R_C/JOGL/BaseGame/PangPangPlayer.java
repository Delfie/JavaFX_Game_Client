
package J_R_C.JOGL.BaseGame;

public class PangPangPlayer extends GraphicsContextSprite {

	private String sPlayerName;
	private static AnimationManager PlayerLeftImage;
	private static AnimationManager PlayerRihgtImage;

	public PangPangPlayer(String filename, double width, double height) {
		super(filename, width, height);
	}

	public PangPangPlayer(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		// TODO Auto-generated constructor stub
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

	public static void setPlayerRihgtImage(AnimationManager playerRihgtImage) {
		PlayerRihgtImage = playerRihgtImage;
	}

	public String getsPlayerName() {
		return sPlayerName;
	}

	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

}
