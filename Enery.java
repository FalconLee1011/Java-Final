package peekaboo.props;

import java.awt.Image;

/**
 * 物品的抽象父類
 */
public abstract class Enery {

    public int x, y;// 位置
    public int arrRow, arrCol;// 分別對應到map[i][j]的i,j
    public int width, height;
    public Image img;

    public Enery(int x, int y, int width, int height, Image img) {
        this.arrRow = y / 120;
        this.arrCol = x / 120;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}