package pages;

import data.CountryData;
import data.EnglishLevelData;
import data.RussiaCityData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.NoSuchElementException;

import static java.lang.Thread.sleep;

public class AboutMySelfPage extends GeneralPage {
  private static final Logger logger = (Logger) LogManager.getLogger(AboutMySelfPage.class);
  //////////исходные данные
  private final String nameLat = "Testvit";
  private final String surname = "Тесттест";
  private final String surnameLat = "testtest";
  private final String nickName = "тест";
  private final String birthDay = "05.09.1993";
  private final String country = "RUSSIA";
  private final String city = "PERM";
  private final String englishLevel = "ELEMENTARY";
  private final boolean readyToMove = true;
  private final boolean[] jobFormat = new boolean[]{true, true, false};
  private final String sex = "m";
  private final String company = "Тест";
  private final String jobTitle = "Тестировщик";
  private final String[] communicationМethod1 = new String[]{"VK", "vk.ru/test"};
  private final String[] communicationМethod2 = new String[]{"OK", "ok.ru/test"};

  //////////локаторы
  private final String jobFormatFullInpulLocator = "//input[@name='work_schedule' and @value='full']";
  private final String jobFormatFullDivLocator = "//div/label[input[@name='work_schedule' and @value='full']]";
  private final String jobFormatFlexibleInpulLocator = "//input[@name='work_schedule' and @value='flexible']";
  private final String jobFormatFlexibleDivLocator = "//div/label[input[@name='work_schedule' and @value='flexible']]";
  private final String jobFormatRemoteInpulLocator = "//input[@name='work_schedule' and @value='remote']";
  private final String jobFormatRemoteDivLocator = "//div/label[input[@name='work_schedule' and @value='remote']]";
  private final String communicationMethodTypeLocator = "//div/label/div[contains(text(),'%s')]";
  private final String communicationMethodStringLocator = "//div[div/label/div[contains(text(),'%s')]]/input";

  public AboutMySelfPage(WebDriver driver) {
    super(driver);
  }
//  public void deleteKontakt() {
//    if ((driver.findElement(By.xpath("(//div[contains(text(),'VK')]//..//..//..//..//..//..//button[contains(text(),'Удалить')])[1]")).isDisplayed())) {
//      //нажимаем кнопку Удалить
//      driver.findElement(By.xpath("(//div[contains(text(),'VK')]//..//..//..//..//..//..//button[contains(text(),'Удалить')])[1]")).click();
//    }
//    //если нет поля "ок"
//    if ((driver.findElement(By.xpath("(//div[contains(text(),'OK')]//..//..//..//..//..//..//button[contains(text(),'Удалить')])[1]")).isDisplayed())) {
//      //нажимаем кнопку Удалить
//      driver.findElement(By.xpath("(//div[contains(text(),'OK')]//..//..//..//..//..//..//button[contains(text(),'Удалить')])[1]")).click();
//    }
//  }
  public void addCommunicationMethod(String[] communicationМethod, int i) {
    //выполняем метод добавления
//    driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[3]/div[2]/div[2]/div/form/div[1]/div[3]/div[2]/div[2]/button")).click();
    driver.findElement(By.xpath("//div[label/div/span[text()='Способ связи']]")).click();
    WebElement element = driver.findElement(By.xpath(String.format("//div[not(contains(@class,'hide'))]/div/button[@title='%s']", communicationМethod[0])));
    waiters.waitElementVisible(element);
    element.click();
    waiters.waitElementVisible(driver.findElement(By.xpath(String.format("//input[@name='contact-%d-value']", i))));
    cleanAndEnter(By.xpath(String.format("//input[@name='contact-%d-value']", i)), communicationМethod[1]);
    driver.findElement(By.xpath("//button[text()='Добавить']")).click();

  }

