package factory;

import data.BrowserData;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
  private static final Logger logger = (Logger) LogManager.getLogger(WebDriverFactory.class);
  private static final String BROWSER_URL = System.getProperty("browser", "CHROME").toUpperCase();


  public static WebDriver create() {
    BrowserData browserData = BrowserData.valueOf(BROWSER_URL.toUpperCase());
    WebDriver driver = null;

    switch (browserData) {
      case CHROME: {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--remote-allow-origins=*");
        return new ChromeDriver(chromeOptions);
      }
    }
    return null;
  }
}
