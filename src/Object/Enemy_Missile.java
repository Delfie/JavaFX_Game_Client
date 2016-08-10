package Object;

import java.util.Random;

import GraphicUtility.GraphicsContextSprite;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Enemy_Missile Class using for PangPang
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Enemy_Missile extends GraphicsContextSprite {

	public static final int BUBLLE_MISSILE_WIDTH = 5;
	public static final int BUBLLE_MISSILE_HEIHGT = 5;

	String sNameID;

	private Random rnd = new Random();
	private int image_Number;
	private boolean isDead;
	private int directions;
	private boolean outBound;

	/**
	 * Enemy_Missile constructor
	 * 
	 * @param filename
	 *            - path for Enemy_Missile image
	 * @param startX
	 *            - image start position x in image file standard on left up
	 * @param startY
	 *            - image start position y in image file standard on left up
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 * 
	 * @param x
	 *            - x start on the game image board
	 * @param y
	 *            - y start on the game image board
	 * @param dir
	 *            - object direction
	 * @param name
	 *            - object unique name
	 */
	public Enemy_Missile(String filename, double startX, double startY, double width, double height, double x, double y,
			int dir, String name) {
		super(filename, startX, startY, width, height);
		this.sNameID = name;
		this.isDead = false;
		this.directions = dir;
		this.image_Number = rnd.nextInt(3) + 45;
		setPosition(x, y);
		setVelocity(direction_dis_X[dir] * 90, dircetion_dis_Y[dir] * 90);

	}

	/**
	 * get enemyMissile unique Name
	 * 
	 * @return
	 */
	public String getsNameID() {
		return sNameID;
	}

	/**
	 * return the out of bound condition
	 * 
	 * @return
	 */
	boolean get_Out_Bound() {
		return this.outBound;
	}

	/**
	 * return the death condition
	 * 
	 * @return
	 */
	boolean get_Is_Dead() {
		return this.isDead;
	}

	/**
	 * return object direction
	 * 
	 * @return
	 */
	int get_Directions() {
		return this.directions;
	}

	/**
	 * return image number which set the image
	 * 
	 * @return
	 */
	int get_Image_Number() {
		return this.image_Number;
	}

}
