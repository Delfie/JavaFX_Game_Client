
package J_R_C.JOGL.BaseGame;

public class Player extends GraphicsContextSprite {

    private String sPlayerName;

    public Player(String filename, double width, double height) {
        super(filename, width, height);
    }

    public Player(String filename, double startX, double startY, double width, double height) {
        super(filename, startX, startY, width, height);
        // TODO Auto-generated constructor stub
    }

    public String getsPlayerName() {
        return sPlayerName;
    }

    public void setsPlayerName(String sPlayerName) {
        this.sPlayerName = sPlayerName;
    }

}
