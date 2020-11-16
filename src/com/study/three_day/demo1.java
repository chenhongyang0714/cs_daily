package com.study.three_day;

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 访问 链接: https://kuwo.cn/api/www/search/searchMusicBykeyWord?key=%E5%91%A8%E6%9D%B0%E4%BC%A6&pn=1&rn=30&httpsStatus=1&reqId=f94cbc40-2488-11eb-bdad-1b8a24fee8b5
 *  根据 关键字  搜索歌曲
 * (该链接不可以直接访问   会403错误)
 * 需要通过代码访问，相当于破解
 *
 *  已封装到 Utils
 */
public class demo1 {
    public static void main(String[] args) {
        String urlString = "https://kuwo.cn/api/www/search/searchMusicBykeyWord?key=%E5%91%A8%E6%9D%B0%E4%BC%A6&pn=1&rn=30&httpsStatus=1&reqId=f94cbc40-2488-11eb-bdad-1b8a24fee8b5";

        try {
            String token = Utils.getToken();  // 获取 token

//            Response response = Jsoup.connect(urlString)
//                    .cookie("kw_token", token)
//                    .header("csrf", token)
//                    .header("Referer", "http://www.kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6")
//                    .method(Connection.Method.GET)
//                    .ignoreContentType(true)
//                    .execute();
////            System.out.println(response.body());
//
//            /* 解析请求结果 */
//            JSONObject jsonObject = JSONObject.parseObject(response.body());
//            JSONObject data = jsonObject.getJSONObject("data");
//            JSONArray list = data.getJSONArray("list");
//            for (Object j : list) {
//                JSONObject music = (JSONObject) j;
//                System.out.println(music.getString("name"));
//            }
//            方法二
            System.out.println("token:" + token);
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestProperty("Cookie", "kw_token="+token);
            httpURLConnection.setRequestProperty("csrf", token);
            httpURLConnection.setRequestProperty("Referer", "https://kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
            String result = bufferedReader.readLine();
            JSONObject jsonObject = JSONObject.parseObject(result);
            JSONObject data = jsonObject.getJSONObject("data");
            JSONArray list = data.getJSONArray("list");
            for (Object j : list) {
                JSONObject music = (JSONObject) j;
                System.out.println(music.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
