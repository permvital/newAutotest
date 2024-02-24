package mainpage;


import factory.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.AboutMySelfPage;
import pages.AccountPage;
import pages.LoginOtusPage;
import waiters.Waiters;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;


public class UpdateAboutMySelfInfo_Test {
  private static final Logger logger = (Logger) LogManager.getLogger(UpdateAboutMySelfInfo_Test.class);


  private WebDriver driver;
  private Waiters waiters;

  @BeforeAll

  public static void manager() {


    WebDriverManager.chromedriver().setup();
    WebDriverManager.firefoxdriver().setup();
  }


  @BeforeEach
  public void init() {

    driver = new WebDriverFactory().create();
    this.waiters = new Waiters(driver);
    driver.manage().window().maximize();
  }

  @AfterEach
  public void close() {
    logger.info("Закрытие браузера");
    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }


  @Test
  public void checkUpdateInfo() throws InterruptedException {

    LoginOtusPage loginOtusPage = new LoginOtusPage(driver);
    AccountPage accountPage = new AccountPage(driver);
    AboutMySelfPage aboutMySelfPage = new AboutMySelfPage(driver);

    driver.manage().window().maximize();

    loginOtusPage.openPage("/");
    logger.info("Переход на главную страницу сайта Отус");
    loginOtusPage.loginOtus();
    logger.info("Авторизация");
    accountPage.entryLkOtus();
    logger.info("Вход в личный кабинет");
    aboutMySelfPage.updateMySelf();
    logger.info("Обновление данных 'О себе'");
    close();//закрываем браузер
    logger.info("Закрытие браузера");
    init();//открываем браузер снова
    logger.info("Повторное открытие браузера");
    loginOtusPage.openPage("/");//переход на главную страницу
    logger.info("Повторно Переход на главную страницу сайта Отус");
    loginOtusPage.loginOtus();//авторизация
    logger.info("Повторно Авторизация");
    accountPage.entryLkOtus();//вход в личный кабинет
    logger.info("Повторно Вход в личный кабинет");
    aboutMySelfPage.assertMySelfData();

  }


}