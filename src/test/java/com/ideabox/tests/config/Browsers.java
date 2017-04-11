package com.ideabox.tests.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tdvoryanchenko on 5/25/16.
 */
public class Browsers {
  public static final String USERNAME = "yendvor2";
  public static final String AUTOMATE_KEY = "WstGjet7CdrkVzaq13Qe";
  public static final String BrowserStack_URl = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static WebDriver getDriver() throws MalformedURLException {
    WebDriver dr;

    DesiredCapabilities caps =new DesiredCapabilities();

//    System.setProperty("webdriver.chrome.driver", "lib/chromedriver");

    switch (System.getProperty("browser").toLowerCase()){
      case "chrome":
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "51.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "El Capitan");
        caps.setCapability("resolution", "1024x768");

        break;
      case "firefox":
        caps.setCapability("browser", "Firefox");
        caps.setCapability("browser_version", "44.0.1");
        caps.setCapability("browserstack.local", "true");
        break;
      default:
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "51.0");
        caps.setCapability("os", "OS X");
        caps.setCapability("os_version", "El Capitan");
        caps.setCapability("resolution", "1024x768");
        break;
    }
    dr = WebDriverPool.DEFAULT.getDriver(new URL(BrowserStack_URl), caps);
    return dr;
  }

  public static void closeAllDrivers(){
    WebDriverPool.DEFAULT.dismissAll();

  }

}
