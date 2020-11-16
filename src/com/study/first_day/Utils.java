package com.study.first_day;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * 工具类
 */
public class Utils {

    /**
     * 将歌曲下载到指定的文件目录
     *
     * @param mp3URLString 歌曲下载链接
     * @param path   歌曲下载到的本地路径
     * @param name   歌曲名
     */
    public static void download(String mp3URLString, String path, String name) {
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            File file = new File(path + "\\" + name + ".mp3");
            // 如果 file 不存在，则创建一个
            if (!file.exists()) {
                file.createNewFile();
            }
            // 发起接口请求
            URL url = new URL(mp3URLString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            inputStream = conn.getInputStream();
            fileOutputStream = new FileOutputStream(file);
            // 缓存数组
            byte[] bys = new byte[1024];
            int chs = 0;
            while ((chs = inputStream.read(bys)) != -1) {
                fileOutputStream.write(bys, 0, chs);
            }
            System.out.println("歌曲<<" + name + ">>下载完成！！");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据 rid 获取歌曲下载链接
     *
     * @param ridString 歌曲对应的 rid
     * @return 歌曲下载链接
     */
    public static String get_url_by_rid(String ridString) {
        String mp3URLString = null;  // 歌曲下载链接
        BufferedReader bufferedReader = null;
        String urlString = "http://kuwo.cn/url?format=mp3&rid=" + ridString + "&response=url&type=convert_url3&br=320kmp3&from=web&t=1604991207945&httpsStatus=1&reqId=6fad66a1-2321-11eb-beba-c93a68b45841";
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String downloadInfo = bufferedReader.readLine();
            // 把字符串转换为 JSON
            JSONObject downloadJson = JSONObject.parseObject(downloadInfo);
            mp3URLString = downloadJson.getString("url");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mp3URLString;
    }
}