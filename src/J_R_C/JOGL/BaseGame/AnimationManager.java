package J_R_C.JOGL.BaseGame;

import javafx.scene.image.Image;

public class AnimationManager {
	private Image image;
	private double animationChangeTime;
	private boolean isAnimationOneTime;
	private AnimationSprite[] animationsprites;
	private int nIndicationSpriteNumber;

	public AnimationManager(Image image, AnimationSprite... animationsprites) {
		super();
		this.image = image;
		this.animationsprites = animationsprites;
		this.nIndicationSpriteNumber = 0;
		this.isAnimationOneTime = false;
	}

	public double getAnimationChangeTime() {
		return animationChangeTime;
	}

	public void setAnimationChangeTime(double animationChangeTime) {
		this.animationChangeTime = animationChangeTime;
	}

	public boolean isAnimationOneTime() {
		return isAnimationOneTime;
	}

	public void setAnimationOneTime(boolean isAnimationOneTime) {
		this.isAnimationOneTime = isAnimationOneTime;
	}

	public Image getImage() {
		return image;
	}

	public AnimationSprite getAnimationsprites(int i) {
		return animationsprites[i];
	}

	public int getAnimationspriteLength() {
		return animationsprites.length;
	}

	public int getnIndicationSpriteNumber() {
		return nIndicationSpriteNumber;
	}

	public void increaseIndicationSpriteNumber() {
		nIndicationSpriteNumber++;

		if (nIndicationSpriteNumber >= animationsprites.length && false == isAnimationOneTime)
			nIndicationSpriteNumber = 0;
		else
			nIndicationSpriteNumber = animationsprites.length - 1;
	}

}
