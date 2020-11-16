package com.study.second_day;

import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 *  封装接口
 */
public class Utils {

    /**
     * 向 urlPath 发送请求，返回请求结果
     *
     * @param urlPath 需要请求的 url
     * @return 请求 url 返回的结果
     */
    public static String sendUrl(String urlPath) {
        String result = null;  // 请求url返回的结果
        BufferedReader bufferedReader = null;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            result = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
