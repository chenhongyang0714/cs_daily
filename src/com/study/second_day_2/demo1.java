package com.study.second_day_2;

import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Scanner;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Jsoup
 * 根据 小说名称  下载小说
 */
public class demo1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要下载的书名:");
        String searchTxt = scanner.nextLine();

        try {
            Document document = Jsoup.connect("http://www.xbiquge.la/modules/article/waps.php?searchkey="
                    + searchTxt).get();

            System.out.println("编号\t\t\t\t书名\t\t\t\t最新章节\t\t\t\t更新时间\t\t\t\t");
//            #checkform > table > tbody > tr
            Elements ele_tr = document.select("#checkform > table > tbody > tr");  // 获取所有小说列表

            HashMap hashMap = new HashMap();  // 存储书籍编号和该书的链接
            /* 遍历搜索得到的所有书籍 (注意书籍列表的标题问题) */
            for (int i = 1; i < ele_tr.size(); i++) {
                Element element = ele_tr.get(i);
//              #checkform > table > tbody > tr:nth-child(2) > td:nth-child(1) > a
                String title = element.select("td:nth-child(1) > a").text();  // 获取   文章名称
                String newzj = element.select("td:nth-child(2) > a").text();  // 获取 最新章节
                String author = element.select("td:nth-child(3) > a").text();
                String updateTime = element.select("td:nth-child(4) > a").text();
                System.out.println(i + "\t\t\t\t" + title + "\t\t\t\t" + newzj + "\t\t\t\t" + author + "\t\t\t\t" + updateTime);
                hashMap.put(i, element.select("td:nth-child(1) > a").attr("href"));
            }

            System.out.println("请选择书的编号:");
            String selectNum = scanner.nextLine();  // 获取用户输入的书籍的编号
            String url = hashMap.get(Integer.parseInt(selectNum)).toString();
            if (url != null && !"".equals(selectNum)) {
                System.out.println(url);
                /**
                 * 下载  小说
                 */
                Document document1 = Jsoup.connect(url).get();
                String bookname = document1.select("#info > h1").text();  // 获取小说名称
//            #list > dl > dd > a
                Elements elements = document1.select("#list > dl > dd > a");
                for (Element element : elements) {
                    String body_url = "http://www.xbiquge.la" + element.attr("href");

                    Document body_doc = Jsoup.connect(body_url).get();
                    String body = body_doc.select("#content").html();
                    body = body.replace("&nbsp", " ");  // 替换  空格
                    body = body.replace("<br>", "\n\n");  // 替换 换行
                    body = body.replace(" ; ; ; ;", "    ");

                    body = body.substring(0, body.length() - 150);

                    String title = element.text();
                    Utils.downloadTxt(body, "d:\\" + bookname + ".txt", title);

                    System.out.println(title + "下载完成！");
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
