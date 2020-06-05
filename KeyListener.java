package com.huaxin.mario;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import java.awt.*;
//import sun.tools.asm.CatchData;



//鍵盤按下監聽類
 
public class KeyListener extends KeyAdapter {
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public GameFrame gf;
    public KeyListener(GameFrame gf) {
        this.gf = gf;
    }
    // 鍵盤監聽
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 37:// 向左走
            System.out.println("按keyboard: 左鍵");
                gf.human.left = true;
                break;
            case 38:// 向上跳
            System.out.println("按keyboard: 上鍵");
                gf.human.up = true;
                break;
            case 39: // 向右走
                System.out.println("按keyboard: 右鍵");
                gf.human.right = true;
                break;
            case 40:
                System.out.println("按keyboard: 下鍵");
                gf.human.down = true;
                break;
            case 65:
                
                System.out.println("按keyboard: A");
                gf.human.pick=true;
                /*
               try{
                   gf.map[gf.human.x/30+7][(gf.human.y/30)-7]=7;
               }catch(ArrayIndexOutOfBoundsException e1){
                    System.err.println("視窗大小:"+screenSize.getWidth()+" "+screenSize.getHeight());
                    System.err.println("map大小:"+gf.map[1].length+" "+gf.map.length);
                    System.err.println("人的x:"+gf.human.x+"   y:"+gf.human.y);
               }
                
                gf.repaint();
                System.out.println("map"+gf.map[gf.human.x/30-5][(gf.human.y/30)+5]);
                for (int i = 0; i < gf.map.length; i++) {// 讀取地圖，並配置地圖
                    for (int j = 0; j < gf.map[0].length; j++) {
                        System.out.printf(gf.map[i][j]+" ");
                       }
                       System.out.println();
                    }
                    */
                break;
            case 87:
                System.out.println("按keyboard: W");
                gf.human.use=true;
                break;
        }
    }
    // 添加子彈
    // public void addBoom() {
    //     Boom b = new Boom(gf.mario.x, gf.mario.y + 5, 10);
    //     if (gf.mario.left)
    //         b.speed = -2;
    //     if (gf.mario.right)
    //         b.speed = 2;
    //     gf.boomList.add(b);
    // }
    // 鍵盤釋放監聽
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 37:// 向左走
                System.out.println("釋放keyboard: 左鍵");
                gf.human.left = false;
                gf.human.img = new ImageIcon("img/human.png").getImage();
                break;
            case 38:// 向上跳
                System.out.println("釋放keyboard: 上鍵");
                gf.human.up = false;
                gf.human.img = new ImageIcon("img/human.png").getImage();   
                break;
            case 39: // 向右走
                System.out.println("釋放keyboard: 右鍵");
                gf.human.right = false;
                gf.human.img = new ImageIcon("img/human.png").getImage();
                break;
            case 40:
                System.out.println("釋放keyboard: 下鍵");
                gf.human.down = false;
                gf.human.img = new ImageIcon("img/human.png").getImage();
                break;
            case 65:
                System.out.println("釋放keyboard: A");
                gf.human.pick = false;
                gf.human.img = new ImageIcon("img/human.png").getImage();
                break;
             case 87:
                System.out.println("按keyboard: W");
                 gf.human.use = false;
        
             break;
        }
    }
}
