package peekaboo.props;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
// import peekaboo.huaxin.enery.*;
// import peekaboo.huaxin.mario.PkbHuman;

import peekaboo.props.*;
import peekaboo.role.PkbHuman;
import peekaboo.*;

import java.security.SecureRandom;
import java.util.Calendar;

import java.awt.Point;

/**
 * PkbGhost
 * 撿起來的地板
 */
public class PkbFlyingRock extends Thread{

  public GameFrame gameFrame;
  public int x = 0, y = 0;
  public int speed = 2;
  public static final int width = 120, height = 120;
  public Image img = new ImageIcon("img/ghost_downMove_GIF_160.gif").getImage();

  private int tolerance = 20;

  public PkbFlyingRock(PkbHuman player) { 
    this.x = player.x;
    this.y = player.y;
  }

}
