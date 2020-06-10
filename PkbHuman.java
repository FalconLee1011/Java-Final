package peekaboo.role;

import java.util.ArrayList;
import java.util.Calendar;
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
import java.security.SecureRandom;
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

    public boolean up = false, down = false, left = false, right = false;
    private static final String Str_Up = "Up", Str_Down = "Down", Str_Left = "Left", Str_Right = "Right";
    public String lastDirection = "Right";
    private static SecureRandom rnd = new SecureRandom();

    private int bound_x = 600 * 2;
    private int bound_y = 300 * 2;

    Timer timer = new Timer();
    private static boolean run = true;
    ArrayList<Integer> bagList = new ArrayList<Integer>(0);
    ArrayList<Enery> bageneryList = new ArrayList<Enery>(0);
    ArrayList<Integer> bagList2 = new ArrayList<Integer>(0);
    ArrayList<Point> pp = new ArrayList<>();

    ArrayList<Enery> backpack = new ArrayList<Enery>(0);

    public boolean pick = false, use = false, div = false, teacher = false, isWitch = false, quickSend = false,camel=false;
    public int num = 0;

    // 如果吃到鞋子//另一個計時
    /*
    public boolean camel = false;
    public long timeSinceCamel = 0;
    public long camelInterval = 120000;
    */
    public PkbHuman(GameFrame g) {
        this.gameFrame = g;
        teleport(1000, 500);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void Time(int t, int tool) {
        TimerTask test = new TimerTask() {
            @Override
            public void run() {
                switch (tool) {
                    case 2:
                        camel = false;
                        break;
                    case 3:
                        quickSend = false;
                        break;
                    case 5:
                        isWitch = false;//蠍子
                        break;
    
                    case 6:
                        teacher = false;
                        break;

                    default:
                        break;
                }
                System.out.println("10秒到了");
                
                
                
                
                xspeed = 8;
                yspeed = 8;
                Toolkit.getDefaultToolkit().beep();
                // timer.cancel();
                // timer.purge();
            }
        };

        timer.schedule(test, t);
        run = false;
    }

    public void run() {
        this.bagList.add(0);

        while (true) {
            if (isWitch == true)
                Witch();
            else
                move();
            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // this.varSinceOrigin_X = 120 - this.x;
            // this.varSinceOrigin_Y = 360 - this.y;
            // System.out.printf("VAR SINCE ORIGIN <%d, %d>%n", this.varSinceOrigin_X,
            // this.varSinceOrigin_Y);
            eneryInteract();
            /*//另一個計時
            if (camel && Calendar.getInstance().getTimeInMillis() - timeSinceCamel >= camelInterval) {
                camel = false;
                xspeed = 8;
                yspeed = 8;
            }
            */
        }
    }

    public void move() {
        // System.out.printf("x: %d, y: %d%n", this.x, this.y);
        // while (true) {
        if (up) {

            // if(bump(gameFrame.eneryList,Str_Up)!=0 && bump(gameFrame.toolList,Str_Up)==0
            // && bump(gameFrame.rockList,Str_Up)==0){//碰觸到道具，道具不影響速度變0 this.yspeed = 0; }

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_upMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_upMove_GIF.gif").getImage();
            }

            this.lastDirection = Str_Up;
            if (this.y >= 100 && this.y <= bound_y) {
                this.y -= this.yspeed;
            } else if (this.y > bound_y || this.y < 100) {
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
                // for (Door door : this.gameFrame.doors) { door.y += this.yspeed; }
            }
        }
        if (down) {
            // if(bump(gameFrame.eneryList,Str_Down)!=0 &&
            // bump(gameFrame.toolList,Str_Down)==0&& bump(gameFrame.rockList,Str_Down)==0){
            // this.yspeed = 0; }

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_downMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_downMove_GIF.gif").getImage();
            }
            lastDirection = Str_Down;
            if (this.y < bound_y) {
                this.y += this.yspeed;
            } else if (this.y >= bound_y) {
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
                // for (Door door : this.gameFrame.doors) { door.y -= this.yspeed; }

            }
            // this.yspeed = 5;
        }
        if (left) {// 向左走
                   // if (bump(gameFrame.eneryList,Str_Left)!=0 &&
                   // bump(gameFrame.toolList,Str_Left)==0&& bump(gameFrame.rockList,Str_Left)==0)
                   // {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_leftMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_leftMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_leftMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_leftMove_GIF.gif").getImage();
            }
            lastDirection = Str_Left;
            if (this.x >= bound_x) {
                this.x -= this.xspeed;
            } else if (this.x < bound_x) {
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
                // for (Door door : this.gameFrame.doors) { door.x += this.xspeed; }

            }
            // this.xspeed = 5;
        }
        if (right) {// 向右走
                    // if (bump(gameFrame.eneryList,Str_Right)!=0 &&
                    // bump(gameFrame.toolList,Str_Right)==0&&
                    // bump(gameFrame.rockList,Str_Right)==0) {//若撞到障礙物 this.xspeed = 0; }

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_rightMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_rightMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_rightMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_rightMove_GIF.gif").getImage();
            }
            lastDirection = Str_Right;
            if (this.x <= bound_x) {
                this.x += this.xspeed;
            } else if (this.x > bound_x) {
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
                // for (Door door : this.gameFrame.doors) { door.x -= this.xspeed; }

            }
            // this.xspeed = 5;
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
            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_upMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_upMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_upMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_upMove_GIF.gif").getImage();
            }
            this.lastDirection = Str_Up;
            if (this.y >= 100 && this.y <= bound_y) {
                this.y -= this.yspeed;
            } else if (this.y > bound_y || this.y < 100) {
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

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_downMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_downMove_GIF.gif").getImage();
            }
            lastDirection = Str_Down;
            if (this.y < bound_y) {
                this.y += this.yspeed;
            } else if (this.y >= bound_y) {
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

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_leftMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_leftMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_leftMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_leftMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_leftMove_GIF.gif").getImage();
            }

            lastDirection = Str_Left;
            if (this.x >= bound_x) {
                this.x -= this.xspeed;
            } else if (this.x < bound_x) {
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

            if (teacher == false && camel == true && quickSend == false)
                this.img = new ImageIcon("img/camelHuman_rightMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == true)
                this.img = new ImageIcon("img/slowHuman_rightMove_GIF.gif").getImage();
            else if (teacher == true && camel == false && quickSend == false)
                this.img = new ImageIcon("img/teacher_rightMove_GIF.gif").getImage();
            else if (teacher == false && camel == false && quickSend == false)
                this.img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();
            else if ((teacher == true && camel == true && quickSend == true)
                    || (teacher == true && camel == true && quickSend == false)
                    || (teacher == true && camel == false && quickSend == true)
                    || (teacher == false && camel == true && quickSend == true)) {
                if (sequence == 6)
                    this.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                else if (sequence == 2)
                    this.img = new ImageIcon("img/camelHuman_rightMove_GIF.gif").getImage();
                else if (sequence == 3)
                    this.img = new ImageIcon("img/slowHuman_rightMove_GIF.gif").getImage();
            }
            lastDirection = Str_Right;
            if (this.x <= bound_x) {
                this.x += this.xspeed;
            } else if (this.x > bound_x) {
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
        try {
            this.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // }
    }

    public void eneryInteract() {
        // 檢查是否碰撞到道具
        Enery bumpedEnery = bump(this.gameFrame.mapEneryByPos);
        if (bumpedEnery != null) {
            System.out.println(bumpedEnery.getClass());
            if (bumpedEnery instanceof Shoe) {
                camel = true;
                //timeSinceCamel = Calendar.getInstance().getTimeInMillis();//另一個計時
                img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
                xspeed = 20;
                yspeed = 20;
                sequence = 2;
                System.out.println("shoe");
                Time(12000,2);
                // 從 bump 判定的字典中將碰撞到的物件移除
                bumpedEnery.img = new ImageIcon("img/back.png").getImage();
                this.gameFrame.mapEneryByPos.get(String.valueOf(bumpedEnery.raw_x * 120))
                        .remove(String.valueOf(bumpedEnery.raw_y * 120));

            } else if (bumpedEnery instanceof Turtle) {
                xspeed = 2;
                yspeed = 2;
                Time(10000,3);
                quickSend = true;
                // 從 bump 判定的字典中將碰撞到的物件移除
                sequence = 3;
                bumpedEnery.img = new ImageIcon("img/back.png").getImage();
                this.gameFrame.mapEneryByPos.get(String.valueOf(bumpedEnery.raw_x * 120))
                        .remove(String.valueOf(bumpedEnery.raw_y * 120));
            } else if (bumpedEnery instanceof Door) {
                // 若是 Door
                int rnd_door = rnd.nextInt(this.gameFrame.doors.size());
                Door door = this.gameFrame.doors.get(rnd_door);
                teleport(door.y, door.x + 120);
            } else if (bumpedEnery instanceof Fruit) {
                img = new ImageIcon("img/camelHuman_downMove_GIF.gif").getImage();
                teacher = true;
                sequence = 6;
                for (PkbGhost ghost : this.gameFrame.ghosts) {
                    ghost.rageActivated = true;
                }
                Time(10000,6);
                // 從 bump 判定的字典中將碰撞到的物件移除
                bumpedEnery.img = new ImageIcon("img/back.png").getImage();
                this.gameFrame.mapEneryByPos.get(String.valueOf(bumpedEnery.raw_x * 120))
                        .remove(String.valueOf(bumpedEnery.raw_y * 120));

                // backpack.add(bumpedEnery);
            } else if (bumpedEnery instanceof Bewitch) {

                img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                isWitch = true;
                Time(10000,5);
                // 從 bump 判定的字典中將碰撞到的物件移除
                bumpedEnery.img = new ImageIcon("img/back.png").getImage();
                this.gameFrame.mapEneryByPos.get(String.valueOf(bumpedEnery.raw_x * 120))
                        .remove(String.valueOf(bumpedEnery.raw_y * 120));

                // backpack.add(bumpedEnery);
            }
            // TODO: 其他道具

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
            this.gameFrame.backEneryByPos.get(String.valueOf(diggableEnery.raw_x * 120))
                    .remove(String.valueOf(diggableEnery.raw_y * 120));
            this.backpack.add(diggableEnery);
        }

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

    // public

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

    public void teleport(int target_y, int target_x) {
        // MOVE Y
        int var_y = this.y - target_y;
        int var_x = this.x - target_x;

        // System.out.printf("CURRENT: <%d, %d> | MOVE TO <%d, %d> ", this.x, this.y,
        // target_x, target_y);
        // System.out.printf("VAR: <%d, %d>%n", var_x, var_y);

        gameFrame.bg.y += var_y;
        for (Enery enery : gameFrame.eneryList) {
            enery.y += var_y;
        }
        for (PkbGhost ghost : this.gameFrame.ghosts) {
            ghost.y += var_y;
        }
        for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
            fock.y += var_y;
        }

        gameFrame.bg.x += var_x;
        for (Enery enery : gameFrame.eneryList) {
            enery.x += var_x;
        }
        for (PkbGhost ghost : this.gameFrame.ghosts) {
            ghost.x += var_x;
        }
        for (PkbFlyingRock fock : this.gameFrame.flyingRocks) {
            fock.x += var_x;
        }

        // System.out.printf("MOVED TO: <%d, %d>%n%n ", this.x, this.y);

    }

}