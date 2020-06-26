package peekaboo.props;

import java.awt.Image;

/**
 * 任意門:從A地順移到B地(隨機換)
 */

public class Door extends Enery {

    public int doorID;
    public int absoluteX, absoluteY;

    public Door(int x, int y, int width, int height, Image img, int doorID) {
        super(x, y, width, height, img);
        this.absoluteX = x;
        this.absoluteY = y;
        this.doorID = doorID;
    }
}
