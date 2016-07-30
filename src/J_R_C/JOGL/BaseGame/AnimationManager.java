package J_R_C.JOGL.BaseGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class AnimationManager {
	private Image image;
	private double animationChangeTime;
	private boolean isAnimationOneTime;
	private AnimationSprite[] animationsprites;
	private int nIndicationSpriteNumber;
	private double nAnimationInterval;

	public AnimationManager(String imageName, AnimationSprite... animationsprites) {
		super();
		this.image = new Image(imageName);
		this.animationsprites = animationsprites;
		this.nIndicationSpriteNumber = 0;
		this.isAnimationOneTime = false;
		this.nAnimationInterval = 0f;
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
		else if(nIndicationSpriteNumber >= animationsprites.length && true == isAnimationOneTime)
			nIndicationSpriteNumber = animationsprites.length - 1;
	}

	public void renderAnimation(double time, GraphicsContext gc, double positionXX, double positionYY, double sizeX,
			double sizeY) {

		nAnimationInterval += time;

		if (nAnimationInterval >= this.getAnimationChangeTime()) {
			nAnimationInterval = 0f;
			this.increaseIndicationSpriteNumber();
		}

		gc.drawImage(this.getImage(), this.getAnimationsprites(this.getnIndicationSpriteNumber()).getImageStartX(),
				this.getAnimationsprites(this.getnIndicationSpriteNumber()).getImageStartY(),
				this.getAnimationsprites(this.getnIndicationSpriteNumber()).getImageSizeX(),
				this.getAnimationsprites(this.getnIndicationSpriteNumber()).getImageSizeY(), positionXX - sizeX / 2,
				positionYY - sizeY / 2, sizeX, sizeY);

	}

}
