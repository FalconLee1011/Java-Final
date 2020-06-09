// 可以放圖到視窗上
package peekaboo.menu;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowGUI {
	public static void main(String [] args) {
		//為事件排程執行緒安排一個任務
		//建立並顯示這個程式的圖形使用者介面
		ShowGUI main= new ShowGUI();

		Runnable runnable = new Runnable() {
			public void run() {
				main.createAndShowGUI();
			}
		};
		
		
		javax.swing.SwingUtilities.invokeLater (runnable);
		
	}
	
	//創鍵一個視窗，並顯示
	private  void createAndShowGUI() {
		//創鍵並設定視窗
		JFrame frame = new JFrame("Hello Swing");
		
		//為視窗設定一些引數：
		//顯示視窗
		frame.setVisible(true);
		//調整視窗的大小
		frame.setSize(650, 650);
		//設定視窗的位置
		frame.setLocation(700, 300);
		
		//新增影象
		frame.add(new JLabel(new ImageIcon("f.jpg")));
		
		//視窗關閉時退出程式
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}