package com.ideabox.tests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Created by tdvoryanchenko on 4/6/17.
 */

@CucumberOptions(features = "src/test/resources/features/AddIdeaFlow.feature",
  format = {"pretty", "html:report/Add-IdeaBox-html-report", "json:report/Add-IdeaBox-report.json"},
  glue = "com.ideabox.tests.steps")
public class AddIdeaRunner extends AbstractTestNGCucumberTests {
}
