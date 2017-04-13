package com.ideabox.tests.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by tdvoryanchenko on 5/25/16.
 */
public class Browsers {
  public static final String USERNAME = System.getenv("BROWSERSTACK_USER");
  public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESSKEY");
  public static final String browserStack_Local = System.getenv("BROWSERSTACK_LOCAL");
  public static final String browserStack_URl = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

  public static WebDriver getDriver() throws MalformedURLException {
    WebDriver dr;
    DesiredCapabilities caps =new DesiredCapabilities();
    caps.setCapability("browserstack.local", browserStack_Local);
    if (System.getProperty("browserStack","false").toLowerCase().equals("true")){
      switch (System.getProperty("browser","chrome").toLowerCase()){
        case "chrome":
          caps.setCapability("browser", "Chrome");
          caps.setCapability("browser_version", "57.0");
          caps.setCapability("os", "OS X");
          caps.setCapability("resolution", "1024x768");
          break;
        case "firefox":
          caps.setCapability("browser", "Firefox");
          caps.setCapability("browser_version", "44.0.1");
          break;
        default:
          caps.setCapability("browser", "Chrome");
          caps.setCapability("browser_version", "57.0");
          caps.setCapability("os", "OS X");
          caps.setCapability("resolution", "1024x768");
          break;
      }
      dr = WebDriverPool.DEFAULT.getDriver(new URL(browserStack_URl), caps);
    } else {
      Capabilities firefox = DesiredCapabilities.firefox();
      Capabilities chrome = DesiredCapabilities.chrome();
      if (System.getProperty("runner", "jenkins").equals("local")) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
      }else{
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver-ubuntu");
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
      }
      switch (System.getProperty("browser","chrome").toLowerCase()){
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
    }
    return dr;
  }

  public static void closeAllDrivers(){
    WebDriverPool.DEFAULT.dismissAll();

  }

}
