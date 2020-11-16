package com.study.second_day;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Selenium 学习使用
 * 模拟京东登录，搜索
 */
public class demo1 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("http://www.jd.com");

//      #ttbar-login > a.link-login
        WebElement login_link = webDriver.findElement(By.cssSelector("#ttbar-login > a.link-login"));
        login_link.click();

//      #content > div.login-wrap > div.w > div > div.login-tab.login-tab-r > a
        WebElement user_link = webDriver.findElement(By.cssSelector("#content > div.login-wrap > div.w > div > div.login-tab.login-tab-r > a"));
        user_link.click();

//        #loginname
        WebElement account_name = webDriver.findElement(By.cssSelector("#loginname"));
        account_name.sendKeys("17873954278");

//      #nloginpwd
        WebElement account_password = webDriver.findElement(By.cssSelector("#nloginpwd"));
        account_password.sendKeys("ChenHongYang0");

//        #loginsubmit
        WebElement login_button = webDriver.findElement(By.cssSelector("#loginsubmit"));
        login_button.click();

//      手动过验证码
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#key")));

        // 实现商品搜搜
        // #key
        WebElement search_bar = webDriver.findElement(By.cssSelector("#key"));
        search_bar.sendKeys("apple");


        // 点击搜索按钮
//        #search > div > div.form > button
        WebElement search_button = webDriver.findElement(By.cssSelector("#search > div > div.form > button"));
        search_button.click();


    }
}
