package com.ideabox.tests.utils;

import com.ideabox.tests.config.Configuration;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by tdvoryanchenko on 21.01.2016.
 */
public class Common {
  private static final Logger logger = LoggerFactory.getLogger(Common.class);
  protected static String ideaboxUIURL = Configuration.getIdeaboxUIURL(Configuration.getEnvs());

  public static String getBaseUrl() {
   return ideaboxUIURL;
  }

  public static String getPropByKey(String key, String filename) {
    String value;
    Properties prop = ReadWriteFileData.loadProperties(filename);
    value = prop.getProperty(key);
    return value;
  }

  public static String getUserLogin(String userType) {
    logger.info("Getting user login form file"+ getPropByKey(userType+"Login", "userData"));

    return getPropByKey(userType+"Login", "userData");
  }
  public static String getUserPass(String userType) {
    return getPropByKey(userType+"Password", "userData");
  }

  /**
   * method checks if element present in DOM and is it Displayed on the page in browser
   * parameter locator is used to find element on the page
   * parameter driver gives the browser page to search to
   */
  public static boolean isElementPresent(By locator, WebDriver driver) {
    driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    boolean result = driver.findElements(locator).size() > 0;
    if (result) {
      result = driver.findElement(locator).isDisplayed();
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      return result;
    } else {
      driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      return result;
    }
  }

  public static void waitUntilElementIsHidden(By locator, WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    Assert.assertFalse( isElementPresent(locator, driver), "Loading is more then 30 sec");
  }

  public static void waitUntilElementIsPresent(By locator, WebDriver driver) {
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    Assert.assertTrue( isElementPresent(locator, driver), "Loading is more then 30 sec");
  }

  public static void turnOffModalDialog(WebDriver driver){
    JavascriptExecutor js=(JavascriptExecutor) driver;
    js.executeScript("window.onbeforeunload = undefined");
  }

  public static String getEntityFromResponse(Response response, String entity) {
    return response.body().jsonPath().getString(entity);
  }

}
