package com.ideabox.tests.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;

/**
 * Created by tdvoryanchenko on 5/25/16.
 */
public class Browsers {

  public static WebDriver getDriver() throws MalformedURLException {
    WebDriver dr;

    Capabilities firefox = DesiredCapabilities.firefox();
    Capabilities chrome = DesiredCapabilities.chrome();
    System.setProperty("webdriver.chrome.driver", "lib/chromedriver");

    switch (System.getProperty("browser").toLowerCase()){
      case "chrome":
        dr = WebDriverPool.DEFAULT.getDriver(chrome);
        break;
      case "firefox":
        dr =  WebDriverPool.DEFAULT.getDriver(firefox);
        break;
      default:
        dr =  WebDriverPool.DEFAULT.getDriver(firefox);
        break;
    }
    return dr;
  }

  public static void closeAllDrivers(){
    WebDriverPool.DEFAULT.dismissAll();

  }

}
