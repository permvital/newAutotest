package waiters;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobject.AbsPageObject;

import java.time.Duration;

import static org.junit.platform.commons.util.Preconditions.condition;

public class Waiters {
  private WebDriver driver;

  public Waiters(WebDriver driver) {
    this.driver = driver;
  }


  public boolean waitForCondition(ExpectedCondition condition) {
    try {
      WebDriverWait webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
      webDriverWait.until(condition);
      return true;
    } catch (TimeoutException ignores) {
      return false;
    }
  }

  public boolean waitElementVisible(WebElement element) {
    return waitForCondition(ExpectedConditions.visibilityOf(element));
  }
}