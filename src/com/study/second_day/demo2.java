package com.study.second_day;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * http://129.204.31.174:8887/zking/index.html
 * 代码寻找 蔡依林
 */
public class demo2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        // 打开网址
        webDriver.get("http://129.204.31.174:8887/zking/index.html");

        // 等待一直等到能点击某个元素时 才继续执行
        /**
         * webDriver:浏览器驱动
         * timeout:最长超时时间，默认以秒为单位
         */
//        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#index > div.btns > button")));

        // 点击开始游戏
//        #index > div.btns > button
        WebElement button_start = webDriver.findElement(By.cssSelector("#index > div.btns > button"));
        button_start.click();

        /**
         * 老师的 方法: 找到 蔡依林 的图片，然后点击
         */
        while (true) {
//        拿 box 下面全部的 span
            List<WebElement> elements = webDriver.findElements(By.cssSelector("#box > span"));
            for (WebElement webElement : elements) {
//                try {
                    String style = webElement.getAttribute("style");
                    System.out.println(style);
                    if (style.indexOf("2.png") > 0) {
                        webElement.click();
                        break;
                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        }

        /**
         * 我的方法：扯淡方法  慢的一批
         */
//        while (true) {
////        拿 box 下面全部的 span
//            List<WebElement> elements = webDriver.findElements(By.cssSelector("#box > span"));
//            for (WebElement webElement: elements) {
//                try{
////                    String style = webElement.getAttribute("style");
////                    System.out.println(style);
////                    if(style.indexOf("2.png")>0) {
////                        webElement.click();
////                        break;
////                    }
//                    webElement.click();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }

    }
}
