import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.OutputStream;

import java.net.URL;
import java.net.http.HttpRequest;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import org.json.*;

public class requestTestjava {
	public static String httpRequestTest(String targetUrl) {
		HttpURLConnection connection = null;
		StringBuffer sb = new StringBuffer("");

		try {
			URL url = new URL(targetUrl);// 建立連接對象
			connection = (HttpURLConnection) url.openConnection();// 通過URL打開連接，並強制轉成httpURLConnection類型

			// connection.setRequestMethod(method);// POST,GET,POST,DELETE,INPUT
			connection.setRequestMethod("GET");// 設定連接方式: GET

			connection.setDoOutput(true);// 默認為false,傳送數據/寫數據時，需要設定為true
			connection.setDoInput(true);// 默認為true,讀取數據時，需要為true
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);

			// connection.setRequestProperty("Content-Type", "application/json");
			// 傳入參數的格式(Content-Type): 參數應該是 "name1=value1&name2=value2" 的形式
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			// connection.setRequestProperty("Content-Type", "text/xml");
			connection.setRequestProperty("Accept", "application/json");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			// connection.setRequestProperty("charset", "UTF-8");

			connection.setConnectTimeout(15000);// 連接主機服務器超時時間: 15000 ms
			connection.setReadTimeout(60000);// 讀取返回的超時時間: 60000 ms
			connection.connect();// 發送請求
			/*
			 * OutputStream out = connection.getOutputStream();
			 * out.write(requestBodyJson.getBytes()); out.flush(); out.close();
			 */
			if (connection.getResponseCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

				String lines = "";
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes(), "utf-8");
					sb.append(lines);
				}
				// JSONObject jsonText = JSONObject.fromObject(sb.toString());
				// System.out.println(jsonText.get("status"));
			}

			// System.out.println(sb);
			// reader.close();
			connection.disconnect();// 關閉連接
		} catch (MalformedURLException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			return sb.toString();
		}
	}

	public static void main(String args[]) {
		try {
			// System.out.println(httpRequestTest("http://140.121.197.14:8756/create_game"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}