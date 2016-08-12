package GraphicUtility;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Manage The all for the AnimationManager
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class AnimationManager {
	private Image image;
	private double animationChangeTime;
	private boolean isAnimationOneTime;
	private AnimationSprite[] animationsprites;
	private int nIndicationSpriteNumber;
	private double nAnimationInterval;

	/**
	 * AnimationManager Constructor
	 * 
	 * @param imageName
	 * @param animationsprites
	 */
	public AnimationManager(String imageName, AnimationSprite... animationsprites) {
		super();
		this.image = new Image(imageName);
		this.animationsprites = animationsprites;
		this.nIndicationSpriteNumber = 0;
		this.isAnimationOneTime = false;
		this.nAnimationInterval = 0f;
	}

	/**
	 * get animation update time
	 * 
	 * @return
	 */
	public double getAnimationChangeTime() {
		return animationChangeTime;
	}

	/**
	 * set animation update time
	 * 
	 * @param animationChangeTime
	 */
	public void setAnimationChangeTime(double animationChangeTime) {
		this.animationChangeTime = animationChangeTime;
	}

	/**
	 * is animation just one time running
	 * 
	 * @return
	 */
	public boolean isAnimationOneTime() {
		return isAnimationOneTime;
	}

	/**
	 * set animation one time running
	 * 
	 * @param isAnimationOneTime
	 */
	public void setAnimationOneTime(boolean isAnimationOneTime) {
		this.isAnimationOneTime = isAnimationOneTime;
	}

	/**
	 * get image
	 * 
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * get animation sprite[i]
	 * 
	 * @param i
	 * @return
	 */
	public AnimationSprite getAnimationsprites(int i) {
		return animationsprites[i];
	}

	/**
	 * get animation total length
	 * 
	 * @return
	 */
	public int getAnimationspriteLength() {
		return animationsprites.length;
	}

	/**
	 * get animation indication position now
	 * 
	 * @return
	 */
	public int getnIndicationSpriteNumber() {
		return nIndicationSpriteNumber;
	}

	/**
	 * Increase sprite image index i
	 */
	public void increaseIndicationSpriteNumber() {
		nIndicationSpriteNumber++;

		if (nIndicationSpriteNumber >= animationsprites.length && false == isAnimationOneTime)
			nIndicationSpriteNumber = 0;
		else if (nIndicationSpriteNumber >= animationsprites.length && true == isAnimationOneTime)
			nIndicationSpriteNumber = animationsprites.length - 1;
	}

	/**
	 * render animation to scene
	 * 
	 * @param time
	 *            - update time
	 * @param gc
	 *            - draw scene
	 * @param positionXX
	 *            - start position x
	 * @param positionYY
	 *            - start position y
	 * @param sizeX
	 *            - image size x
	 * @param sizeY
	 *            - image size y
	 */
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
