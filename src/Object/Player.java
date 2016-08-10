
package Object;

import GraphicUtility.GraphicsContextSprite;

/**
 * @author KJW finish at 2016/ 08/ 11
 * @version 2.0.0v
 * @description this class Player Class using for Meteor Game
 * @copyRight of KJW all Rights Reserved and follow the MIT license
 */
public class Player extends GraphicsContextSprite {

	private String sPlayerName;

	/**
	 * Player constructor
	 * 
	 * @param filename
	 *            - path for Meteor Game player image
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public Player(String filename, double width, double height) {
		super(filename, width, height);
	}

	/**
	 * Player constructor
	 * 
	 * @param filename
	 *            - path for Meteor Game player image
	 * @param startX
	 *            - image start position x in image file standard on left up
	 * @param startY
	 *            - image start position y in image file standard on left up
	 * @param width
	 *            - image width
	 * @param height
	 *            - image height
	 */
	public Player(String filename, double startX, double startY, double width, double height) {
		super(filename, startX, startY, width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * get player unique name string
	 * 
	 * @return
	 */
	public String getsPlayerName() {
		return sPlayerName;
	}

	/**
	 * set player unique name string
	 * 
	 * @param sPlayerName
	 */
	public void setsPlayerName(String sPlayerName) {
		this.sPlayerName = sPlayerName;
	}

}
