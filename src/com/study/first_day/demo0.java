package com.study.first_day;

import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * 根据 rid 获取下载链接
 */
public class demo0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入歌曲的rid:");
        String rid = scanner.next();
        String urlString = "http://kuwo.cn/url?format=mp3&rid=" + rid + "&response=url&type=convert_url3&br=320kmp3&from=web&t=1604991207945&httpsStatus=1&reqId=6fad66a1-2321-11eb-beba-c93a68b45841";
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String result = bufferedReader.readLine();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
