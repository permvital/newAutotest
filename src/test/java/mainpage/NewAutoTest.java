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

    loginOtusPage.openPage("/"); //переход на главную страницу
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
    logger.info("Закрытие браузера");
    driver.close();
  }
}
