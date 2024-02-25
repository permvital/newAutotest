package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageobject.AbsPageObject;

import java.time.Duration;

public class AccountPage extends GeneralPage {
  private static final Logger logger = (Logger) LogManager.getLogger(AccountPage.class);

  public AccountPage(WebDriver driver) {
    super(driver);
  }

  public void entryLkOtus() {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    WebElement enter = driver.findElement(By.cssSelector("#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > button"));
    enter.click();
    waiters.waitElementVisible(enter);
    new Actions(driver).moveToElement(enter).perform();
    driver.findElement(By.cssSelector("#__next > div.sc-1j17uuq-0.klmZDZ > div.sc-r03h0s-0.bLpisq > div > section > div:nth-child(2)")).click();
    WebElement myProfil = driver.findElement(By.cssSelector("#__next > div.sc-1j17uuq-0.klmZDZ > div.sc-r03h0s-0.bLpisq > div > section > div.sc-r03h0s-5.sc-1youhxc-2.bYKNcH.ifrJtg.sc-1og4wiw-0-Component.fgPsmr > div.sc-qjead8-0.ePqlXd.sc-f3yn2n-0.DnSJp > div > a:nth-child(1)"));
    waiters.waitElementVisible(myProfil);
    myProfil.click();
  }
}