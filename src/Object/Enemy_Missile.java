package Object;

import java.util.Random;

import GraphicUtility.GraphicsContextSprite;

public class Enemy_Missile extends GraphicsContextSprite {

	public static final int BUBLLE_MISSILE_WIDTH = 5;
	public static final int BUBLLE_MISSILE_HEIHGT = 5;

	String sNameID;

	private Random rnd = new Random();
	private int image_Number;
	private boolean isDead;
	private int directions;
	private boolean outBound;

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

	public String getsNameID() {
		return sNameID;
	}

	boolean get_Out_Bound() {
		return this.outBound;
	}

	boolean get_Is_Dead() {
		return this.isDead;
	}

	int get_Directions() {
		return this.directions;
	}

	int get_Image_Number() {
		return this.image_Number;
	}

}
