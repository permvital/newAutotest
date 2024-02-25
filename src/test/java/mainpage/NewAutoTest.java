package mainpage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AboutMySelfPage;
import pages.AccountPage;
import pages.LoginOtusPage;

public class NewAutoTest {

  private static final Logger logger = (Logger) LogManager.getLogger(NewAutoTest.class);
  ChromeOptions chromeOptions = new ChromeOptions();
  WebDriver webDriver;
  WebDriver webDriverNew;

  @Test
  public void Test() throws InterruptedException {
    browserOne();
    browserTwo();
  }

  public void browserOne() throws InterruptedException {
    webDriver = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
    logger.info("Первичный запуск браузера");

    webDriver.get("https://otus.ru");
    logger.info("Первичное открытие сайта 'Отус'");

    LoginOtusPage loginOtusPage = new LoginOtusPage(webDriver);
    AccountPage accountPage = new AccountPage(webDriver);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(webDriver);

    loginOtusPage.loginOtus(); //авторизация
    accountPage.entryLkOtus(); //вход в личный кабинет
    aboutMySelfPage.updateMySelf(); //Обновление данных о себе
  }

  public void browserTwo() {
    webDriverNew = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
    logger.info("Повторный запуск браузера");

    webDriverNew.get("https://otus.ru");
    logger.info("Повторное открытие сайта 'Отус'");

    LoginOtusPage loginOtusPage = new LoginOtusPage(webDriverNew);
    AccountPage accountPage = new AccountPage(webDriverNew);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(webDriverNew);

    loginOtusPage.loginOtus();//авторизация
    logger.info("Повторная авторизация");

    accountPage.entryLkOtus();//вход в личный кабинет
    logger.info("Повторный вход в 'Личный кабинет'");

    aboutMySelfPage.assertMySelfData();//Проверка данных
  }

  @AfterEach
  public void closeBrowser() {
    logger.info("Закрытие браузеров");
    webDriver.close();
    webDriverNew.close();
  }
}
