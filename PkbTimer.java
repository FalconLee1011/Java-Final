package peekaboo;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import peekaboo.props.*;

/*
 * 創建計時器
 */

public class PkbTimer {
    private Timer timergame = new Timer();
    private int count = 0;
    private boolean isRun = true;
    public long midTime = 3000;// !
    public long countMin = 0, countSec = 0, countMSec = 0;
    public long MapMin = 0, MapSec = 0, MapMSec = 0;

    public void timerStart() {
        TimerTask gametest = new TimerTask() {
            @Override
            public void run() {
                if (midTime > 0) {
                    midTime--;
                    count++;
                    // 計時賽的時間---計時器
                    countMin = midTime / 20 / 60 % 60;
                    countSec = midTime / 20 % 60;
                    countMSec = midTime % 100;
                    // 正常模式的時間---倒數計時器
                    MapMin = count / 20 / 60 % 60;
                    MapSec = count / 20 % 60;
                    MapMSec = count % 100;

                    Toolkit.getDefaultToolkit().beep();
                    timergame.purge();
                } else
                    timergame.purge();
            }
        };
        timergame.schedule(gametest, 1000);
        isRun = false;
    }
}