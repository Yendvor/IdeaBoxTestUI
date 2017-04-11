package com.ideabox.tests.config;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

import java.net.MalformedURLException;
import java.net.URL;

public class Browsers {


  public static WebDriver getDriver() throws MalformedURLException {
    WebDriver dr;

    Capabilities firefox = DesiredCapabilities.firefox();
    Capabilities chrome = DesiredCapabilities.chrome();
    System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
    switch (System.getProperty("browser").toLowerCase()){
      case "chrome":
        dr = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chrome);
//        dr = WebDriverPool.DEFAULT.getDriver(chrome);
        break;
      case "firefox":
        dr = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefox);
//        dr =  WebDriverPool.DEFAULT.getDriver(firefox);
        break;
      default:
        dr = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefox);
//       dr =  WebDriverPool.DEFAULT.getDriver(firefox);
        break;
    }
    return dr;
  }

  public static void closeAllDrivers(){
    WebDriverPool.DEFAULT.dismissAll();

  }

}
