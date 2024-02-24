package pageobject;

import org.openqa.selenium.WebDriver;
import waiters.Waiters;

public abstract class AbsPageObject {
  protected static WebDriver driver;
  protected Waiters waiters;


  public AbsPageObject(WebDriver driver) {
    this.driver = driver;
    this.waiters = new Waiters(driver);
  }
}