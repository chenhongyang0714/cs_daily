package com.study.second_day;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class practice_1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.dytt8.net/index.htm");

//        等待透明层的加载
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 20);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#cs_ap_8040 > div")));

        System.out.println("透明层已加载");
//        去除透明层
//        #cs_ap_8040 > div
        WebElement lucency = webDriver.findElement(By.cssSelector("#cs_ap_8040 > div"));
        lucency.click();

////        等待人工点击回到  电影天堂首页
//        WebDriverWait webDriverWait1 = new WebDriverWait(webDriver, 10);
//        webDriverWait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#header > div > div.bd2 > div.bd3 > div:nth-child(2) > div:nth-child(1) > div > div.search > form > div.searchl > p:nth-child(1) > input")));
//
////        搜索
////        #header > div > div.bd2 > div.bd3 > div:nth-child(2) > div:nth-child(1) > div > div.search > form > div.searchl > p:nth-child(1) > input
//        WebElement search_bar = webDriver.findElement(By.cssSelector("#header > div > div.bd2 > div.bd3 > div:nth-child(2) > div:nth-child(1) > div > div.search > form > div.searchl > p:nth-child(1) > input"));
//        search_bar.sendKeys("花木兰");

    }
}
