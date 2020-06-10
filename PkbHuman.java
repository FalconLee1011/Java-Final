package peekaboo.role;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import peekaboo.props.*;
import peekaboo.*;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.awt.Toolkit;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class PkbHuman extends Thread {
    // ArrayList<Integer> arrlist = new ArrayList<Integer<(8);
    public KeyListener key;

    public GameFrame gameFrame;// 遊戲地圖
    public int x = 120, y = 360;// 角色的坐標(一開始在左下角)
    public int xspeed = 8, yspeed = 8;// 角色的坐標(一開始在左下角)
    public int sequence = 0;// 記錄誰最後
    public static final int width = 120, height = 120;// 角色的寬高
    public Image img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();// 角色圖片

    public boolean up = false, down = false, left = false, right = false, camel = false;
    private static final String Str_Up = "Up", Str_Down = "Down", Str_Left = "Left", Str_Right = "Right";
    public String lastDirection = "Right";

    Timer timer = new Timer();
    private static boolean run = true;
    ArrayList<Integer> bagList = new ArrayList<Integer>(0);
    ArrayList<Enery> bageneryList = new ArrayList<Enery>(0);
    ArrayList<Integer> bagList2 = new ArrayList<Integer>(0);
    ArrayList<Point> pp = new ArrayList<>();

    ArrayList<Enery> backpack = new ArrayList<Enery>(0);

    public boolean pick = false, use = false, div = false, teacher = false, isWitch = false;
    public int num = 0;

    public PkbHuman(GameFrame g) {
        this.gameFrame = g;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void Time(int t) {
        TimerTask test = new TimerTask() {
            @Override
            public void run() {
                System.out.println("10秒到了");
                teacher = false;
                isWitch = false;
                camel = false;
                xspeed = 8;
                yspeed = 8;
                Toolkit.getDefaultToolkit().beep();
                // timer.cancel();
                timer.purge();
            }
        };

        timer.schedule(test, t);
        run = false;
    }

    public void run() {
        this.bagList.add(0);

        // this.bagList2.add(0);
        // pp.add(event.getPoint());//如果是滑鼠event，他的(x,y)
        // pp.x=super.x; //int x座標
        // pp.y; //int y座標
        // System.out.println("point:" + pp.x);
        while (true) {

            if (isWitch == true)
                Witch();

            else
                move();
            // 檢查是否碰撞到道具
            Enery bumpedEnery = bump(this.gameFrame.mapEneryByPos);
            if (bumpedEnery != null) {
                System.out.println(bumpedEnery.getClass());
                bumpedEnery.img = new ImageIcon("img/back.png").getImage();
                // 從 bump 判定的字典中將碰撞到的物件移除
                this.gameFrame.mapEneryByPos.get(String.valueOf(bumpedEnery.raw_x * 120))
                        .remove(String.valueOf(bumpedEnery.raw_y * 120));
                if (bumpedEnery instanceof Shoe) {
                    img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
                    xspeed = 20;
                    yspeed = 20;
                    camel = true;
                    sequence = 2;
                    System.out.println("shoe");
                    Time(12000);
                } else if (bumpedEnery instanceof Turtle) {
                    xspeed = 2;
                    yspeed = 2;
                    Time(10000);
                } else if (bumpedEnery instanceof Stone) {
                    // 若道具是 Stone (pipe) 則放入背包
                    backpack.add(bumpedEnery);
                } else if (bumpedEnery instanceof Fruit) {
                    img = new ImageIcon("img/downMove_GIF.gif").getImage();
                    teacher = true;
                    sequence = 6;

                    Time(10000);
                    // backpack.add(bumpedEnery);
                } else if (bumpedEnery instanceof Bewitch) {
                    img = new ImageIcon("img/downMove_GIF.gif").getImage();
                    isWitch = true;
                    Time(10000);
                    // backpack.add(bumpedEnery);
                }
                // TODO: 其他道具

            } else {
                // TODO: 非道具類互動
                // 若沒有碰撞到道具，則檢查是否可以被挖
                // System.out.println(isDiggable());
            }
            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        // System.out.printf("x: %d, y: %d%n", this.x, this.y);
        // while (true) {
        if (up) {

            // if(bump(gameFrame.eneryList,Str_Up)!=0 && bump(gameFrame.toolList,Str_Up)==0
            // && bump(gameFrame.rockList,Str_Up)==0){//碰觸到道具，道具不影響速度變0 this.yspeed = 0; }
            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/upMove_GIF.gif").getImage();

            this.lastDirection = Str_Up;
            if (this.y >= 0 && this.y <= 300) {
                this.y -= this.yspeed;
            } else if (this.y > 300) {
                gameFrame.bg.y += this.yspeed;// 背景向下移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.y += this.yspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.y += this.yspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.y += this.yspeed;
                }
            }
        }
        if (down)

        {
            // if(bump(gameFrame.eneryList,Str_Down)!=0 &&
            // bump(gameFrame.toolList,Str_Down)==0&& bump(gameFrame.rockList,Str_Down)==0){
            // this.yspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Down;
            if (this.y < 300) {
                this.y += this.yspeed;
            } else if (this.y >= 300) {
                gameFrame.bg.y -= this.yspeed;// 背景向上移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.y -= this.yspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.y -= this.yspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.y -= this.yspeed;
                }
            }
            // this.yspeed = 5;
        }
        if (left) {// 向左走
                   // if (bump(gameFrame.eneryList,Str_Left)!=0 &&
                   // bump(gameFrame.toolList,Str_Left)==0&& bump(gameFrame.rockList,Str_Left)==0)
                   // {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Left;
            if (this.x >= 650) {
                this.x -= this.xspeed;
            } else if (this.x < 650) {
                gameFrame.bg.x += this.xspeed;// 背景向右移動
                // 障礙物項右移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.x += this.xspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.x += this.xspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.x += this.xspeed;
                }
            }
            // this.xspeed = 5;
        }
        if (right) {// 向右走
                    // if (bump(gameFrame.eneryList,Str_Right)!=0 &&
                    // bump(gameFrame.toolList,Str_Right)==0&&
                    // bump(gameFrame.rockList,Str_Right)==0) {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_rightMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_rightMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Right;
            if (this.x <= 650) {
                this.x += this.xspeed;
            } else if (this.x > 650) {
                gameFrame.bg.x -= this.xspeed;// 背景向左移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.x -= this.xspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.x -= this.xspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.x -= this.xspeed;
                }
            }
            // this.xspeed = 5;
        }
        if (this.use) {
            if (this.backpack.size() != 0) {
                this.backpack.remove(this.backpack.size() - 1);
                PkbFlyingRock fr = new PkbFlyingRock(this, this.lastDirection);
                this.gameFrame.flyingRocks.add(fr);
                fr.start();
            }
        }
        if (this.pick &&

                isDiggable()) {
            Enery diggableEnery = bump(this.gameFrame.backEneryByPos);
            diggableEnery.img = new ImageIcon("img/dig.png").getImage();
            this.gameFrame.mapEneryByPos.get(String.valueOf(diggableEnery.raw_x * 120))
                    .remove(String.valueOf(diggableEnery.raw_y * 120));
            this.backpack.add(diggableEnery);
        }
        try {
            this.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }
    }

    public void Witch() {
        // System.out.printf("x: %d, y: %d%n", this.x, this.y);
        // while (true) {
        if (down) {

            // if(bump(gameFrame.eneryList,Str_Up)!=0 && bump(gameFrame.toolList,Str_Up)==0
            // && bump(gameFrame.rockList,Str_Up)==0){//碰觸到道具，道具不影響速度變0 this.yspeed = 0; }
            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            this.lastDirection = Str_Up;
            if (this.y >= 0 && this.y <= 300) {
                this.y -= this.yspeed;
            } else if (this.y > 300) {
                gameFrame.bg.y += this.yspeed;// 背景向下移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.y += this.yspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.y += this.yspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.y += this.yspeed;
                }
            }
        }
        if (up) {
            // if(bump(gameFrame.eneryList,Str_Down)!=0 &&
            // bump(gameFrame.toolList,Str_Down)==0&& bump(gameFrame.rockList,Str_Down)==0){
            // this.yspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Down;
            if (this.y < 300) {
                this.y += this.yspeed;
            } else if (this.y >= 300) {
                gameFrame.bg.y -= this.yspeed;// 背景向上移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.y -= this.yspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.y -= this.yspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.y -= this.yspeed;
                }
            }
            // this.yspeed = 5;
        }
        if (right) {// 向左走
            // if (bump(gameFrame.eneryList,Str_Left)!=0 &&
            // bump(gameFrame.toolList,Str_Left)==0&& bump(gameFrame.rockList,Str_Left)==0)
            // {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_rightMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_rightMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Left;
            if (this.x >= 650) {
                this.x -= this.xspeed;
            } else if (this.x < 650) {
                gameFrame.bg.x += this.xspeed;// 背景向右移動
                // 障礙物項右移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.x += this.xspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.x += this.xspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.x += this.xspeed;
                }
            }
            // this.xspeed = 5;
        }
        if (left) {// 向右走
            // if (bump(gameFrame.eneryList,Str_Right)!=0 &&
            // bump(gameFrame.toolList,Str_Right)==0&&
            // bump(gameFrame.rockList,Str_Right)==0) {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == true && camel == true) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            } else if (teacher == false && camel == true)
                this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false)
                this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();

            else if (teacher == true && camel == false)
                this.img = new ImageIcon("img/downMove_GIF.gif").getImage();
            lastDirection = Str_Right;
            if (this.x <= 650) {
                this.x += this.xspeed;
            } else if (this.x > 650) {
                gameFrame.bg.x -= this.xspeed;// 背景向左移動
                // 障礙物項左移動
                for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                    Enery enery = gameFrame.eneryList.get(i);
                    enery.x -= this.xspeed;
                }
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.x -= this.xspeed;
                }
                for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
                    fock.x -= this.xspeed;
                }
            }
            // this.xspeed = 5;
        }
        if (this.use) {
            if (this.backpack.size() != 0) {
                this.backpack.remove(this.backpack.size() - 1);
                PkbFlyingRock fr = new PkbFlyingRock(this, this.lastDirection);
                this.gameFrame.flyingRocks.add(fr);
                fr.start();
            }
        }
        if (this.pick && isDiggable()) {
            Enery diggableEnery = bump(this.gameFrame.backEneryByPos);
            diggableEnery.img = new ImageIcon("img/dig.png").getImage();
            this.gameFrame.mapEneryByPos.get(String.valueOf(diggableEnery.raw_x * 120))
                    .remove(String.valueOf(diggableEnery.raw_y * 120));
            this.backpack.add(diggableEnery);
        }
        try {
            this.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }
    }

    public Enery bump(Map<String, Map<String, Enery>> eneryByPos) {
        // Rectangle playerScanPoly = new Rectangle( this.x - width, this.y - height,
        // width * 2, height * 2 );
        Rectangle playerPoly = new Rectangle(this.x - (width / 2), this.y - (height / 2), width, height);
        Map<String, Map<String, Enery>> mapEneryByPos = eneryByPos;
        Set<String> keys = mapEneryByPos.keySet();
        for (String k : keys) {
            for (String y_l : mapEneryByPos.get(k).keySet()) {
                Enery e = mapEneryByPos.get(k).get(y_l);
                Rectangle eneryPoly = new Rectangle(e.x - (e.width / 2), e.y - (e.height / 2), e.width, e.height);
                if (playerPoly.intersects(eneryPoly)) {
                    return e;
                }
            }
        }
        return null;
    }

    public boolean isDiggable() {
        Rectangle playerPoly = new Rectangle(this.x - (width / 2), this.y - (height / 2), width, height);
        Map<String, Map<String, Enery>> backEneryByPos = this.gameFrame.backEneryByPos;
        Set<String> keys = backEneryByPos.keySet();
        for (String k : keys) {
            for (String y_l : backEneryByPos.get(k).keySet()) {
                Enery e = backEneryByPos.get(k).get(y_l);
                Rectangle eneryPoly = new Rectangle(e.x - (e.width / 2), e.y - (e.height / 2), e.width, e.height);
                if (playerPoly.intersects(eneryPoly)) {
                    return true;
                }
            }
        }
        return false;
    }

}