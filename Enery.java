package peekaboo.huaxin.enery;

import java.awt.Image;

public abstract class Enery {//障礙物的抽象父類

    public int x, y;
    public int raw_x, raw_y;
    public int width, height;
    public Image img;

    public Enery(int x, int y, int width, int height, Image img) {
        this.raw_x = y / 30;
        this.raw_y = x / 30;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }
}