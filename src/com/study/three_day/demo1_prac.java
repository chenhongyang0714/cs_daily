package com.study.three_day;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问 链接: https://kuwo.cn/api/www/search/searchKey?key=%E5%91%A8&httpsStatus=1&reqId=91f026d0-26f5-11eb-a729-d3d9e252a503
 * 根据 关键字  实现联想 (用于搜索框的联想功能)
 * (该链接不可以直接访问   会403错误)
 * 需要通过代码访问，相当于破解
 *
 *  已封装到 Utils
 */
public class demo1_prac {
    public static void main(String[] args) {
        String searchWord = "周";
        String requestUrl = "https://kuwo.cn/api/www/search/searchKey?key=" + searchWord + "&httpsStatus=1&reqId=91f026d0-26f5-11eb-a729-d3d9e252a503";

        try {
            String token = Utils.getToken();

            Response response = Jsoup.connect(requestUrl)
                    .cookie("kw_token", token)
                    .header("csrf", token)
                    .header("Referer", "https://kuwo.cn/")
                    .method(Connection.Method.GET)
                    .ignoreContentType(true)
                    .execute();

//            System.out.println(response.body());

            /* 解析请求结果 */

            JSONObject jsonObject = JSONObject.parseObject(response.body());
            String data = jsonObject.get("data").toString();

            data = data.replace("[", "");
            data = data.replace("]", "");

            String[] splits = data.split(",");

            List<String> list = new ArrayList<String>();
            for (String split : splits) {
                String[] split1 = split.split("SNUM");
                String[] split2 = split1[0].split("=");
                String s = split2[1].substring(0, split2[1].length()-4);
                list.add(s);
            }

            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
