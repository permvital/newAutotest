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
import pages.AboutMySelfPage;
import pages.AccountPage;
import pages.LoginOtusPage;
import waiters.Waiters;

import java.util.ArrayList;
import java.util.List;

public class NewAutoTest {

  private static final Logger logger = (Logger) LogManager.getLogger(NewAutoTest.class);
  private List<WebDriver> driver = new ArrayList<>();
  private Waiters waiters;
  LoginOtusPage loginOtusPage;
  AccountPage accountPage;
  AboutMySelfPage aboutMySelfPage;
  @BeforeAll
  public static void manager(){
    WebDriverManager.chromedriver().setup();
  }

  @BeforeEach
  public void init() {
    WebDriver dr = new WebDriverFactory().create();
    this.waiters = new Waiters(dr);
    dr.manage().window().maximize();
    driver.add(dr);
    loginOtusPage = new LoginOtusPage(dr);
    accountPage = new AccountPage(dr);
    aboutMySelfPage = new AboutMySelfPage(dr);
  }

  @Test
  public void Test() throws InterruptedException {
    loginOtusPage.openPage("/"); //переход на главную страницу
    logger.info("Переход на главную страницу");
    loginOtusPage.loginOtus(); //авторизация
    accountPage.entryLkOtus(); //вход в личный кабинет
    aboutMySelfPage.updateMySelf(); //Обновление данных о себе

    closeBrowser();
    init();


    loginOtusPage.openPage("/"); //переход на главную страницу
    loginOtusPage.loginOtus();//авторизация
    logger.info("Повторная авторизация");
    accountPage.entryLkOtus();//вход в личный кабинет
    logger.info("Повторный вход в 'Личный кабинет'");
    aboutMySelfPage.assertMySelfData();//Проверка данных
    logger.info("Проверка данных");
  }

  @AfterEach
  public void closeBrowser() {
    for (WebDriver dr : driver) {
      logger.info("Закрытие браузера");
      dr.quit();
    }
  }
}
