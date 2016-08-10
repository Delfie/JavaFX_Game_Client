
package Object;

import GraphicUtility.AnimationManager;
import GraphicUtility.GraphicsContextSprite;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class PangPangPlayer Class using for PangPang
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class PangPangPlayer extends GraphicsContextSprite {

	private String sPlayerName;
	private static AnimationManager PlayerLeftImage;
	private static AnimationManager PlayerRihgtImage;

	private int nLife;
	private boolean isDeath;

	/**
	 * PangPangPlayer constructor
	 * 
	 * @param filename
	 *            - path for PangPangPlayer Game player image
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public PangPangPlayer(String filename, double width, double height) {
		super(filename, width, height);
		this.nLife = 2;
		this.isDeath = false;
	}

	/**
	 * PangPangPlayer constructor
	 * 
	 * @param filename
	 *            - path for PangPangPlayer Game player image
	 * @param startX
	 *            - image start position x in image file standard on left up
	 * @param startY
	 *            - image start position y in image file standard on left up
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public PangPangPlayer(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		this.nLife = 2;
		this.isDeath = false;
	}

	/**
	 * update this object using time LEFT -40 RIGHT 40 (speed)
	 */
	public void update(double time) {

		if (nDirection == LEFT)
			velocityX = -40;
		else if (nDirection == RIGHT)
			velocityX = 40;
		else if (nDirection == UP)
			velocityX = 0;

		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	/**
	 * get pangpang player left animation
	 * 
	 * @return
	 */
	public static AnimationManager getPlayerLeftImage() {
		return PlayerLeftImage;
	}

	/**
	 * set pangpang player left animation
	 * 
	 * @param playerLeftImage
	 */
	public static void setPlayerLeftImage(AnimationManager playerLeftImage) {
		PlayerLeftImage = playerLeftImage;
	}

	/**
	 * get pangpang player right animation
	 * 
	 * @return
	 */
	public static AnimationManager getPlayerRightImage() {
		return PlayerRihgtImage;
	}

	/**
	 * set pangpang player right animation
	 * 
	 * @param playerRihgtImage
	 */
	public static void setPlayerRightImage(AnimationManager playerRihgtImage) {
		PlayerRihgtImage = playerRihgtImage;
	}

	/**
	 * get pangapng player unique name
	 * 
	 * @return
	 */
	public String getsPlayerName() {
		return sPlayerName;
	}

	/**
	 * set pangapng player unique name
	 * 
	 * @param sPlayerName
	 */
	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

	/**
	 * get pangapng player life
	 * 
	 * @return
	 */
	public int getnLife() {
		return nLife;
	}

	/**
	 * decrease pangpang player life if pangpang player life is 0 then change
	 * the isdeath to true
	 */
	public void decreaseLife() {
		this.nLife--;

		if (nLife < 0)
			this.isDeath = true;
	}

	/**
	 * check the pangpang player death
	 * 
	 * @return
	 */
	public boolean isDeath() {
		return isDeath;
	}

}
