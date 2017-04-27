package com.ideabox.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory
{

  private DriverFactory()
  {}
  private static DriverFactory instance = new DriverFactory();

  public static DriverFactory getInstance()
  {
    return instance;
  }

  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
  {
    @Override
    protected WebDriver initialValue()
    {
      WebDriver dr;
      if (System.getProperty("runner", "jenkins").equals("local")) {
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver-mac");
      }else{
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver-ubuntu");
        System.setProperty("webdriver.gecko.driver", "lib/geckodriver");
      }
      switch (System.getProperty("browser").toLowerCase()){
        case "chrome":
          dr =  new ChromeDriver();
          break;
        case "firefox":
          dr =  new FirefoxDriver();
          break;
        default:
          dr =  new FirefoxDriver();
          break;
      }
      return dr;
    }

  };

  public WebDriver getDriver() // call this method to get the driver object and launch the browser
  {
    return driver.get();
  }

  public void removeDriver() // Quits the driver and closes the browser
  {
    driver.get().quit();
    driver.remove();
  }
}
