package peekaboo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.json.*;
import javax.swing.ImageIcon;
import java.security.SecureRandom;
import peekaboo.props.*;

public class PkbAPIHandler {

  public String response, gameID;
  public JSONObject obj;
  public int playerID;
  public GameFrame gameFrame;

  public PkbAPIHandler() {
  }

  public PkbAPIHandler(GameFrame g) {
    this.gameFrame = g;
  }

  public String get(String targetUrl) {
    HttpURLConnection connection = null;
    StringBuffer sb = new StringBuffer("");

    try {
      URL url = new URL(targetUrl);// 建立連接對象
      connection = (HttpURLConnection) url.openConnection();// 通過URL打開連接，並強制轉成httpURLConnection類型
      connection.setRequestMethod("GET");// 設定連接方式: GET

      connection.setDoOutput(true);// 默認為false,傳送數據/寫數據時，需要設定為true
      connection.setDoInput(true);// 默認為true,讀取數據時，需要為true
      connection.setUseCaches(false);
      connection.setInstanceFollowRedirects(true);
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setRequestProperty("Accept", "application/json");
      connection.setRequestProperty("Accept-Charset", "UTF-8");

      connection.setConnectTimeout(15000);// 連接主機服務器超時時間: 15000 ms
      connection.setReadTimeout(60000);// 讀取返回的超時時間: 60000 ms
      connection.connect();// 發送請求

      if (connection.getResponseCode() == 200) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
        String lines = "";
        while ((lines = reader.readLine()) != null) {
          lines = new String(lines.getBytes(), "utf-8");
          sb.append(lines);
        }
      }
      connection.disconnect();// 關閉連接
    }

    catch (MalformedURLException e) {
      throw e;
    } catch (UnsupportedEncodingException e) {
      throw e;
    } catch (IOException e) {
      throw e;
    } finally {
      this.response = sb.toString();
      return this.response;
    }
  }

  public String create_game() {
    String response = this.get("http://140.121.197.14:8756/create_game");
    this.obj = new JSONObject(this.response);
    this.gameID = obj.getString("gameID");
    return this.gameID;
  }

  public int joinGame() {
    String response = this.get("http://140.121.197.14:8756/add_player?game=" + this.gameID);
    this.obj = new JSONObject(this.response);
    this.playerID = obj.getInt("playerID");
    return this.playerID;
  }

  public void update_game(int x, int y) {
    this.get("http://140.121.197.14:8756/update_game?game=" + this.gameID + "&playerID="
        + Integer.toString(this.playerID) + "&x=" + Integer.toString(x) + "&y=" + Integer.toString(y));
  }

  public void get_game() {
    String response = this.get("http://140.121.197.14:8756/get_game?game=" + this.gameID);
    JSONObject game;
    int playerCount;
    try {
      // System.out.println(this.response);
      this.obj = new JSONObject(this.response);
      game = obj.getJSONObject("game");
      playerCount = game.getInt("playerCount");
    } catch (Exception errrrr) {
      return;
    }
    if (playerCount == 0)
      return;

    ArrayList<PkbOnlinePlayer> onlinePlayers = new ArrayList<PkbOnlinePlayer>();

    for (int i = 0; i < playerCount; i++) {
      if (i == this.playerID)
        continue;
      Image img = new ImageIcon("img/downMove_gif_160_"+i+".gif").getImage();// 角色圖片
      int x = game.getJSONObject(Integer.toString(i)).getInt("x");
      int y = game.getJSONObject(Integer.toString(i)).getInt("y");
      PkbOnlinePlayer op = new PkbOnlinePlayer(x, y, 120, 120, img);
      // System.out.printf("Player %d is at <%d, %d>%n", i, x, y);
      onlinePlayers.add(op);
    }
    this.gameFrame.onlinePlayers = onlinePlayers;
    // System.out.printf("%n");
  }

  public int getPlayerCount() {
    String response = this.get("http://140.121.197.14:8756/get_game?game=" + this.gameID);
    this.obj = new JSONObject(this.response);
    int playerCount = obj.getJSONObject("game").getInt("playerCount");
    return playerCount;
  }

  public void invalid_game() {
    this.get("http://140.121.197.14:8756/invalid_game?game=" + this.gameID);
  }

  public static void main(String args[]) {
    PkbAPIHandler newOnlineGame = new PkbAPIHandler();
    // String response = newOnlineGame.create_game();
    // System.out.println(newOnlineGame.create_game());
    // System.out.println(newOnlineGame.joinGame());
    // System.out.println(newOnlineGame.joinGame());
    // newOnlineGame.get_game();
    // System.out.println(newOnlineGame.gameID);
  }
}