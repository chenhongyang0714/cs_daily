package com.study.three_day;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;

import java.util.Scanner;

/**     案例
 * 根据用户的输入，查询该歌手的歌曲
 */
public class demo2 {
    public static void main(String[] args) {
        System.out.println("请输入歌手的名字:");
        Scanner scan = new Scanner(System.in);
        String searchName = scan.nextLine();
        Connection.Response response = Utils.musicList(searchName, 1);

//        对 JSON 数据进行解析
        JSONObject jsonObject = JSONObject.parseObject(response.body());
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray list = data.getJSONArray("list");
        for (Object j : list) {
            JSONObject music = (JSONObject) j;
            System.out.println(music.getString("name"));
        }
    }
}
