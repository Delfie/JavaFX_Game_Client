
package GraphicUtility;

/**
 * @author KJW finish at 2016/ 02/ 15
 * @version 1.0.0v
 * @description this class for the Sprite, this class manage the state of the
 *              catchMe board.
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Sprite {
    /**
     * Image Position URL
     */
    String imageURL;

    /**
     * image start position x for viewport on imageView
     */
    double imageStartX;

    /**
     * image start position y for viewport on imageView
     */
    double imageStartY;

    /**
     * image width for viewport on imageView
     */
    double imageWidth;

    /**
     * image height for viewport on imageView
     */
    double imageHeight;

    /**
     * init The Sprite Using imageURL and imageStartX, imageStartY, imageWidth,
     * imageHeight
     * 
     * @param imageURL
     * @param imageStartX
     * @param imageStartY
     * @param imageWidth
     * @param imageHeight
     */
    public Sprite(String imageURL, double imageStartX, double imageStartY, double imageWidth,
            double imageHeight) {
        super();
        this.imageURL = "/Asset/" + imageURL + ".png";
        this.imageStartX = imageStartX;
        this.imageStartY = imageStartY;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    /**
     * get the imageURL String
     * 
     * @return
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * set The String of imageURL ( about Image File Location)
     * 
     * @param imageURL
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * get the imageStartX in the PNG file for the viewport on the imageView
     * double
     * 
     * @return
     */
    public double getImageStartX() {
        return imageStartX;
    }

    /**
     * set The start x of Image in the PNG File (Sprite Image File)
     * 
     * @param imageStartX
     */
    public void setImageStartX(double imageStartX) {
        this.imageStartX = imageStartX;
    }

    /**
     * get the imageStartY in the PNG file for the viewport on the imageView
     * double
     * 
     * @return
     */
    public double getImageStartY() {
        return imageStartY;
    }

    /**
     * set The start y of Image in the PNG File (Sprite Image File)
     * 
     * @param imageStartY
     */
    public void setImageStartY(double imageStartY) {
        this.imageStartY = imageStartY;
    }

    /**
     * get the image Width in the PNG file for the viewport on the imageView
     * double
     * 
     * @return
     */
    public double getImageWidth() {
        return imageWidth;
    }

    /**
     * set The Width of Image in the PNG File (Sprite Image File)
     * 
     * @param imageWidth
     */
    public void setImageWidth(double imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * get the image Height in the PNG file for the viewport on the imageView
     * double
     * 
     * @return
     */
    public double getImageHeight() {
        return imageHeight;
    }

    /**
     * set The Height of Image in the PNG File (Sprite Image File)
     * 
     * @param imageHeight
     */
    public void setImageHeight(double imageHeight) {
        this.imageHeight = imageHeight;
    }

}
