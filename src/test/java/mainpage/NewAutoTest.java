package mainpage;

import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AboutMySelfPage;
import pages.AccountPage;
import pages.LoginOtusPage;
import waiters.Waiters;

public class NewAutoTest {

  private static final Logger logger = (Logger) LogManager.getLogger(NewAutoTest.class);
  ChromeOptions chromeOptions = new ChromeOptions();
  private WebDriver driver;
  private Waiters waiters;
//  WebDriver webDriverNew;

  @BeforeAll
  public static void manager(){
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void init(){

    driver = new WebDriverFactory().create();
    this.waiters = new Waiters(driver);
    driver.manage().window().maximize();
  }
  @Test
  public void Test() throws InterruptedException {
    LoginOtusPage loginOtusPage = new LoginOtusPage(driver);
    AccountPage accountPage = new AccountPage(driver);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(driver);

    loginOtusPage.loginOtus(); //авторизация
    accountPage.entryLkOtus(); //вход в личный кабинет
    aboutMySelfPage.updateMySelf(); //Обновление данных о себе
    loginOtusPage.loginOtus();//авторизация
    logger.info("Повторная авторизация");

    accountPage.entryLkOtus();//вход в личный кабинет
    logger.info("Повторный вход в 'Личный кабинет'");

    aboutMySelfPage.assertMySelfData();//Проверка данных
    logger.info("Проверка данных");
  }

//  public void browserOne() throws InterruptedException {
//    webDriver = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
//    logger.info("Первичный запуск браузера");
//
//    webDriver.get("https://otus.ru");
//    logger.info("Первичное открытие сайта 'Отус'");
//
//  }
//
//  public void browserTwo() {
//    webDriverNew = new ChromeDriver(chromeOptions.addArguments("--start-maximized").addArguments("--incognito"));
//    logger.info("Повторный запуск браузера");
//
//    webDriverNew.get("https://otus.ru");
//    logger.info("Повторное открытие сайта 'Отус'");
//
//  }

  @AfterEach
  public void closeBrowser() {
    logger.info("Закрытие браузеров");
    driver.close();
//    webDriverNew.close();
  }
}