  public void updateMySelf() throws NoSuchElementException, InterruptedException {

    cleanAndEnter(By.id("id_fname_latin"), nameLat);
    cleanAndEnter(By.id("id_lname"), surname);
    cleanAndEnter(By.id("id_lname_latin"), surnameLat);
    cleanAndEnter(By.id("id_blog_name"), nickName);
    cleanAndEnter(By.name("date_of_birth"), birthDay);
    new Actions(driver).moveToLocation(1500, 500)
        .click()
        .perform();

    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("window.scrollBy(0,700)");
    ///выбор страны

    WebElement countryElement = driver.findElement(By.xpath("//div[label/input[@name='country']]"));
    waiters.waitElementVisible(countryElement);
    new Actions(driver).moveToElement(countryElement)
        .build()
        .perform();
    countryElement.click();
    CountryData countryData = CountryData.valueOf(country);
    String countryStr = countryData.getName();
    WebElement countryButton = driver.findElement(By.xpath(String.format("//button[@title='%s']", countryStr)));
    waiters.waitElementVisible(countryButton);
    new Actions(driver).moveToElement(countryButton)
        .build()
        .perform();
    countryButton.click();

    sleep (1000);

    RussiaCityData russiaCityData = RussiaCityData.valueOf(city);
    String cityStr = russiaCityData.getName();
    ////////выбор города
    WebElement cityElement = driver.findElement(By.xpath("//div[label/input[@name='city']]"));
    waiters.waitElementVisible(cityElement);
    cityElement.click();

    sleep (1000);

    WebElement cityButton = driver.findElement(By.xpath(String.format("//button[@title='%s']", cityStr)));
    waiters.waitElementVisible(cityButton);
    cityButton.click();
    /////////уровень английского
    EnglishLevelData englishLevelData = EnglishLevelData.valueOf(englishLevel);
    WebElement englishLevelElement = driver.findElement(By.xpath("//div[label/input[@name='english_level']]"));
    waiters.waitElementVisible(englishLevelElement);
    englishLevelElement.click();
    driver.findElement(By.xpath(String.format("//button[@title='%s']", englishLevelData.getName()))).click();

    sleep (1000);

    //готов к переезду

    if (readyToMove) {
      driver.findElement(By.xpath("//label[input[@id='id_ready_to_relocate_1']]")).click();
    } else {
      driver.findElement(By.xpath("//label[input[@id='id_ready_to_relocate_0']]")).click();
    }
    ////формат работы
    checkStateAndClickCheckbox(jobFormat[0], jobFormatFullInpulLocator, jobFormatFullDivLocator);
    checkStateAndClickCheckbox(jobFormat[1], jobFormatFlexibleInpulLocator, jobFormatFlexibleDivLocator);
    checkStateAndClickCheckbox(jobFormat[2], jobFormatRemoteInpulLocator, jobFormatRemoteDivLocator);
    ///добавление контактов
//    deleteKontakt ();
    addCommunicationMethod(communicationМethod1, 0);
    js.executeScript("window.scrollBy(0,700)");
    addCommunicationMethod(communicationМethod2, 1);

    ///пол
    waiters.waitElementVisible(driver.findElement(By.id("id_gender")));
    driver.findElement(By.xpath(String.format("//option[@value='%s']", sex))).click();
    cleanAndEnter(By.id("id_company"), company);
    cleanAndEnter(By.id("id_work"), jobTitle);

    //сoхранение
    driver.findElement(By.xpath("//button[@name='continue']")).click();
  }


  public void assertMySelfData() throws NoSuchElementException {

    Assertions.assertEquals(nameLat,
        driver.findElement(By.id("id_fname_latin")).getAttribute("value"), "nameLat is right");
    Assertions.assertEquals(surname,
        driver.findElement(By.id("id_lname")).getAttribute("value"), "surname is right");
    Assertions.assertEquals(surnameLat,
        driver.findElement(By.id("id_lname_latin")).getAttribute("value"), "surnameLat is right");
    Assertions.assertEquals(nickName,
        driver.findElement(By.id("id_blog_name")).getAttribute("value"), "nickName is right");
    Assertions.assertEquals(birthDay,
        driver.findElement(By.name("date_of_birth")).getAttribute("value"), "birthDay is right");
    CountryData countryData = CountryData.valueOf(country);
    Assertions.assertEquals(countryData.getName(),
        driver.findElement(By.xpath("//label[input[@name='country']]/div")).getText(), "Country is correct");
    RussiaCityData russiaCityData = RussiaCityData.valueOf(city);
    Assertions.assertEquals(russiaCityData.getName(),
        driver.findElement(By.xpath("//label[input[@name='city']]/div")).getText(), "City is correct");
    EnglishLevelData englishLevelData = EnglishLevelData.valueOf(englishLevel);
    Assertions.assertEquals(englishLevelData.getName(),
        driver.findElement(By.xpath("//label[input[@name='english_level']]/div")).getText(), "English level is correct");
    if (readyToMove) {
      Assertions.assertTrue(driver.findElement(By.xpath("//label[input[@id='id_ready_to_relocate_1']]")).isEnabled(), "readyToMove is correct");
    } else {
      Assertions.assertTrue(driver.findElement(By.xpath("//label[input[@id='id_ready_to_relocate_0']]")).isEnabled(), "readyToMove is correct");
    }

    boolean[] jobFormatActual = new boolean[]{driver.findElement(By.xpath(jobFormatFullInpulLocator)).isSelected(),
        driver.findElement(By.xpath(jobFormatFlexibleInpulLocator)).isSelected(),
        driver.findElement(By.xpath(jobFormatRemoteInpulLocator)).isSelected()
    };
    Assertions.assertArrayEquals(jobFormat, jobFormatActual, "jobFormat is correct");

    Assertions.assertTrue(driver.findElement(By.xpath(String.format(communicationMethodTypeLocator, communicationМethod1[0]))).isDisplayed());
    Assertions.assertEquals(communicationМethod1[1],
        driver.findElement(By.xpath(String.format(communicationMethodStringLocator, communicationМethod1[0]))).getAttribute("value"));
    Assertions.assertTrue(driver.findElement(By.xpath(String.format(communicationMethodTypeLocator, communicationМethod2[0]))).isDisplayed());
    Assertions.assertEquals(communicationМethod2[1],
        driver.findElement(By.xpath(String.format(communicationMethodStringLocator, communicationМethod2[0]))).getAttribute("value"));
    Assertions.assertEquals(sex, driver.findElement(By.xpath("//select[@id='id_gender']/option[@selected='']")).getAttribute("value"), "Sex is correct");
    Assertions.assertEquals(company, driver.findElement(By.id("id_company")).getAttribute("value"), "Company is correct");
    Assertions.assertEquals(jobTitle, driver.findElement(By.id("id_work")).getAttribute("value"), "Work is correct");
  }
}