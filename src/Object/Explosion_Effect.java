package Object;

import GraphicUtility.GraphicsContextSprite;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Explosion_effect Class using for PangPang
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Explosion_Effect extends GraphicsContextSprite {

	private int limitTime;
	private int direction;
	private boolean termination;

	/**
	 * Explosion_Effect constructor
	 * 
	 * @param filename
	 *            - path for Explosion_Effect image
	 * @param startX
	 *            - image start position x in image file standard on left up
	 * @param startY
	 *            - image start position y in image file standard on left up
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 * @param dir
	 *            - object direction
	 */
	public Explosion_Effect(String filename, double startX, double startY, double width, double height, int dir) {
		super(filename, startX, startY, width, height);
		limitTime = 0;
		direction = dir;
		setVelocity(direction_dis_X[dir] * 10, dircetion_dis_Y[dir] * 10);
		termination = false;
	}

	/**
	 * update image if over the limitTime then this object is destroyed
	 */
	public void update(double time) {
		limitTime++;
		positionX += velocityX * time;
		positionY += velocityY * time;

		if (limitTime > 100)
			termination = true;

	}

	/**
	 * return termination condition
	 * 
	 * @return
	 */
	public boolean isTermination() {
		return termination;
	}

	/**
	 * get object direction
	 * 
	 * @return
	 */
	public int get_Direction() {
		return this.direction;
	}

}