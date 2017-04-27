package com.ideabox.tests.steps;

import com.ideabox.tests.config.Browsers;
import com.ideabox.tests.utils.BaseUtil;
import com.ideabox.tests.utils.Common;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tdvoryanchenko on 2/24/16.
 */
public class Hooks extends BaseUtil{

 private BaseUtil base;

  public Hooks(BaseUtil base) {
    this.base=base;
  }

  @Before
  /**
   * Delete all cookies at the start of each scenario to avoid
   * shared state between tests
   */
  public void openBrowser(final Scenario scenario)  {
    try {
      base.driver= Browsers.getDriver();
      base.driver.manage().window().maximize();
      base.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    //  base.driver.manage().deleteAllCookies();
      Common.turnOffModalDialog(base.driver);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }
  }

  @After
  /** Embed a screenshot in tests report if tests is marked as failed*/
    public void embedScreenshot(Scenario scenario) {
    if (!scenario.isFailed()) {
      return;
    }
    scenario.write("Current Page URL is " + base.driver.getCurrentUrl());
    if (base.driver instanceof TakesScreenshot) {
      scenario.embed(((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES), "image/jpeg");
    }
  }
  @AfterSuite
  public void testDown(){
    Browsers.closeAllDrivers();
  }

}
