package GraphicUtility;

public class AnimationSprite {
	private double imageStartX;

	private double imageStartY;

	private double imageSizeX;

	private double imageSizeY;

	public AnimationSprite(double imageStartX, double imageStartY, double imageSizeX, double imageSizeY) {
		super();
		this.imageStartX = imageStartX;
		this.imageStartY = imageStartY;
		this.imageSizeX = imageSizeX;
		this.imageSizeY = imageSizeY;
	}

	public double getImageStartX() {
		return imageStartX;
	}

	public double getImageStartY() {
		return imageStartY;
	}

	public double getImageSizeX() {
		return imageSizeX;
	}

	public double getImageSizeY() {
		return imageSizeY;
	}

}
