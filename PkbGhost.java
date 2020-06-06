package com.huaxin.mario;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import com.huaxin.enery.*;
import com.huaxin.mario.PkbHuman;

import java.awt.Point;

/**
 * PkbGhost
 */
public class PkbGhost extends Ghost{

    public PkbGhost(GameFrame g) {
        // super(g);
        // this.touchFloor();
    }

    public void pursue(PkbHuman player){ super.pursue(player); }
  
}