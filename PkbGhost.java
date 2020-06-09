package com.huaxin.mario;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import com.huaxin.enery.*;
import com.huaxin.mario.PkbHuman;
import java.security.SecureRandom;
import java.util.Calendar;

import java.awt.Point;

/**
 * PkbGhost
 */
public class PkbGhost extends Thread{

  public GameFrame gameFrame;
  public int x = 360, y = 360;
  public int rndx = 0, rndy = 360;

  private int range = 1; // Will pursue if player within range, only when isRangedTrigger is set true
  private int patrolRange = 0;
  public int speed = 1;
  public static final int width = 30, height = 30;// 角色的寬高
  public Image img = new ImageIcon("img/ghost_downMove_GIF_160.gif").getImage();// 角色圖片
  public static SecureRandom rnd = new SecureRandom();
  private boolean isMoving = false;
  private boolean isRangedTrigger = false;
  private boolean willPatrol = false;
  private long lastMoved = 0;
  private long patrolInterval = 7000;

  private int tolerance = 20;

  public PkbGhost() { }

  public PkbGhost(int x, int y, int speed) {
    this.x = x;
    this.y = y;
    this.speed = speed;
  }

  public PkbGhost(int x, int y, int speed, boolean isRangedTrigger, int range) {
    this.x = x;
    this.y = y;
    this.speed = speed;
    this.isRangedTrigger = isRangedTrigger;
    this.range = range;
  }

  public PkbGhost(int x, int y, int speed, boolean isRangedTrigger, int range, boolean willPatrol, int patrolRange) {
      this.x = x;
      this.y = y;
      this.speed = speed;
      this.isRangedTrigger = isRangedTrigger;
      this.range = range;
      this.willPatrol = willPatrol;
      this.patrolRange = patrolRange;
  }
  
  public boolean pursue(PkbHuman player){
    if(this.isRangedTrigger){ pursueIsRangedTrigger(player); }
    else{ pursueIsNotRangedTrigger(player); }
    return hasCapturedPlayer(player);
  }

  private void pursueIsNotRangedTrigger(PkbHuman player){
    if(isMoving){
      if(this.x < this.rndx){ 
        this.x += speed;
        this.img = new ImageIcon("img/ghost_rightMove_GIF_160.gif").getImage();
        // TODO: Change pic
      }
      else if(this.x > this.rndx){ 
        this.x -= speed;       
        this.img = new ImageIcon("img/ghost_leftMove_GIF_160.gif").getImage();
        // TODO: Change pic
      }
      if(this.y < this.rndy){ 
        this.y += speed;
        this.img = new ImageIcon("img/ghost_downMove_GIF_160.gif").getImage();
        // TODO: Change pic
      }
      else if(this.y > this.rndy){ 
        this.y -= speed;
        this.img = new ImageIcon("img/ghost_upMove_GIF_160.gif").getImage();
        // TODO: Change pic
      }

      if( (this.x + 30 >= this.rndx || this.x - 30 <= this.rndx) && (this.y + 30 >= this.rndy || this.y - 30 <= this.rndy) ){ 
        this.isMoving = false; 
      }

    }else{
      this.rndx = player.getX();
      this.rndy = player.getY();
      this.isMoving = true;
    }
  }

  private void pursueIsRangedTrigger(PkbHuman player){
    if(isMoving){
      if(this.x < this.rndx){
         this.x += speed;
         this.img = new ImageIcon("img/ghost_rightMove_GIF_160.gif").getImage();
       }
      else if(this.x > this.rndx){ 
        this.x -= speed;
        this.img = new ImageIcon("img/ghost_leftMove_GIF_160.gif").getImage(); }
      if(this.y < this.rndy){ 
        this.y += speed; 
        this.img = new ImageIcon("img/ghost_downMove_GIF_160.gif").getImage();}
      else if(this.y > this.rndy){ 
        this.y -= speed;
        this.img = new ImageIcon("img/ghost_upMove_GIF_160.gif").getImage(); }

      if( (this.x + 30 >= this.rndx || this.x - 30 <= this.rndx) && (this.y + 30 >= this.rndy || this.y - 30 <= this.rndy) ){ 
        this.isMoving = false;
      }
    }else{
      if(isInRange(player)){
          this.rndx = player.getX();
          this.rndy = player.getY();
          this.isMoving = true;
        }
      else{
        if(this.willPatrol){
          if(this.lastMoved == 0 || Calendar.getInstance().getTimeInMillis() - this.lastMoved >= this.patrolInterval){
            this.rndx = rnd.nextInt(range);
            this.rndy = rnd.nextInt(range);
            lastMoved = Calendar.getInstance().getTimeInMillis();
          }
          this.isMoving = true;
        }
        else{ this.isMoving = false; }
      }
    }
  }

  private boolean isInRange(PkbHuman player){
    Rectangle ghostPoly = new Rectangle(this.x - ((width + this.range) / 2) , this.y  - ((height + this.range) / 2), width + range, height + range);
    Rectangle playerPoly = new Rectangle(player.x, player.y, player.width, player.height);
    if (ghostPoly.intersects(playerPoly)) { return true; }
    return false;
  }

  private boolean hasCapturedPlayer(PkbHuman player){
    Rectangle ghostPoly = new Rectangle(this.x - ((width - tolerance) / 2), this.y - ((height - tolerance) / 2), width - tolerance, height - tolerance);
    Rectangle playerPoly = new Rectangle(player.x - ((player.width - tolerance) / 2), player.y - ((player.height - tolerance) / 2), player.width - tolerance, player.height - tolerance);
    if (ghostPoly.intersects(playerPoly)) { return true; }
    return false;
  }

}