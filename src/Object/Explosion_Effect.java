package Object;

import GraphicUtility.GraphicsContextSprite;

public class Explosion_Effect extends GraphicsContextSprite {

	private int limitTime;
	private int direction;
	private boolean termination;

	public Explosion_Effect(String filename, double startX, double startY, double width, double height, int dir) {
		super(filename, startX, startY, width, height);
		limitTime = 0;
		direction = dir;
		setVelocity(direction_dis_X[dir] * 10, dircetion_dis_Y[dir] * 10);
		termination = false;
	}

	public void update(double time) {
		limitTime++;
		positionX += velocityX * time;
		positionY += velocityY * time;

		if (limitTime > 100)
			termination = true;

	}

	public boolean isTermination() {
		return termination;
	}

	public int get_Direction() {
		return this.direction;
	}

}