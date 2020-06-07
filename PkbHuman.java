package com.huaxin.mario;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import com.huaxin.enery.*;
import java.awt.Point;

public class PkbHuman extends Mario {
    // ArrayList<Integer> arrlist = new ArrayList<Integer<(8);
    ArrayList<Integer> bagList = new ArrayList<Integer>(0);
    ArrayList<Enery> bageneryList = new ArrayList<Enery>(0);
    ArrayList<Integer> bagList2 = new ArrayList<Integer>(0);
    ArrayList<Point> pp = new ArrayList<>();

    public boolean pick = false, use = false, div = false;
    public int num=0;
    public PkbHuman(GameFrame g) {
        super(g);
    }
    
                        
    public void run() {
        this.bagList.add(0);
        
        //this.bagList2.add(0);
        //pp.add(event.getPoint());//如果是滑鼠event，他的(x,y)
        //pp.x=super.x; //int x座標
       //pp.y; //int y座標
        //System.out.println("point:" +  pp.x);
        while (true) {           
            int j = bump(gameFrame.toolList, "Left");//觸碰到
            
            if (j != 0) {//道具類
                int temp = gameFrame.toolList2.get(j);
                Enery enery = gameFrame.toolList.get(j);
                this.bagList2.add(temp);// 放入
                if (bagList2.get(bagList2.size() - 1) == 0) {
                    
                    
                    //System.out.println("222222222 ");

                }
                if (bagList2.get(bagList2.size() - 1) == 2) {

                    super.xspeed = 20;
                    super.yspeed = 20;
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    

                } else if (bagList2.get(bagList2.size() - 1) == 3) {
                    super.xspeed = 2;
                    super.yspeed = 2;
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    

                    
                }
                else if (bagList2.get(bagList2.size() - 1) == 4) {//門
                    enery.img=new ImageIcon("img/back.png").getImage();

                }
                else if (bagList2.get(bagList2.size() - 1) == 5) {//迷失
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    
                }
            }
            
            if (pick) {// 撿起來(石頭類)
                int i = bump(gameFrame.rockList, "Left");
                
                if (i != 0) {// 若有
                    Enery enery = gameFrame.rockList.get(i);
                    int tooltemp = gameFrame.rockList2.get(i);
                    this.bagList.add(tooltemp);// 放入
                    this.bageneryList.add(enery);// 放入bageneryList
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    enery.img=new ImageIcon("img/dig.png").getImage();
                    num++;
                    for (int A : bagList) {
                        System.out.println("Number = " + A+" "+num);
                    }
                }
            }
            if (use) {// 用起來石頭區
                    if (num>0)
                    {
                        Enery enery=this.bageneryList.get(bageneryList.size() - 1);
                        enery.x=this.x;
                        enery.y=this.y+30;
                        num=num-1;
                        System.out.println("use num= "+num);
                    }
                    else
                    {
                        System.out.println("沒石頭了 ");
                    }
                } 
                


            super.run();   
                 
            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}