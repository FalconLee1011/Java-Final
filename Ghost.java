package com.huaxin.mario;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import com.huaxin.enery.*;
import com.huaxin.mario.PkbHuman;

import java.awt.Point;
import java.security.SecureRandom;

/**
 * PkbGhost 
 */
public class Ghost extends Thread{

    public GameFrame gameFrame;
    public int x = 360, y = 360;
    public int rndx = 0, rndy = 360;
    public int xspeed = 1, yspeed = 1;
    public static final int width = 30, height = 30;// 角色的寬高
    public Image img = new ImageIcon("img/moonmoon.png").getImage();// 角色圖片
    public static SecureRandom rnd = new SecureRandom();
    private boolean isMoving = false;

    public Ghost() {
        // this.gameFrame = g;
    }
    
    public void pursue(PkbHuman player){
      // System.out.println("ghost in pursue!");
      // System.out.printf("Target at <%d, %d>!%n", player.getX(), player.getY());
      if(isMoving){
        if(this.x < this.rndx){ this.x += xspeed; }
        else if(this.x > this.rndx){ this.x -= xspeed; }
        if(this.y < this.rndy){ this.y += yspeed; }
        else if(this.y > this.rndy){ this.y -= yspeed; }

        if(
            (this.x + 30 >= this.rndx || this.x - 30 <= this.rndx)
            &&
            (this.y + 30 >= this.rndy || this.y - 30 <= this.rndy)
          ){ this.isMoving = false; }

      }else{
        // this.rndx = rnd.nextInt(30);
        //this.rndx = player.getX();
        this.rndy = rnd.nextInt(360);
        // this.rndy = player.getY();
        this.isMoving = true;
      }
    }
  
}