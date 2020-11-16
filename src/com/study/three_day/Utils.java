package com.study.three_day;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Utils {

    /**
     * 获取 token
     *
     * @return token值
     */
    public static String getToken() {
        String token = "";
        try {
//            方法一
            URL url = new URL("http://www.kuwo.cn");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            String setCookies = httpURLConnection.getHeaderField("Set-Cookie");
//            System.out.println(setCookies);
            String[] splits = setCookies.split(";");
            for (String split : splits) {
                if (split.contains("kw_token")) {
                    token = split.split("=")[1];
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * searchName   查询该歌手的歌曲
     *
     * @param searchName 歌手的名字
     * @param page       查询的页数
     */
    public static Connection.Response musicList(String searchName, int page) {
        String urlString = "https://kuwo.cn/api/www/search/searchMusicBykeyWord?" +
                "key=" + searchName + "&pn=" + page + "&rn=30&httpsStatus=1&reqId=f94cbc40-2488-11eb-bdad-1b8a24fee8b5";

        Connection.Response response = null;

        try {
            String token = Utils.getToken();  // 获取 token

            response = Jsoup.connect(urlString)
                    .cookie("kw_token", token)
                    .header("csrf", token)
                    .header("Referer", "http://www.kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 用户搜索框的联想搜索
     *
     * @param value 搜索框输入的值
     * @return 联想搜索的值
     */
    public static List<String> searchKey(String value) {
        String requestUrl = "https://kuwo.cn/api/www/search/searchKey?key=" + value + "&httpsStatus=1&reqId=91f026d0-26f5-11eb-a729-d3d9e252a503";
        List<String> list = new ArrayList<String>();

        try {
            String token = Utils.getToken();
            Connection.Response response = Jsoup.connect(requestUrl)
                    .cookie("kw_token", token)
                    .header("csrf", token)
                    .header("Referer", "https://kuwo.cn/")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

            /* 解析请求结果 */
            JSONObject jsonObject = JSONObject.parseObject(response.body());
            String data = jsonObject.get("data").toString();
            data = data.replace("[", "");
            data = data.replace("]", "");
            String[] splits = data.split(",");

            for (String split : splits) {
                String[] split1 = split.split("SNUM");
                String[] split2 = split1[0].split("=");
                String s = split2[1].substring(0, split2[1].length() - 4);
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
