package com.study.second_day_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 工具类
 */
public class Utils {

    /**
     * 将文章下载到本地磁盘
     *
     * @param body  文本文档内容
     * @param path  要下载到的路径
     * @param title 每一章节 标题
     */
    public static void downloadTxt(String body, String path, String title) {
        FileWriter fileWriter = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            fileWriter = new FileWriter(file, true);
            fileWriter.write(title);
            fileWriter.write("\n\n");
            fileWriter.write(body);
            fileWriter.write("\n");
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
