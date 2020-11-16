package com.study.first_day;

import com.alibaba.fastjson.JSONObject;

/**
 * JSON 数据的解析练习
 */
public class demo1 {
    public static void main(String[] args) {
        String string = "{\"code\": 200, \"msg\": \"success\", \"url\": " +
                "\"https://ev-sycdn.kuwo.cn/fc937ed0143567266888c15507d783b0/" +
                "5faa4122/resource/n1/39/67/2304902463.mp3\"}";

        // 把字符串转换为 JSON
        JSONObject jsonObject = JSONObject.parseObject(string);
        // 想获取某个key的值
        String url = jsonObject.getString("url");

        System.out.println(url);
    }
}
