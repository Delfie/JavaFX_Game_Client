package GraphicUtility;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Manage The all for the AnimationSprite
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class AnimationSprite {
	private double imageStartX;

	private double imageStartY;

	private double imageSizeX;

	private double imageSizeY;

	/**
	 * AnimationSprite constructor
	 * 
	 * @param imageStartX
	 * @param imageStartY
	 * @param imageSizeX
	 * @param imageSizeY
	 */
	public AnimationSprite(double imageStartX, double imageStartY, double imageSizeX, double imageSizeY) {
		super();
		this.imageStartX = imageStartX;
		this.imageStartY = imageStartY;
		this.imageSizeX = imageSizeX;
		this.imageSizeY = imageSizeY;
	}

	/**
	 * get image start x
	 * 
	 * @return
	 */
	public double getImageStartX() {
		return imageStartX;
	}

	/**
	 * get image start y
	 * 
	 * @return
	 */
	public double getImageStartY() {
		return imageStartY;
	}

	/**
	 * get image size x
	 * 
	 * @return
	 */
	public double getImageSizeX() {
		return imageSizeX;
	}

	/**
	 * get image size y
	 * 
	 * @return
	 */
	public double getImageSizeY() {
		return imageSizeY;
	}

}
