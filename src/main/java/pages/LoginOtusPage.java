package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageobject.AbsPageObject;
import waiters.Waiters;


public class LoginOtusPage extends GeneralPage {

  private static final Logger logger = (Logger) LogManager.getLogger(LoginOtusPage.class);

  private final String LOGIN = "permvpva@yandex.ru";
  private final String PASSWORD = "fdgkj8w9Ds!";
  private String enterButtonLocator = "//button[text()='Войти']";
  private String inputEmailLocator = "//div/input[@name='email']";
  private String inputPassLocator = "//input[@type='password']";
  private String enterButtonLocator2 = "//button/div[text()='Войти']";

  public LoginOtusPage(WebDriver driver) {
    super(driver);
  }


  public void loginOtus() {
    logger.info("Login OTUS");
    try {
      driver.findElement(By.xpath(enterButtonLocator)).click();
      driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div[1]/div")).click();
      cleanAndEnter(By.xpath(inputEmailLocator), LOGIN);
      driver.findElement(By.xpath("//*[@id=\"__PORTAL__\"]/div/div/div[3]/div[2]/div/div[2]/div[1]/div/div[1]/div/div[2]/div")).click();
      cleanAndEnter(By.xpath(inputPassLocator), PASSWORD);
      driver.findElement(By.cssSelector("button[type=button] div.sc-9a4spb-2"));
    } catch (Exception e) {
      logger.info(e.getMessage());
    }
  }

}