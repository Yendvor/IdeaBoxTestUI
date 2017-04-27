package com.ideabox.tests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

@CucumberOptions(features = "src/test/resources/features/Login.feature",
  format = {"pretty", "html:report/Login-IdeaBox-html-report", "json:report/Login-IdeaBox-report.json"},
  glue = "com.ideabox.tests.steps")
public class LoginRunner extends AbstractTestNGCucumberTests {
}
