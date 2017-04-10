package com.ideabox.tests.steps;

import com.ideabox.tests.config.Browsers;
import com.ideabox.tests.utils.Common;
import com.ideabox.tests.utils.CustomReporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tdvoryanchenko on 2/24/16.
 */
public class Hooks {

  public WebDriver driver;

  public Hooks() throws MalformedURLException {
        driver= Browsers.getDriver();
  }

  @Before
  /**
   * Delete all cookies at the start of each scenario to avoid
   * shared state between tests
   */
  public void openBrowser(final Scenario scenario) throws MalformedURLException {
    driver= Browsers.getDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    Common.turnOffModalDialog(driver);
  }

  @After
  /** Embed a screenshot in tests report if tests is marked as failed*/
    public void embedScreenshot(Scenario scenario) {
    if (!scenario.isFailed()) {
      return;
    }
    scenario.write("Current Page URL is " + driver.getCurrentUrl());
    if (driver instanceof TakesScreenshot) {
      scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/jpeg");
    }
  }

  public static void afterAll() {
    Browsers.closeAllDrivers();
    CustomReporter.generateFinalReport();
  }


  public static void beforeAll() {
    CustomReporter.cleanReportDirectory();
  }
}
