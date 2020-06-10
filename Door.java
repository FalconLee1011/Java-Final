package peekaboo.props;

import java.awt.Image;

/**
 * 任意門 從某地順移到某地
 */
public class Door extends Enery {
    
    public int doorID;

    public Door(int x, int y, int width, int height, Image img, int doorID) {
        super(x, y, width, height, img);
        this.doorID = doorID;
    }

}
