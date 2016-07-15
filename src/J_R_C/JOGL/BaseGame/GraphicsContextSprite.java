
package J_R_C.JOGL.BaseGame;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;

public class GraphicsContextSprite {
    private double MAXIMUMSPEED = 10;

    private Image image;

    private double imageStartX;

    private double imageStartY;

    private double positionX;

    private double positionY;

    private double velocityX;

    private double velocityY;

    private double imageSizeX;

    private double imageSizeY;

    private double width;

    private double height;

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

    public GraphicsContextSprite(String filename, double startX, double startY, double width,
            double height) {
        Image i = new Image(filename);
        setImage(i, width, height);
        imageStartX = 0;
        imageStartY = 0;
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
        imageSizeX = width;
        imageSizeY = height;
    }

    public void setImage(Image i, double width, double height) {
        image = i;
        this.width = width;
        this.height = height;
        imageSizeX = width;
        imageSizeY = height;
    }

    public void setImage(Image i, double startX, double startY, double width, double height) {
        image = i;
        this.width = width;
        this.height = height;
        this.imageStartX = startX;
        this.imageStartY = startY;
        imageSizeX = width;
        imageSizeY = height;
    }

    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setImage(String filename, double width, double height) {
        Image i = new Image(filename);
        setImage(i, width, height);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }

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

    public void update(double time) {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, this.positionX
                - this.imageSizeX / 2, this.positionY - this.imageSizeY / 2, this.imageSizeX,
                this.imageSizeY);
    }

    public void render(GraphicsContext gc, double positionXX, double positionYY) {
        gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX
                - this.imageSizeX / 2, positionYY - this.imageSizeY / 2, this.imageSizeX,
                this.imageSizeY);
    }

    public void render(GraphicsContext gc, double positionXX, double positionYY, double sizeX,
            double sizeY) {
        gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX - sizeX
                / 2, positionYY - sizeY / 2, sizeX, sizeY);
    }

    public Rectangle2D getBoundary() {
        return new Rectangle2D(positionX, positionY, width, height);
    }

    public boolean intersects(GraphicsContextSprite s) {
        return s.getBoundary().intersects(this.getBoundary());
    }

    public String toString() {
        return " Position: [" + positionX + "," + positionY + "]" + " Velocity: [" + velocityX
                + "," + velocityY + "]";
    }

    public void setImageSize(double imageWidth, double imageHeight) {
        imageSizeX = imageWidth;
        imageSizeY = imageHeight;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

}
