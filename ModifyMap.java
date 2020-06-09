package com.huaxin;
import java.io.FileNotFoundException;
import java.lang.SecurityException;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ModifyMap {
    private static Formatter output; // outputs text to a file
    
    public ModifyMap(String fileName,int[][] modify) {
        openFile(fileName);
        modifyRecords(modify);
        closeFile();
    }

    public void openFile(String fileName) {
        try {
            output = new Formatter(fileName); // 指定file名稱 open the file
            // 如果未指定路徑，則JVM會假定該文件位於執行程序的目錄中
            // 如果文件不存在，將創建該文件
            // 如果打開了現有文件，其內容將被切去了頭
        } catch (SecurityException securityException) {
            // 如果用戶沒有權限將數據寫入文件，則會發生SecurityException
            System.err.println("Write權限被拒絕.終止。");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            // 如果文件不存在並且無法創建新文件，則會發生FileNotFoundException
            System.err.println("Error opening file.終止。");
            System.exit(1);// 終止應用程序
        }
    }

    public static void modifyRecords(int[][] input) {
        for (int i = 0; i < input.length; i++) {
            try {
                // output new record to file; assumes valid input
                for (int j = 0; j < input[i].length;j++){
                    if(j==0)
                    {
                        output.format("%d", input[i][j]);
                    }
                    else
                        output.format(",%d", input[i][j]);
                }
                output.format("%n");
                // Formatter java.util.Formatter.format(String format,
                // Object...args):類似於System.out.printf
            } catch (FormatterClosedException formatterClosedException) {
                // 如果在嘗試輸出時關閉格式化程序，則會發生FormatterClosedException
                System.err.println("Error writing to file. Terminating.");
                break;
            }
        }
    }

    public static void closeFile() {
        if (output != null)
            output.close();
    }
}