
package GraphicUtility;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Manage The all for the GraphicContexSprite
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class GraphicsContextSprite {
	protected double MAXIMUMSPEED = 10;

	protected Image image;

	protected double imageStartX;

	protected double imageStartY;

	protected double positionX;

	protected double positionY;

	protected double velocityX;

	protected double velocityY;

	protected double imageSizeX;

	protected double imageSizeY;

	protected double width;

	protected double height;

	protected int nDirection;

	public static final int LEFT = 1;

	public static final int RIGHT = 0;

	public static final int UP = 2;

	protected float direction_dis_X[] = { 0f, 0.39f, 0.75f, 0.93f, 1f, 0.93f, 0.75f, 0.39f, 0f, -0.39f, -0.75f, -0.93f,
			-1f, -0.93f, -0.75f, -0.39f };

	protected float dircetion_dis_Y[] = { -1f, -0.93f, -0.75f, -0.39f, -0f, +0.39f, +0.75f, +0.93f, +1f, +0.93f, +0.75f,
			+0.39f, 0f, -0.39f, -0.75f, -0.93f };

	/**
	 * GraphicsContextspirte constructor
	 */
	public GraphicsContextSprite() {
		imageStartX = 0;
		imageStartY = 0;
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
		imageSizeX = 0;
		imageSizeY = 0;
	}

	/**
	 * GraphicsContextspirte constructor
	 * 
	 * @param filename
	 *            - image path
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public GraphicsContextSprite(String filename, double width, double height) {
		Image i = new Image(filename);
		setImage(i, width, height);
		imageStartX = 0;
		imageStartY = 0;
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}

	/**
	 * GraphicsContextspirte constructor*
	 * 
	 * @param filename
	 *            - image path
	 * @param startX
	 *            - image start x
	 * @param startY
	 *            - image start y
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public GraphicsContextSprite(String filename, double startX, double startY, double width, double height) {
		Image i = new Image(filename);
		setImage(i, startX, startY, width, height);
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}

	/**
	 * reInit
	 * 
	 * @param filename
	 *            - image path
	 * @param startX
	 *            - image start x
	 * @param startY
	 *            - image start y
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public void reInitImage(String filename, double startX, double startY, double width, double height) {
		Image i = new Image(filename);
		setImage(i, startX, startY, width, height);
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
	}

	/**
	 * set image
	 * 
	 * @param i
	 *            - image
	 */
	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
		imageSizeX = width;
		imageSizeY = height;
	}

	/**
	 * set image
	 * 
	 * @param i
	 *            - image
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public void setImage(Image i, double width, double height) {
		image = i;
		this.width = width;
		this.height = height;
		imageSizeX = width;
		imageSizeY = height;
	}

	/**
	 * set image
	 * 
	 * @param i
	 *            - image
	 * @param startX
	 *            - image start x
	 * @param startY
	 *            - image start y
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public void setImage(Image i, double startX, double startY, double width, double height) {
		image = i;
		this.width = width;
		this.height = height;
		this.imageStartX = startX;
		this.imageStartY = startY;
		imageSizeX = width;
		imageSizeY = height;
	}

	/**
	 * set image
	 * 
	 * @param filename
	 *            - image path
	 */
	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	/**
	 * set image
	 * 
	 * @param filename
	 *            - image path
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public void setImage(String filename, double width, double height) {
		Image i = new Image(filename);
		setImage(i, width, height);
	}

	/**
	 * set unit position x, y
	 * 
	 * @param x
	 * @param y
	 */
	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	/**
	 * set unit position x
	 * 
	 * @param x
	 */
	public void setPositionX(double x) {
		positionX = x;
	}

	/**
	 * set unit position y
	 * 
	 * @param y
	 */
	public void setPositionY(double y) {
		positionY = y;
	}

	/**
	 * set velocity x, y
	 * 
	 * @param x
	 * @param y
	 */
	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}

	/**
	 * add velocity x, y
	 * 
	 * @param x
	 * @param y
	 */
	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
		if (velocityX > MAXIMUMSPEED)
			velocityX = MAXIMUMSPEED;
		else if (velocityX < -MAXIMUMSPEED)
			velocityX = -MAXIMUMSPEED;

		if (velocityY > MAXIMUMSPEED)
			velocityY = MAXIMUMSPEED;
		else if (velocityY < -MAXIMUMSPEED)
			velocityY = -MAXIMUMSPEED;
	}

	/**
	 * set unit direction
	 * 
	 * @param direction
	 */
	public void setDirection(int direction) {
		this.nDirection = direction;
	}

	/**
	 * get unit direction
	 * 
	 * @return
	 */
	public int getDirection() {
		return this.nDirection;
	}

	/**
	 * unit update
	 * 
	 * @param time
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

	/**
	 * unit render
	 * 
	 * @param gc
	 *            - scene
	 */
	public void render(GraphicsContext gc) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, this.positionX - this.imageSizeX / 2,
				this.positionY - this.imageSizeY / 2, this.imageSizeX, this.imageSizeY);
	}

	/**
	 * unit render
	 * 
	 * @param gc
	 *            -scene
	 * @param positionXX
	 *            - draw position x
	 * @param positionYY
	 *            - draw position y
	 */
	public void render(GraphicsContext gc, double positionXX, double positionYY) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX - this.imageSizeX / 2,
				positionYY - this.imageSizeY / 2, this.imageSizeX, this.imageSizeY);
	}

	/**
	 * unit render
	 * 
	 * @param gc
	 *            -scene
	 * @param positionXX
	 *            - draw position x
	 * @param positionYY
	 *            - draw position y
	 * @param sizeX
	 *            - draw size x
	 * @param sizeY
	 *            - draw size y
	 */
	public void render(GraphicsContext gc, double positionXX, double positionYY, double sizeX, double sizeY) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX - sizeX / 2,
				positionYY - sizeY / 2, sizeX, sizeY);
	}

	/**
	 * get unit boundary
	 * 
	 * @return
	 */
	public Circle getBoundary() {
		return new Circle(positionX - this.imageSizeX / 2, positionY - this.imageSizeY / 2, this.imageSizeX);

	}

	/**
	 * check unit crash
	 * 
	 * @param s
	 * @return
	 */
	public boolean intersects(GraphicsContextSprite s) {
		double distX = positionX - s.getPositionX();
		double distY = positionY - s.getPositionY();

		double distance = distX * distX + distY * distY;
		double radiusSum = imageSizeX / 2 + s.getImageSizeX();
		return distance <= radiusSum * radiusSum;
	}

	/**
	 * get unit information string
	 */
	public String toString() {
		return " Position: [" + positionX + "," + positionY + "]" + " Velocity: [" + velocityX + "," + velocityY + "]";
	}

	/**
	 * set image size
	 * 
	 * @param imageWidth
	 * @param imageHeight
	 */
	public void setImageSize(double imageWidth, double imageHeight) {
		imageSizeX = imageWidth;
		imageSizeY = imageHeight;
	}

	/**
	 * get unit position x
	 * 
	 * @return
	 */
	public double getPositionX() {
		return positionX;
	}

	/**
	 * get unit position y
	 * 
	 * @return
	 */
	public double getPositionY() {
		return positionY;
	}

	/**
	 * get image size width
	 * 
	 * @return
	 */
	public double getImageSizeX() {
		return imageSizeX;
	}

	/**
	 * set image size x
	 * 
	 * @param imageSizeX
	 */
	public void setImageSizeX(double imageSizeX) {
		this.imageSizeX = imageSizeX;
	}

	/**
	 * get image size height
	 * 
	 * @param imageSizeX
	 */
	public double getImageSizeY() {
		return imageSizeY;
	}

	/**
	 * set image size y
	 * 
	 * @param imageSizeY
	 */
	public void setImageSizeY(double imageSizeY) {
		this.imageSizeY = imageSizeY;
	}

}
