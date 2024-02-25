package mainpage;

import data.BrowserData;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AboutMySelfPage;
import pages.AccountPage;
import pages.LoginOtusPage;

public class TestNew {
  ChromeOptions chromeOptions = new ChromeOptions();
  WebDriver webDriver;
  WebDriver webDriverNew;

  @Test
  public void Test() throws InterruptedException {
    browserOne();
    browserTwo();
    System.out.println(",kf ,fkmsdmlkdfmlkmv");
  }

  public void browserOne() throws InterruptedException {
    webDriver = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
    webDriver.get("https://otus.ru");
    LoginOtusPage loginOtusPage = new LoginOtusPage(webDriver);
    AccountPage accountPage = new AccountPage(webDriver);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(webDriver);

    loginOtusPage.loginOtus();//авторизация
    accountPage.entryLkOtus();//вход в личный кабинет
    aboutMySelfPage.updateMySelf(); //Обновление данных о себе
  }

  public void browserTwo() {
    webDriverNew = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
    webDriverNew.get("https://otus.ru");
    LoginOtusPage loginOtusPage = new LoginOtusPage(webDriverNew);
    AccountPage accountPage = new AccountPage(webDriverNew);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(webDriverNew);
    loginOtusPage.loginOtus();//авторизация
    accountPage.entryLkOtus();//вход в личный кабинет
    aboutMySelfPage.assertMySelfData();//Проверка данных
  }

  @AfterEach
  public void closeBrowser() {
    webDriver.close();
    webDriverNew.close();
  }
}

