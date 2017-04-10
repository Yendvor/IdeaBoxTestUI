package com.ideabox.tests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

@CucumberOptions(features = "src/test/resources/features/Login.feature",
  format = {"pretty", "html:report/Login-IdeaBox-html-report", "json:report/Login-IdeaBox-report.json"},
  glue = "com.ideabox.tests.steps")
public class LoginRunner extends AbstractTestNGCucumberTests {
  @BeforeSuite public static void setup() {
    System.out.println("Before suite");
//    Hooks.beforeAll();
  }

  @AfterSuite public static void teardown() {
    System.out.println("After Suite");
//    Hooks.afterAll();
  }
}
