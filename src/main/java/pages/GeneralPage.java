package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageobject.AbsPageObject;

public abstract class GeneralPage extends AbsPageObject {

  private static final String BASE_URL = System.getProperty("base.url", "https://otus.ru");
  public static final Logger logger = (Logger) LogManager.getLogger(GeneralPage.class);

  public GeneralPage(WebDriver driver) {
    super(driver);
  }

  public void cleanAndEnter(By by, String sendedKey) {
    WebElement element = driver.findElement(by);
    new Actions(driver).moveToElement(element)
        .click()
        .perform();
    waiters.waitElementVisible(element);
    element.clear();
    element.sendKeys(sendedKey);

  }

  public static void openPage(String path) {
    driver.get(BASE_URL + path);
  }

  public void checkStateAndClickCheckbox(boolean mustBeState, String inputLocator, String divLocator) {
    if (mustBeState) {
      if (!driver.findElement(By.xpath(inputLocator)).isSelected()) {
        driver.findElement(By.xpath(divLocator)).click();
      }
    } else if (!mustBeState) {
      if (driver.findElement(By.xpath(inputLocator)).isSelected()) {
        driver.findElement(By.xpath(divLocator)).click();
      }
    }
  }
}