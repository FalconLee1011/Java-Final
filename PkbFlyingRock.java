package peekaboo.props;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Calendar;
import java.security.SecureRandom;
import peekaboo.*;
import peekaboo.role.PkbHuman;

/**
 * PkbFlyingRock:正在飛的石頭，即子彈
 */

public class PkbFlyingRock extends Thread {

  public GameFrame gameFrame;
  public int x = 0, y = 0;
  public int speed = 2;
  public static final int width = 120, height = 120;
  public Image img;
  private String direction;
  private int hasMoved;
  private int tolerance = 20;
  public boolean valid = true;

  public PkbFlyingRock(PkbHuman player, String direction) {
    this.x = player.x;
    this.y = player.y;
    this.direction = direction;
  }

  public void run() {

    try {
      Thread.sleep(200);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (this.direction == "Up") {
      img = new ImageIcon("img/bullet_up.png").getImage();
    } else if (this.direction == "Down") {
      img = new ImageIcon("img/bullet_down.png").getImage();
    } else if (this.direction == "Left") {
      img = new ImageIcon("img/bullet_left.png").getImage();
    } else if (this.direction == "Right") {
      img = new ImageIcon("img/bullet_right.png").getImage();
    }

    while (true) {
      if (this.direction == "Up") {
        this.y -= this.speed;
      } else if (this.direction == "Down") {
        this.y += this.speed;
      } else if (this.direction == "Left") {
        this.x -= this.speed;
      } else if (this.direction == "Right") {
        this.x += this.speed;
      }
      if (this.hasMoved > 500) {
        this.valid = false;
        break;
      }

      this.hasMoved += this.speed;

      try {
        Thread.sleep(2);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    this.img = new ImageIcon("").getImage();
  }
}
