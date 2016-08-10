package Object;

import GraphicUtility.GraphicsContextSprite;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class PangPangEnemy Class using for PangPang
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class PangPangEnemy extends GraphicsContextSprite {
	private String sPlayerName;
	private boolean isDeath;
	private int imageTypeNumber;

	/**
	 * PangPangEnemy constructor
	 * 
	 * @param filename
	 *            - path for PangPangEnemy image
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public PangPangEnemy(String filename, double width, double height) {
		super(filename, width, height);
	}

	/**
	 * PangPangEnemy constructor
	 * 
	 * @param filename
	 *            - path for PangPangEnemy image
	 * @param startX
	 *            - image start position x in image file standard on left up
	 * @param startY
	 *            - image start position y in image file standard on left up
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public PangPangEnemy(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * get enemy unique name
	 */
	public String getsPlayerName() {
		return sPlayerName;
	}

	/**
	 * set enemy unique name
	 * 
	 * @param sPlayerName
	 */
	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

	/**
	 * get enemy death condition
	 * 
	 * @return
	 */
	public boolean isDeath() {
		return isDeath;
	}

	/**
	 * set enemy death condition
	 * 
	 * @param isDeath
	 */
	public void setDeath(boolean isDeath) {
		this.isDeath = isDeath;
	}

	/**
	 * get enemy image type number which decide enemy image
	 * 
	 * @return
	 */
	public int getImageTypeNumber() {
		return imageTypeNumber;
	}

	/**
	 * set enemy image type number which decide enemy iamge
	 * 
	 * @param imageTypeNumber
	 */
	public void setImageTypeNumber(int imageTypeNumber) {
		this.imageTypeNumber = imageTypeNumber;
	}

	/**
	 * update enemy this method automatically change enemy direction and change
	 */
	public void update(double time) {

		if (velocityX > 0)
			nDirection = LEFT;
		else if (velocityX < 0)
			nDirection = RIGHT;
		else if (velocityX == 0)
			nDirection = UP;

		positionX += velocityX * time;
		positionY += velocityY * time;

	}

}
