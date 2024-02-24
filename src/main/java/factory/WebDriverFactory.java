package factory;

import data.BrowserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.AboutMySelfPage;

import java.util.Locale;

public class WebDriverFactory {
  private static final Logger logger = (Logger) LogManager.getLogger(WebDriverFactory.class);
  private final String BROWSER_NAME = System.getProperty("browser", "firefox");


  public WebDriver create() {
    BrowserData browserData = BrowserData.valueOf(BROWSER_NAME.toUpperCase());
    switch (browserData) {
      case CHROME: {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        return new ChromeDriver(chromeOptions);
      }
      case FIREFOX: {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        return new FirefoxDriver(firefoxOptions);
      }
    }

    return null;
  }
}